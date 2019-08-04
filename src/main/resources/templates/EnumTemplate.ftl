package ${packageName};

public enum ${field.type} {
<#list field.values as value>
    ${value}<#if value?is_last>;<#else>,</#if>
</#list>
}
