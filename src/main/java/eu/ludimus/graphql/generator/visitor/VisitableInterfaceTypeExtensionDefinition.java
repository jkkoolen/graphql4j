package eu.ludimus.graphql.generator.visitor;

import graphql.language.InterfaceTypeExtensionDefinition;

public class VisitableInterfaceTypeExtensionDefinition implements DefinitionVisitable {
    private InterfaceTypeExtensionDefinition value;
    public VisitableInterfaceTypeExtensionDefinition(InterfaceTypeExtensionDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitInterfaceTypeExtensionDefinition(this.value);
    }
}
