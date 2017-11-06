package de.hilling.training.annotations.processing;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

import javax.tools.JavaFileObject;

import org.junit.Test;

import com.google.testing.compile.JavaFileObjects;

public class ValueObjectProcessorTest {

    @Test
    public void validateValidObject() {
        assert_().about(javaSource())
                 .that(source(ValidValueObject.class))
                 .processedWith(new ValueObjectProcessor())
                 .compilesWithoutError();
    }

    @Test
    public void ignoreOtherObject() {
        assert_().about(javaSource())
                 .that(source(PlainObject.class))
                 .processedWith(new ValueObjectProcessor())
                 .compilesWithoutError();
    }

    @Test
    public void validateInvalidObject() {
        assert_().about(javaSource())
                 .that(source(InvalidValueObject.class))
                 .processedWith(new ValueObjectProcessor())
                 .failsToCompile()
                 .withErrorContaining(ValueObjectProcessor.DEFAULT_ERROR_MESSAGE);
    }

    @Test
    public void validateInvalidInterface() {
        assert_().about(javaSource())
                 .that(source(InvalidValueObjectInterface.class))
                 .processedWith(new ValueObjectProcessor())
                 .failsToCompile()
                 .withErrorContaining(ValueObjectProcessor.DEFAULT_ERROR_MESSAGE);
    }

    private JavaFileObject source(Class<?> clazz) {
        return JavaFileObjects.forResource(clazz.getCanonicalName().replace('.', '/') + ".java");
    }


}
