package eu.ludimus.graphql.generator.visitor;

import graphql.language.InterfaceTypeDefinition;

public class VisitableInterfaceTypeDefinition implements DefinitionVisitable {
    private InterfaceTypeDefinition value;
    public VisitableInterfaceTypeDefinition(InterfaceTypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitInterfaceTypeDefinition(this.value);
    }
}
