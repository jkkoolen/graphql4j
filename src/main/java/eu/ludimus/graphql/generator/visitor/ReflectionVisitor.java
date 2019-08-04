package eu.ludimus.graphql.generator.visitor;

import graphql.language.Definition;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Slf4j
@Service
public class ReflectionVisitor implements DefinitionVisitor {
    public void visit(Definition definition) {
        String methodName = "visit" + definition.getClass().getName().substring(definition.getClass().getName().lastIndexOf('.') + 1);
        // Now we try to invoke the method visit<methodName>
        try {
            Method m = getClass().getMethod(methodName, new Class[]{definition.getClass()});
            m.invoke(this, new Object[]{definition});
        } catch (Exception e) {
            log.error("You try to visit a unknown type !", e);
        }
    }

    @Override
    public void visitDirectiveDefinition(DirectiveDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitEnumTypeDefinition(EnumTypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitEnumTypeExtensionDefinition(EnumTypeExtensionDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitFragmentDefinition(FragmentDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitInputObjectTypeDefinition(InputObjectTypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitInputObjectTypeExtensionDefinition(InputObjectTypeExtensionDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitInterfaceTypeDefinition(InterfaceTypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitInterfaceTypeExtensionDefinition(InterfaceTypeExtensionDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitObjectTypeDefinition(ObjectTypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitObjectTypeExtensionDefinition(ObjectTypeExtensionDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitOperationDefinition(OperationDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitScalarTypeDefinition(ScalarTypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitScalarTypeExtensionDefinition(ScalarTypeExtensionDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitSchemaDefinition(SchemaDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitTypeDefinition(TypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitUnionTypeDefinition(UnionTypeDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }

    @Override
    public void visitUnionTypeExtensionDefinition(UnionTypeExtensionDefinition definition) {
        log.error(definition.getClass().getName() + "Not implemented yet!");
    }
}
