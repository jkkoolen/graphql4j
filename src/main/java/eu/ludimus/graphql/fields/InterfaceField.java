package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import eu.ludimus.graphql.fields.exception.FieldException;
import lombok.Getter;

@Getter
public class InterfaceField extends AbstractObjectField {
    private boolean isInterface;

    InterfaceField(String name, ScalarProperties scalarProperties) {
        super(name, scalarProperties);
        this.isInterface = true;
    }

    @Override
    public String defaultValue() {
        throw new FieldException("This should never happen! Interface field cannot be instantiated.");
    }
}
