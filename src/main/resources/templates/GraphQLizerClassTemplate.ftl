package ${packageName};

${field.imports()}
${import}

public class ${field.type} implements GraphQLizer {

<#list field.properties as p>
    <#if p.field.isObject() || field.isInputObject()>
        private ${p.field.type} ${p.name};
        public ${field.type} include${p.name[0]?upper_case}${p.name?substring(1)}(${p.field.type} ${p.name}) {
        this.${p.name} = ${p.name};
        return this;
        }
    <#elseif p.field.isList()>
        private ${p.field.listField.type} ${p.name};
        public ${field.type} include${p.name[0]?upper_case}${p.name?substring(1)}(${p.field.listField.type} ${p.name}) {
        this.${p.name} = ${p.name};
        return this;
        }
    <#else>
        private ${p.field.type} ${p.name};
        public ${field.type} include${p.name[0]?upper_case}${p.name?substring(1)}() {
        ${p.field.defaultValue("this.${p.name}")};
        return this;
        }
    </#if>
</#list>
    private ${field.type}() {
        //empty
    }

    public static ${field.type} graphql() {
        return new ${field.type}();
    }

    @Override
    public String toQuery() {
        return ("{" +
        <#if field.isInputObject()>
            <#list field.properties as p>
                <#if p.field.isList()>
                    (${p.name} == null ? "[]" : ${p.name}.stream().map(field -> <#if p.field.listField.isObject()>field.toQuery()<#elseif p.field.listField.isEnum()>field.name()<#else>field.toString()</#if>).collect(java.util.stream.Collectors.joining(",", "[", "]"))) + "," +
                <#else>
                (${p.name} != null ? "${p.name}: \"" + ${p.name}<#if p.field.isObject()>.toQuery()</#if> + "\", " : "") +
                </#if>
            </#list>
        <#else>
            <#list field.properties as p>
                (${p.name} == null ? "" : "${p.name} " <#if p.field.isObject() || (p.field.isList() && p.field.listField.isObject())> + ${p.name}.toQuery()</#if>) +
            </#list>
        </#if>
        "}").replace(", }", "}");
    }
}
