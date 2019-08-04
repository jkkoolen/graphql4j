package eu.ludimus.graphql.generator.visitor;

import graphql.language.UnionTypeExtensionDefinition;


public class VisitableUnionTypeExtensionDefinition implements DefinitionVisitable {
    private UnionTypeExtensionDefinition value;

    public VisitableUnionTypeExtensionDefinition(UnionTypeExtensionDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitUnionTypeExtensionDefinition(this.value);
    }
}
