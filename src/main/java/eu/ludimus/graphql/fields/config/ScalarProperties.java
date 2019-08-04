package eu.ludimus.graphql.fields.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties("scalar")
public class ScalarProperties {
    private Map<String, String> defaults;
    private Map<String, String> imports;

    public String defaultValue(String type) {
        if(defaults.containsKey(type)) {
            return defaults.get(type);
        }
        return "(" + type + ") new Object()";
    }

    public String getImportForType(String type) {
        if(imports.containsKey(type)) {
            return imports.get(type);
        }
        return "import ...;";
    }
}
