package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import lombok.Getter;

@Getter
public class InterfaceField extends AbstractObjectField {
    private boolean isInterface;

    InterfaceField(String name, ScalarProperties scalarProperties) {
        super(name, scalarProperties);
        this.isInterface = true;
    }
}
