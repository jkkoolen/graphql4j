package eu.ludimus.graphql.generator.visitor;

import graphql.language.ScalarTypeDefinition;

public class VisitableScalarTypeDefinition implements DefinitionVisitable {
    private ScalarTypeDefinition value;
    public VisitableScalarTypeDefinition(ScalarTypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitScalarTypeDefinition(this.value);
    }
}
