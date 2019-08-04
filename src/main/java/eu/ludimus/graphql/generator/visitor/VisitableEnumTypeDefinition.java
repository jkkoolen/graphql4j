package eu.ludimus.graphql.generator.visitor;

import graphql.language.EnumTypeDefinition;

public class VisitableEnumTypeDefinition implements DefinitionVisitable {
    private EnumTypeDefinition value;
    public VisitableEnumTypeDefinition(EnumTypeDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitEnumTypeDefinition(this.value);
    }
}
