package eu.ludimus.graphql.fields;

public class BooleanField extends AbstractField {
    BooleanField() {
        setType("Boolean");
    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = Boolean.TRUE";
    }
}
