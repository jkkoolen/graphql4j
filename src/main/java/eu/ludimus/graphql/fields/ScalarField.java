package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;

public class ScalarField extends AbstractField {
    private ScalarProperties scalarProperties;

    ScalarField(String name, ScalarProperties scalarProperties) {
        setType(name);
        this.scalarProperties = scalarProperties;
    }

    @Override
    public String defaultValue(String variableName) {
        return scalarProperties.defaultValue(getType()).replaceAll("\\$\\{VARIABLE}", variableName);
    }
}
