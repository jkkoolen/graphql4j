package eu.ludimus.graphql.fields;

public class FloatField extends AbstractField {
    FloatField() {
        setType("Float");

    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = Float.valueOf(1.5)";
    }
}
