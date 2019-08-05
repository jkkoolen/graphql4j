package eu.ludimus.graphql.fields;

import eu.ludimus.graphql.fields.config.ScalarProperties;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class FieldFactoryTest {
    private static final String LOCAL_DATE_KEY = "LocalDate";
    private static final String TYPE = "Field";
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
        map.put(LOCAL_DATE_KEY, "java.time.LocalDate.now()");
        return map;
    }

    @Test
    public void createBooleanField() {
        BooleanField field = fieldFactory.createBooleanField();
        assertEquals("Boolean", field.getType());
        assertEquals("Boolean.TRUE", field.defaultValue());
    }

    @Test
    public void createIntegerField() {
        IntegerField field = fieldFactory.createIntegerField();
        assertEquals("Integer", field.getType());
        assertEquals("Integer.valueOf(1)", field.defaultValue());
    }

    @Test
    public void createStringField() {
        StringField field = fieldFactory.createStringField();
        assertEquals("String", field.getType());
        assertEquals("\"\"", field.defaultValue());
    }

    @Test
    public void createEnumField() {
        EnumField field = fieldFactory.createEnumField(TYPE);
        assertEquals(TYPE, field.getType());
        assertEquals("Field.values()[0]", field.defaultValue());
    }

    @Test
    public void createInputObjectField() {
        InputObjectField field = fieldFactory.createInputObjectField(TYPE);
        assertEquals(TYPE, field.getType());
        assertEquals("new Field()", field.defaultValue());
    }

    @Test
    public void createObjectField() {
        ObjectField field = fieldFactory.createObjectField(TYPE);
        assertEquals(TYPE, field.getType());
        assertEquals("new Field()", field.defaultValue());
    }

    @Test
    public void createListField() {
        ListField field = fieldFactory.createListField(new StringField());
        assertEquals("List<String>", field.getType());
        assertEquals("java.util.Collections.emptyList()", field.defaultValue());
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
        assertEquals("", field.defaultValue());
    }

    @Test
    public void createQueryField() {
        QueryField test = new QueryField("Test", scalarProperties);
        test.addMethod("method1", Arrays.asList(new FieldProperty(fieldFactory.createBooleanField(), "myBoolean")));
        test.addMethod("method2", Arrays.asList(
                new FieldProperty(fieldFactory.createIntegerField(), "myInteger"),
                new FieldProperty(fieldFactory.createStringField(), "myString")));
        assertEquals(2, test.getMethods().size());
        assertEquals(3, test.getProperties().size());
    }

    @Test(expected = RuntimeException.class)
    public void createQueryFieldThrowException() {
        QueryField test = new QueryField("Test", scalarProperties);
        test.defaultValue();
    }

    @Test
    public void createScalarField() {
        ScalarField field = fieldFactory.createScalarField(LOCAL_DATE_KEY);
        assertEquals(LOCAL_DATE_KEY, field.getType());
        assertEquals("java.time.LocalDate.now()", field.defaultValue());
    }
}
