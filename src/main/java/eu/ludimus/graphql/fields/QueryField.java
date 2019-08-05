package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import eu.ludimus.graphql.fields.exception.FieldException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QueryField extends AbstractField {
    private List<Method> methods = new ArrayList<>();
    private final ScalarProperties scalarProperties;

    @Getter
    @AllArgsConstructor
    public static class Method {
        private String name;
        private List<FieldProperty> arguments;
    }

    public List<FieldProperty> getProperties() {
        return methods.stream()
                .map(Method::getArguments)
                .flatMap(Collection::stream)
                .distinct().collect(Collectors.toList());
    }

    QueryField(String name, ScalarProperties scalarProperties) {
        setType(name);
        this.scalarProperties = scalarProperties;
    }

    public QueryField addMethod(String name, List<FieldProperty> arguments) {
        methods.add(new Method(name, arguments));
        return this;
    }

    public String imports() {
        String imports = "";
        if (methods.stream().map(Method::getArguments).flatMap(Collection::stream).distinct().map(FieldProperty::getField).anyMatch(Field::isList)) {
            imports += "import java.util.List;\n";
        }

        if(methods.stream().map(Method::getArguments).flatMap(Collection::stream).distinct().map(FieldProperty::getField).anyMatch(field -> field instanceof IDField)) {
            imports += "import java.util.UUID;\n";
        }


        return methods.stream().map(Method::getArguments).flatMap(Collection::stream)
                .map(FieldProperty::getField)
                .filter(f -> f instanceof ScalarField)
                .map(Field::getType)
                .map(scalarProperties::getImportForType)
                .distinct()
                .collect(Collectors.joining("\n", imports, ""));
    }

    @Override
    public String defaultValue(String variableName) {
        throw new FieldException("This should never happen! Interface field cannot be instantiated.");
    }
}
