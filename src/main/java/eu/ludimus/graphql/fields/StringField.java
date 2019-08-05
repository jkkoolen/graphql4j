package eu.ludimus.graphql.fields;

public class StringField extends AbstractField {
    StringField() {
        setType("String");
    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = \"\"";
    }
}
