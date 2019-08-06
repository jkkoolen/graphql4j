package eu.ludimus.graphql.fields;

public abstract class AbstractPrimitiveField extends AbstractField {
    @Override
    public boolean isPrimitive() {
        return true;
    }
}
