package eu.ludimus.graphql.generator.visitor;

import graphql.language.ScalarTypeExtensionDefinition;

public class VisitableScalarTypeExtensionDefinition implements DefinitionVisitable {
    private ScalarTypeExtensionDefinition value;
    public VisitableScalarTypeExtensionDefinition(ScalarTypeExtensionDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitScalarTypeExtensionDefinition(this.value);
    }
}
