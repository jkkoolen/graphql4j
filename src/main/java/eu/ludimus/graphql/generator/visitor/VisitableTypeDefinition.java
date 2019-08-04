package eu.ludimus.graphql.generator.visitor;

import graphql.language.TypeDefinition;

public class VisitableTypeDefinition implements DefinitionVisitable {
    private TypeDefinition value;
    public VisitableTypeDefinition(TypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitTypeDefinition(this.value);
    }
}
