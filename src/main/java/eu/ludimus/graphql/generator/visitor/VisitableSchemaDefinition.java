package eu.ludimus.graphql.generator.visitor;

import graphql.language.SchemaDefinition;

public class VisitableSchemaDefinition implements DefinitionVisitable {
    private SchemaDefinition value;
    public VisitableSchemaDefinition(SchemaDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitSchemaDefinition(this.value);
    }
}
