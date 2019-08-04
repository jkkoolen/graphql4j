package eu.ludimus.graphql.generator;

import eu.ludimus.graphql.fields.EnumField;
import eu.ludimus.graphql.fields.Field;
import eu.ludimus.graphql.fields.FieldFactory;
import eu.ludimus.graphql.fields.FieldProperty;
import eu.ludimus.graphql.fields.InputObjectField;
import eu.ludimus.graphql.fields.InterfaceField;
import eu.ludimus.graphql.fields.ObjectField;
import eu.ludimus.graphql.fields.QueryField;
import eu.ludimus.graphql.fields.ScalarField;
import eu.ludimus.graphql.generator.visitor.ReflectionVisitor;
import freemarker.template.Configuration;
import freemarker.template.Template;
import graphql.language.EnumTypeDefinition;
import graphql.language.EnumValueDefinition;
import graphql.language.FieldDefinition;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.InterfaceTypeDefinition;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ScalarTypeDefinition;
import graphql.language.TypeName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GeneratorVisitor extends ReflectionVisitor {
    private static final String PACKAGE_NAME = "packageName";
    private static final String FIELD_KEY = "field";
    private static final String IMPORT_KEY = "import";
    private static final String QUERY_PACKAGE_SUFFIX = ".query";
    private static final String MODEL_PACKAGE_SUFFIX = ".model";
    private final FieldFactory fieldFactory;
    private Template enumTemplate;
    private Template grapqlizerClassTemplate;
    private Template queryTemplate;
    private Template graphQLizerTemplate;
    private Template modelClassTemplate;
    private Template modelInterfaceTemplate;

    private Map<String, EnumTypeDefinition> enums = new HashMap<>();
    private Map<String, InputObjectTypeDefinition> inputObjects = new HashMap<>();
    private Map<String, InterfaceTypeDefinition> interfaces = new HashMap<>();
    private Map<String, ObjectTypeDefinition> objects = new HashMap<>();
    private Map<String, ScalarField> scalars = new HashMap<>();

    @Autowired
    public GeneratorVisitor(FieldFactory fieldFactory, Configuration configuration) {
        this.fieldFactory = fieldFactory;
        try {
            enumTemplate = configuration.getTemplate("EnumTemplate.ftl");
            grapqlizerClassTemplate = configuration.getTemplate("GraphQLizerClassTemplate.ftl");
            graphQLizerTemplate = configuration.getTemplate("GraphQLizerTemplate.ftl");
            modelClassTemplate = configuration.getTemplate("ModelClassTemplate.ftl");
            modelInterfaceTemplate = configuration.getTemplate("ModelInterfaceTemplate.ftl");
            queryTemplate = configuration.getTemplate("QueryTemplate.ftl");
        } catch (Exception e) {
            log.error("Unable to read template files", e);
        }
    }


    @Override
    public void visitEnumTypeDefinition(EnumTypeDefinition definition) {
        enums.put(definition.getName(), definition);

    }

    @Override
    public void visitInputObjectTypeDefinition(InputObjectTypeDefinition definition) {
        inputObjects.put(definition.getName(), definition);
    }

    @Override
    public void visitInterfaceTypeDefinition(InterfaceTypeDefinition definition) {
        interfaces.put(definition.getName(), definition);
    }

    @Override
    public void visitObjectTypeDefinition(ObjectTypeDefinition definition) {
        objects.put(definition.getName(), definition);
    }

    @Override
    public void visitScalarTypeDefinition(ScalarTypeDefinition definition) {
        scalars.put(definition.getName(), fieldFactory.createScalarField(definition.getName()));
    }


    private Field nameToType(String name) {
        if (scalars.containsKey(name)) {
            return scalars.get(name);
        } else if (objects.containsKey(name)) {
            return fieldFactory.createObjectField(name);
        } else if (inputObjects.containsKey(name)) {
            return fieldFactory.createInputObjectField(name);
        } else if (interfaces.containsKey(name)) {
            return fieldFactory.createInterfaceField(name);
        } else if (enums.containsKey(name)) {
            return fieldFactory.createEnumField(name);
        }
        switch (name) {
            case "String":
                return fieldFactory.createStringField();
            case "Boolean":
                return fieldFactory.createBooleanField();
            case "Int":
                return fieldFactory.createIntegerField();
            default:
                log.error(name + " unknown field!");
        }
        return null;
    }

    private FieldProperty mapTypeToProperty(graphql.language.Type type, String name) {
        if (type instanceof graphql.language.ListType) {
            graphql.language.ListType t = (graphql.language.ListType) type;
            String typeName = ((TypeName) t.getType()).getName();
            return new FieldProperty(fieldFactory.createListField(nameToType(typeName)), name);
        } else if (type instanceof graphql.language.NonNullType) {
            graphql.language.NonNullType t = (graphql.language.NonNullType) type;
            String typeName = ((TypeName) t.getType()).getName();
            return new FieldProperty(nameToType(typeName), name);
        } else {
            String typeName = ((TypeName) type).getName();
            return new FieldProperty(nameToType(typeName), name);
        }

    }

    public void generate(String packageName, String outputPath) {
        { //create interface
            Map<String, Object> model = new HashMap<>();
            model.put(PACKAGE_NAME, packageName + QUERY_PACKAGE_SUFFIX);
            model.put(FIELD_KEY, fieldFactory.createInterfaceField("GraphQLizer"));
            processForTemplate(graphQLizerTemplate, model, outputPath);
        }

        objects.values().forEach(definition -> {

            if (definition.getFieldDefinitions().stream().map(FieldDefinition::getInputValueDefinitions).map(List::size).anyMatch(size -> size > 0)) {
                QueryField field = fieldFactory.createQueryField(definition.getName());
                definition.getFieldDefinitions().forEach(fieldDefinition -> field.addMethod(fieldDefinition.getName(), fieldDefinition.getInputValueDefinitions()
                        .stream()
                        .map(ivd -> mapTypeToProperty(ivd.getType(), ivd.getName()))
                        .collect(Collectors.toList())));
                Map<String, Object> model = new HashMap<>();
                model.put(PACKAGE_NAME, packageName + QUERY_PACKAGE_SUFFIX);
                model.put(FIELD_KEY, field);
                model.put(IMPORT_KEY, "import " + packageName + ".model.*;");
                processForTemplate(queryTemplate, model, outputPath);
            } else {
                ObjectField field = fieldFactory.createObjectField(definition.getName());
                field.setImplement(definition.getImplements().stream().map(type -> ((TypeName) type).getName()).collect(Collectors.joining(",")));
                definition.getFieldDefinitions().forEach(def -> field.add(mapTypeToProperty(def.getType(), def.getName())));
                Map<String, Object> model = new HashMap<>();
                model.put(PACKAGE_NAME, packageName + MODEL_PACKAGE_SUFFIX);
                model.put(FIELD_KEY, field);
                processForTemplate(modelClassTemplate, model, outputPath);
                model.put(PACKAGE_NAME, packageName + QUERY_PACKAGE_SUFFIX);
                model.put(IMPORT_KEY, "import " + packageName + ".model.*;");
                processForTemplate(grapqlizerClassTemplate, model, outputPath);
            }
        });


        interfaces.values().forEach(definition -> {
            InterfaceField field = fieldFactory.createInterfaceField(definition.getName());
            definition.getFieldDefinitions().forEach(def -> field.add(mapTypeToProperty(def.getType(), def.getName())));
            Map<String, Object> model = new HashMap<>();
            model.put(PACKAGE_NAME, packageName + MODEL_PACKAGE_SUFFIX);
            model.put(FIELD_KEY, field);
            processForTemplate(modelInterfaceTemplate, model, outputPath);
        });

        inputObjects.values().forEach(definition -> {
            InputObjectField field = fieldFactory.createInputObjectField(definition.getName());
            definition.getInputValueDefinitions().forEach(def -> field.add(mapTypeToProperty(def.getType(), def.getName())));
            Map<String, Object> model = new HashMap<>();
            model.put(PACKAGE_NAME, packageName + MODEL_PACKAGE_SUFFIX);
            model.put(FIELD_KEY, field);
            processForTemplate(modelClassTemplate, model, outputPath);
            model.put(PACKAGE_NAME, packageName + QUERY_PACKAGE_SUFFIX);
            model.put(IMPORT_KEY, "import " + packageName + ".model.*;");
            processForTemplate(grapqlizerClassTemplate, model, outputPath);
        });

        enums.values().forEach(definition -> {
            EnumField field = fieldFactory.createEnumField(definition.getName());
            definition.getChildren().forEach(value -> field.add(((EnumValueDefinition) value).getName()));
            Map<String, Object> model = new HashMap<>();
            model.put(PACKAGE_NAME, packageName + MODEL_PACKAGE_SUFFIX);
            model.put(FIELD_KEY, field);
            processForTemplate(enumTemplate, model, outputPath);
        });

    }

    private void processForTemplate(Template template, Map<String, Object> model, String outputPath) {
        try {
            File file = new File(outputPath + File.separator + "grapqlizer" + File.separator + model.get(PACKAGE_NAME).toString().replaceAll("\\.", "/") + File.separator);
            file.mkdirs();
            String fileName = ((Field) model.get(FIELD_KEY)).getType() + ".java";
            FileWriter out = new FileWriter(file.getAbsolutePath() + File.separator + fileName);
            template.process(model, out);
            out.close();
        } catch (Exception e) {
            log.error("Error during processing template ", e.getMessage(), e);
        }
    }

}
