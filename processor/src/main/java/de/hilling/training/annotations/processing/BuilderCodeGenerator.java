package de.hilling.training.annotations.processing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class BuilderCodeGenerator {
    private final BuilderModel          builderModel;
    private final ProcessingEnvironment env;
    private final String                elementClassName;
    private List<MethodSpec> methods    = new ArrayList<>();
    private List<FieldSpec>  attributes = new ArrayList<>();
    private String builderClassName;
    private String packageName;

    public BuilderCodeGenerator(BuilderModel builderModel, ProcessingContext context) {
        this.builderModel = builderModel;
        env = context.getEnv();
        TypeElement annotatedElement = builderModel.annotatedElement;
        elementClassName = annotatedElement.getSimpleName().toString();
        builderClassName = elementClassName + "Builder";
        PackageElement packageElement = env.getElementUtils().getPackageOf(annotatedElement);
        packageName = packageElement.getQualifiedName().toString();
    }

    public void invoke() {
        builderModel.attributes.forEach(this::generateMethodSpec);
        generateBuilderMethod();
        TypeSpec builderType = TypeSpec.classBuilder(builderClassName)
                                       .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                       .addFields(attributes)
                                       .addMethods(methods)
                                       .build();

        JavaFile javaFile = JavaFile.builder(packageName, builderType)
                                    .indent("    ")
                                    .build();
        try {
            javaFile.writeTo(env.getFiler());
        } catch (IOException e) {
            throw new RuntimeException("unable to write class", e);
        }
    }

    private void generateBuilderMethod() {
        ClassName elementClassType = ClassName.get(packageName, elementClassName);
        MethodSpec.Builder buildMethod = MethodSpec.methodBuilder("build")
                                                  .addModifiers(Modifier.PUBLIC)
                                                  .returns(elementClassType)
                                                  .addStatement("$T result = new $T()", elementClassType, elementClassType);
        for (FieldSpec attribute : attributes) {
            buildMethod.addStatement("result.set" + toUpperFirstCharacter(attribute.name) + "(" + attribute.name + ")");
        }

        methods.add(buildMethod.addStatement("return result")
                              .build());

    }

    private void generateMethodSpec(BeanAttribute beanAttribute) {
        TypeName typeName = TypeName.get(beanAttribute.type);
        String attributeName = beanAttribute.name;
        FieldSpec builderAttribute = FieldSpec.builder(typeName, attributeName, Modifier.PRIVATE).build();
        attributes.add(builderAttribute);
        MethodSpec withMethod = MethodSpec.methodBuilder(withMethod(attributeName))
                                          .addModifiers(Modifier.PUBLIC)
                                          .returns(ClassName.get(packageName, builderClassName))
                                          .addParameter(typeName, attributeName)
                                          .addStatement("this." + attributeName + " = " + attributeName)
                                          .addStatement("return this")
                                          .build();
        methods.add(withMethod);
    }

    private String withMethod(String attributeName) {
        return "with" + toUpperFirstCharacter(attributeName);
    }

    private String toUpperFirstCharacter(String attributeName) {
        return attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
    }
}
