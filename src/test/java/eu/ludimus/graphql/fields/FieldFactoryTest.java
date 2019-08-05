package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class FieldFactoryTest {
    private static final String LOCAL_DATE_KEY = "LocalDate";
    private static final String TYPE = "Field";
    private static final String THIS_A = "this.a";
    private FieldFactory fieldFactory;
    private ScalarProperties scalarProperties = createScalarProperties();

    @Before
    public void setUp() {
        fieldFactory = new FieldFactory(scalarProperties);
    }

    private ScalarProperties createScalarProperties() {
        ScalarProperties tmp = new ScalarProperties();
        tmp.setDefaults(createDefaults());
        tmp.setImports(createImports());
        return tmp;
    }

    private Map<String, String> createImports() {
        HashMap<String, String> map = new HashMap<>();
        map.put(LOCAL_DATE_KEY, "import java.time.LocalDate;");
        return map;
    }

    private Map<String, String> createDefaults() {
        HashMap<String, String> map = new HashMap<>();
        map.put(LOCAL_DATE_KEY, "${VARIABLE} = java.time.LocalDate.now()");
        return map;
    }

    @Test
    public void createBooleanField() {
        BooleanField field = fieldFactory.createBooleanField();
        assertEquals("Boolean", field.getType());
        assertEquals("this.a = Boolean.TRUE", field.defaultValue(THIS_A));
    }

    @Test
    public void createIntegerField() {
        IntegerField field = fieldFactory.createIntegerField();
        assertEquals("Integer", field.getType());
        assertEquals("this.a = Integer.valueOf(1)", field.defaultValue(THIS_A));
    }

    @Test
    public void createFloatField() {
        FloatField field = fieldFactory.createFloatField();
        assertEquals("Float", field.getType());
        assertEquals("this.a = Float.valueOf(1.5)", field.defaultValue(THIS_A));
    }

    @Test
    public void createIDField() {
        IDField field = fieldFactory.createIDField();
        assertEquals("UUID", field.getType());
        assertEquals("this.a = UUID.randomUUID()", field.defaultValue(THIS_A));
    }

    @Test
    public void createStringField() {
        StringField field = fieldFactory.createStringField();
        assertEquals("String", field.getType());
        assertEquals("this.a = \"\"", field.defaultValue(THIS_A));
    }

    @Test
    public void createEnumField() {
        EnumField field = fieldFactory.createEnumField(TYPE);
        assertEquals(TYPE, field.getType());
        assertEquals("this.a = Field.values()[0]", field.defaultValue(THIS_A));
    }

    @Test
    public void createInputObjectField() {
        InputObjectField field = fieldFactory.createInputObjectField(TYPE);
        assertEquals(TYPE, field.getType());
        assertEquals("this.a = new Field()", field.defaultValue(THIS_A));
    }

    @Test
    public void createObjectField() {
        ObjectField field = fieldFactory.createObjectField(TYPE);
        assertEquals(TYPE, field.getType());
        assertEquals("this.a = new Field()", field.defaultValue(THIS_A));
    }

    @Test
    public void createListField() {
        ListField field = fieldFactory.createListField(new StringField());
        assertEquals("List<String>", field.getType());
        assertEquals("this.a = java.util.Collections.emptyList()", field.defaultValue(THIS_A));
    }

    @Test
    public void createInterfaceField() {
        String name = "TestInterface";
        InterfaceField field = fieldFactory.createInterfaceField(name);
        assertEquals(name, field.getType());
    }

    @Test(expected = RuntimeException.class)
    public void createInterfaceFieldThrowException() {
        InterfaceField field = fieldFactory.createInterfaceField("TestInterface");
        assertEquals("", field.defaultValue(THIS_A));
    }

    @Test
    public void createQueryField() {
        QueryField test = new QueryField("Test", scalarProperties);
        test.addMethod("method1", Collections.singletonList(new FieldProperty(fieldFactory.createBooleanField(), "myBoolean")));
        test.addMethod("method2", Arrays.asList(
                new FieldProperty(fieldFactory.createIntegerField(), "myInteger"),
                new FieldProperty(fieldFactory.createStringField(), "myString")));
        assertEquals(2, test.getMethods().size());
        assertEquals(3, test.getProperties().size());
    }

    @Test(expected = RuntimeException.class)
    public void createQueryFieldThrowException() {
        QueryField test = new QueryField("Test", scalarProperties);
        test.defaultValue(THIS_A);
    }

    @Test
    public void createScalarField() {
        ScalarField field = fieldFactory.createScalarField(LOCAL_DATE_KEY);
        assertEquals(LOCAL_DATE_KEY, field.getType());
        assertEquals("this.a = java.time.LocalDate.now()", field.defaultValue(THIS_A));
    }
}
