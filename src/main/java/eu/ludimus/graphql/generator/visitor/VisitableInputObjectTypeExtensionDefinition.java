package eu.ludimus.graphql.generator.visitor;

import graphql.language.InputObjectTypeExtensionDefinition;

public class VisitableInputObjectTypeExtensionDefinition implements DefinitionVisitable {
    private InputObjectTypeExtensionDefinition value;
    public VisitableInputObjectTypeExtensionDefinition(InputObjectTypeExtensionDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitInputObjectTypeExtensionDefinition(this.value);
    }
}
