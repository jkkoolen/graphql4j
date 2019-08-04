package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;

public class ScalarField extends AbstractField {
    ScalarProperties scalarProperties;
    ScalarField(String name, ScalarProperties scalarProperties) {
        setType(name);
        this.scalarProperties = scalarProperties;
    }

    @Override
    public String defaultValue() {
        return scalarProperties.defaultValue(getType());
    }
}
