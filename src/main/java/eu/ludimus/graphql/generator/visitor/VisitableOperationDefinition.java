package eu.ludimus.graphql.generator.visitor;

import graphql.language.OperationDefinition;

public class VisitableOperationDefinition implements DefinitionVisitable {
    private OperationDefinition value;
    public VisitableOperationDefinition(OperationDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitOperationDefinition(this.value);
    }
}
