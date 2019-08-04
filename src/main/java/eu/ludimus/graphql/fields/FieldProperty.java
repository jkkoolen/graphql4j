package eu.ludimus.graphql.fields;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "field")
public class FieldProperty {
    private Field field;
    private String name;
}
