package eu.ludimus.graphql.generator.visitor;

import graphql.language.InputObjectTypeDefinition;

public class VisitableInputObjectTypeDefinition implements DefinitionVisitable {
    private InputObjectTypeDefinition value;
    public VisitableInputObjectTypeDefinition(InputObjectTypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitInputObjectTypeDefinition(this.value);
    }
}
