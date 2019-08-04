package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import lombok.Getter;

@Getter
public class InputObjectField extends AbstractObjectField {
    private boolean isObject;

    InputObjectField(String name, ScalarProperties scalarProperties) {
        super(name, scalarProperties);
        isObject = true;
    }

    @Override
    public boolean isInputObject() {
        return true;
    }
}
