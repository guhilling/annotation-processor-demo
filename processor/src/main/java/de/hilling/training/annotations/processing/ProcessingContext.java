package de.hilling.training.annotations.processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

/**
 * Speichert den internen Status des Annotation Processors.
 */
public class ProcessingContext {
    private final ProcessingEnvironment env;
    private final Messager              messager;
    private Map<Element, ErrorContext> errors        = new HashMap<>();
    private List<BuilderModel>         builderModels = new ArrayList<>();

    public ProcessingContext(ProcessingEnvironment env) {
        this.env = env;
        messager = env.getMessager();
    }

    public void error(Element annotatedElement, AnnotationMirror annotationMirror) {
        errors.put(annotatedElement, new ErrorContext(ValueObjectProcessor.DEFAULT_ERROR_MESSAGE, annotationMirror, null));
    }

    public void printErrors() {
        for (Map.Entry<Element, ErrorContext> entry : errors.entrySet()) {
            ErrorContext errorContext = entry.getValue();
            messager.printMessage(Diagnostic.Kind.ERROR,
                                  errorContext.message,
                                  entry.getKey(),
                                  errorContext.annotationMirror,
                                  errorContext.annotationValue);
        }

    }

    public ProcessingEnvironment getEnv() {
        return env;
    }

    public List<BeanAttribute> getAttributes(Element annotatedElement) {
        ArrayList<BeanAttribute> attributes = new ArrayList<>();
        List<? extends Element> elements = annotatedElement.getEnclosedElements();
        elements.stream().filter(element -> element.getKind() == ElementKind.METHOD).forEach(element -> {
            ExecutableElement executableElement = (ExecutableElement) element;
            final String name = element.getSimpleName().toString();
            if (name.startsWith("get")) {
                TypeMirror returnType = executableElement.getReturnType();
                attributes.add(new BeanAttribute(attributeName(name), returnType));
            }
        });
        return attributes;
    }

    private String attributeName(String name) {
        return name.substring(3, 4).toLowerCase() + name.substring(4);
    }

    /**
     * @return true, if any error occured.
     */
    public boolean failed() {
        return errors.size() > 0;
    }

    public List<BuilderModel> getBuilderModels() {
        return builderModels;
    }

    public void clear() {
        builderModels.clear();
        errors.clear();
    }

    private static class ErrorContext {
        public final CharSequence     message;
        public final AnnotationMirror annotationMirror;
        public final AnnotationValue  annotationValue;

        private ErrorContext(CharSequence message, AnnotationMirror annotationMirror, AnnotationValue annotationValue) {
            this.message = message;
            this.annotationMirror = annotationMirror;
            this.annotationValue = annotationValue;
        }
    }
}
