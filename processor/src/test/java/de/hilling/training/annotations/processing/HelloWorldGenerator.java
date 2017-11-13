package de.hilling.training.annotations.processing;

import javax.lang.model.element.Modifier;

import org.junit.Test;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Simple example for generating code with squarepoet.
 */
public class HelloWorldGenerator {

    @Test
    public void generateHelloWorld() throws Exception {
        MethodSpec main = MethodSpec.methodBuilder("main")
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                    .returns(void.class)
                                    .addParameter(String[].class, "args")
                                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                                    .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                                      .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                      .addMethod(main)
                                      .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                                    .build();

        javaFile.writeTo(System.out);
    }
}
