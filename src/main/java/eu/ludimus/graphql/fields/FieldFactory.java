package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import org.springframework.stereotype.Service;

@Service
public class FieldFactory {
    private final ScalarProperties scalarProperties;

    public FieldFactory(ScalarProperties scalarProperties) {
        this.scalarProperties = scalarProperties;
    }

    public BooleanField createBooleanField() {
        return new BooleanField();
    }

    public IntegerField createIntegerField() {
        return new IntegerField();
    }

    public StringField createStringField() {
        return new StringField();
    }

    public EnumField createEnumField(String type) {
        return new EnumField(type);
    }

    public InputObjectField createInputObjectField(String name) {
        return new InputObjectField(name, scalarProperties);
    }

    public ObjectField createObjectField(String name) {
        return new ObjectField(name, scalarProperties);
    }

    public ListField createListField(Field field) {
        return new ListField(field);
    }

    public InterfaceField createInterfaceField(String name) {
        return new InterfaceField(name, scalarProperties);
    }

    public QueryField createQueryField(String name) {
        return new QueryField(name, scalarProperties);
    }

    public ScalarField createScalarField(String name) {
        return new ScalarField(name, scalarProperties);
    }
}
