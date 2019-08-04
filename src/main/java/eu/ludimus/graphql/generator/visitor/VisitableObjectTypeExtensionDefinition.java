package eu.ludimus.graphql.generator.visitor;

import graphql.language.ObjectTypeExtensionDefinition;

public class VisitableObjectTypeExtensionDefinition implements DefinitionVisitable {
    private ObjectTypeExtensionDefinition value;
    public VisitableObjectTypeExtensionDefinition(ObjectTypeExtensionDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitObjectTypeExtensionDefinition(this.value);
    }
}
