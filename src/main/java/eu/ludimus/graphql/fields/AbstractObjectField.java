package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AbstractObjectField extends AbstractField {
    private List<FieldProperty> properties = new ArrayList<>();
    private final ScalarProperties scalarProperties;
    @Setter
    private String implement = "";

    AbstractObjectField(String name, ScalarProperties scalarProperties) {
        setType(name);
        this.scalarProperties = scalarProperties;
    }

    public String imports() {
        String imports = "";
        if(properties.stream().map(FieldProperty::getField).anyMatch(Field::isList)) {
            imports += "import java.util.List;\n";
        }

        if(properties.stream().map(FieldProperty::getField).anyMatch(field -> field instanceof IDField)) {
            imports += "import java.util.UUID;\n";
        }

        return properties.stream()
                .map(FieldProperty::getField)
                .filter(f -> f instanceof ScalarField)
                .map(Field::getType)
                .map(scalarProperties::getImportForType)
                .distinct()
                .collect(Collectors.joining("\n", imports, ""));

    }

    public String getImplement() {
        if(implement.isEmpty()) {
            return "";
        }
        return " implements " + implement;
    }

    public AbstractObjectField add(FieldProperty property) {
        properties.add(property);
        return this;
    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = new " + getType() + "()";
    }
}
