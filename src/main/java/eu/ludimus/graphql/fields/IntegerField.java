package eu.ludimus.graphql.fields;

public class IntegerField extends AbstractField {
    IntegerField() {
        setType("Integer");

    }

    @Override
    public String defaultValue() {
        return "Integer.valueOf(1)";
    }
}
