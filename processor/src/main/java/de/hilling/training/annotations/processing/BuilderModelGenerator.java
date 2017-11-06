package de.hilling.training.annotations.processing;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Generate builder source code for given classes.
 * <p>
 * The classes should have been validated at this point.
 */
public class BuilderModelGenerator {
    private final RoundEnvironment  roundEnv;
    private final ProcessingContext context;

    public BuilderModelGenerator(RoundEnvironment roundEnv, ProcessingContext context) {
        this.roundEnv = roundEnv;
        this.context = context;
    }

    public void invoke() {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(ValueObject.class)) {
            new ModelGenerator(context, (TypeElement) annotatedElement).invoke();
        }
    }
}
