package ${packageName};

import lombok.Builder;
import lombok.Getter;
${field.imports()}

@Getter
@Builder
public class ${field.type}${field.implement} {
<#list field.properties as p>
    private ${p.field.type} ${p.name};
</#list>
}
