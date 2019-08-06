package eu.ludimus.graphql.fields;

public class BooleanField extends AbstractPrimitiveField {
    BooleanField() {
        setType("Boolean");
    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = Boolean.TRUE";
    }
}
