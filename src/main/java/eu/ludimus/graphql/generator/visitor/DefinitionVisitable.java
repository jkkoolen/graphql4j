package eu.ludimus.graphql.generator.visitor;

public interface DefinitionVisitable {
    void accept(DefinitionVisitor visitor);
}
