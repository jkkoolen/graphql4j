package eu.ludimus.graphql.generator.visitor;

import graphql.language.ObjectTypeDefinition;

public class VisitableObjectTypeDefinition implements DefinitionVisitable {
    private ObjectTypeDefinition value;
    public VisitableObjectTypeDefinition(ObjectTypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitObjectTypeDefinition(this.value);
    }
}
