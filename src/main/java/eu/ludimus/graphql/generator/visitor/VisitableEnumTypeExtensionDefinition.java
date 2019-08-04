package eu.ludimus.graphql.generator.visitor;

import graphql.language.EnumTypeExtensionDefinition;

public class VisitableEnumTypeExtensionDefinition implements DefinitionVisitable {
    private EnumTypeExtensionDefinition value;
    public VisitableEnumTypeExtensionDefinition(EnumTypeExtensionDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitEnumTypeExtensionDefinition(this.value);
    }
}
