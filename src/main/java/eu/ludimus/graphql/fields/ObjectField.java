package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import lombok.Getter;

@Getter
public class ObjectField extends AbstractObjectField {
    private boolean isObject;

    ObjectField(String name, ScalarProperties scalarProperties) {
        super(name, scalarProperties);
        isObject = true;
    }
}
