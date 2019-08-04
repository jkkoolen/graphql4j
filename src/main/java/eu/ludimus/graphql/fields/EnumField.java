package eu.ludimus.graphql.fields;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EnumField extends AbstractField {
    private List<String> values = new ArrayList();

    EnumField(String type) {
        setType(type);
    }

    public EnumField add(String value) {
        values.add(value);
        return this;
    }

    @Override
    public boolean isEnum() {
        return true;
    }

    @Override
    public String defaultValue() {
        return getType() + ".values()[0]";
    }
}
