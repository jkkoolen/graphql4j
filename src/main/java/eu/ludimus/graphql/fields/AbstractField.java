package eu.ludimus.graphql.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
abstract class AbstractField implements Field {
    @Setter
    private String type;

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isInputObject() {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isEnum() {
        return false;
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }
}
