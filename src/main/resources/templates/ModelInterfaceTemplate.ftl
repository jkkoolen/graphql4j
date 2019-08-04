package ${packageName};

${field.imports()}

public interface ${field.type} {
<#list field.properties as p>
    ${p.field.type} get${p.name[0]?upper_case}${p.name?substring(1)}();
</#list>
}
