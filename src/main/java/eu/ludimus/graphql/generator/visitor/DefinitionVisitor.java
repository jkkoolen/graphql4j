package eu.ludimus.graphql.generator.visitor;

import graphql.language.DirectiveDefinition;
import graphql.language.EnumTypeDefinition;
import graphql.language.EnumTypeExtensionDefinition;
import graphql.language.FragmentDefinition;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.InputObjectTypeExtensionDefinition;
import graphql.language.InterfaceTypeDefinition;
import graphql.language.InterfaceTypeExtensionDefinition;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ObjectTypeExtensionDefinition;
import graphql.language.OperationDefinition;
import graphql.language.ScalarTypeDefinition;
import graphql.language.ScalarTypeExtensionDefinition;
import graphql.language.SchemaDefinition;
import graphql.language.TypeDefinition;
import graphql.language.UnionTypeDefinition;
import graphql.language.UnionTypeExtensionDefinition;

public interface DefinitionVisitor {
    void visitDirectiveDefinition(DirectiveDefinition  definition);

    void visitEnumTypeDefinition(EnumTypeDefinition  definition);

    void visitEnumTypeExtensionDefinition(EnumTypeExtensionDefinition  definition);

    void visitFragmentDefinition(FragmentDefinition  definition);

    void visitInputObjectTypeDefinition(InputObjectTypeDefinition  definition);

    void visitInputObjectTypeExtensionDefinition(InputObjectTypeExtensionDefinition  definition);

    void visitInterfaceTypeDefinition(InterfaceTypeDefinition  definition);

    void visitInterfaceTypeExtensionDefinition(InterfaceTypeExtensionDefinition  definition);

    void visitObjectTypeDefinition(ObjectTypeDefinition  definition);

    void visitObjectTypeExtensionDefinition(ObjectTypeExtensionDefinition  definition);

    void visitOperationDefinition(OperationDefinition  definition);

    void visitScalarTypeDefinition(ScalarTypeDefinition  definition);

    void visitScalarTypeExtensionDefinition(ScalarTypeExtensionDefinition  definition);

    void visitSchemaDefinition(SchemaDefinition  definition);

    void visitTypeDefinition(TypeDefinition  definition);

    void visitUnionTypeDefinition(UnionTypeDefinition  definition);

    void visitUnionTypeExtensionDefinition(UnionTypeExtensionDefinition  definition);
}
