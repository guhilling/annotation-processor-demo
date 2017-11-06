package de.hilling.training.annotations.processing;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

/**
 * Perform annotation validations.
 */
class AnnotationValidator {
    private       RoundEnvironment  roundEnv;
    private final ProcessingContext context;

    public AnnotationValidator(RoundEnvironment roundEnv, ProcessingContext context) {
        this.roundEnv = roundEnv;
        this.context = context;
    }

    public void invoke() {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(ValueObject.class)) {
            AnnotationMirror annotationMirror = getAnnotationMirror(annotatedElement);
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                context.error(annotatedElement, annotationMirror);
            }
            Set<Modifier> modifiers = annotatedElement.getModifiers();
            if (!modifiers.contains(Modifier.PUBLIC) || modifiers.contains(Modifier.ABSTRACT)) {
                context.error(annotatedElement, annotationMirror);
            }
        }
    }

    private AnnotationMirror getAnnotationMirror(Element annotatedElement) {
        return annotatedElement.getAnnotationMirrors().get(0);
    }
}
