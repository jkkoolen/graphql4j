package eu.ludimus.graphql.generator.visitor;

import graphql.language.DirectiveDefinition;

public class VisitableDirectiveDefinition implements DefinitionVisitable {
    private DirectiveDefinition value;
    public VisitableDirectiveDefinition(DirectiveDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitDirectiveDefinition(this.value);
    }
}
