package eu.ludimus.graphql.fields;

public class BooleanField extends AbstractField {
    BooleanField() {
        setType("Boolean");
    }

    @Override
    public String defaultValue() {
        return "Boolean.TRUE";
    }
}
