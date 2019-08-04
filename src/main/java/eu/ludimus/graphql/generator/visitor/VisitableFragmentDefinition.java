package eu.ludimus.graphql.generator.visitor;

import graphql.language.FragmentDefinition;

public class VisitableFragmentDefinition implements DefinitionVisitable {
    private FragmentDefinition value;
    public VisitableFragmentDefinition(FragmentDefinition value) {
        this.value = value;
    }

    public void accept(DefinitionVisitor visitor) {
        visitor.visitFragmentDefinition(this.value);
    }
}
