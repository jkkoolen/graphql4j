package eu.ludimus.graphql.fields;

public class IntegerField extends AbstractField {
    IntegerField() {
        setType("Integer");

    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = Integer.valueOf(1)";
    }
}
