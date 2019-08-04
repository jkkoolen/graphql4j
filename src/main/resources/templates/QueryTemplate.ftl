package ${packageName};

${field.imports()}
${import}

public class ${field.type} implements GraphQLizer {
    private static ${field.type} INSTANCE = new ${field.type}();
    private GraphQLizer response;
    private String methodName;
    <#list field.properties as p>
    private ${p.field.type} ${p.name};
    </#list>

    <#list field.methods as m>
    public static ${field.type} ${m.name}(<#list m.arguments as a>${a.field.type} ${a.name}<#if a?is_last>)<#else>,</#if></#list> {
        INSTANCE.methodName = "${m.name}";
        <#list m.arguments as a>
        INSTANCE.${a.name} = ${a.name};
        </#list>
        return INSTANCE;
    }

    </#list>

    public ${field.type} withResponse(GraphQLizer response) {
        this.response = response;
        return this;
    }

    public String toQuery() {
    <#list field.methods as m>
        <#if m?is_last>else {<#else>if(methodName.equals("${m.name}")) {</#if>
            return methodName + "(" + (
            <#list m.arguments as p>
                <#if p.field.isList()>
                    (${p.name} == null ? "[]" : ${p.name}.stream().map(field -> <#if p.field.listField.isObject()>field.toQuery()<#elseif p.field.listField.isEnum()>field.name()<#else>field.toString()</#if>).collect(java.util.stream.Collectors.joining(",", "[", "]"))) + "," +
                <#else>
                    (${p.name} != null ? "${p.name}: \"" + ${p.name}<#if p.field.isObject()>.toQuery()</#if> + "\", " : "") + "," +
                </#if>
            </#list>
               ")").replace(",)", ")") + response.toQuery();
        }
    </#list>

    }

}
