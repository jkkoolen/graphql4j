package eu.ludimus.graphql.fields;

import lombok.Getter;

@Getter
public class ListField extends AbstractField {
    private Field listField;
    ListField(Field field) {
        this.listField = field;
        setType("List<" + (field.isInterface() ? "? extends " : "") + field.getType() + ">");
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public String defaultValue() {
        return "java.util.Collections.emptyList()";
    }
}
