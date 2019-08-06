package eu.ludimus.graphql.fields;

public interface Field {
    String getType();
    boolean isObject();
    boolean isInputObject();
    boolean isInterface();
    boolean isEnum();
    boolean isList();
    boolean isPrimitive();
    String defaultValue(String variableName);
}
