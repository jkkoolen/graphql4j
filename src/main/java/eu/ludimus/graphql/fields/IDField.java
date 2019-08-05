package eu.ludimus.graphql.fields;

public class IDField extends AbstractField {
    IDField() {
        setType("UUID");

    }

    @Override
    public String defaultValue(String variableName) {
        return variableName + " = UUID.randomUUID()";
    }
}
