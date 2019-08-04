package eu.ludimus.graphql.generator.visitor;

import graphql.language.UnionTypeDefinition;

public class VisitableUnionTypeDefinition implements DefinitionVisitable {
    private UnionTypeDefinition value;
    public VisitableUnionTypeDefinition(UnionTypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitUnionTypeDefinition(this.value);
    }
}
