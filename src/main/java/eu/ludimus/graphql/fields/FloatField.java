package eu.ludimus.graphql.fields;

public class FloatField extends AbstractPrimitiveField {
    FloatField() {
        setType("Float");

    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = Float.valueOf(1.5)";
    }
}
