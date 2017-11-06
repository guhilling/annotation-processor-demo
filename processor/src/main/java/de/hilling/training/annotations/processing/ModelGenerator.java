package de.hilling.training.annotations.processing;


import java.util.List;

import javax.lang.model.element.TypeElement;

public class ModelGenerator {
    private final ProcessingContext     context;
    private final TypeElement           annotatedElement;

    public ModelGenerator(ProcessingContext context, TypeElement annotatedElement) {
        this.context = context;
        this.annotatedElement = annotatedElement;
    }

    public void invoke() {
        List<BeanAttribute> attributes = context.getAttributes(annotatedElement);
        context.getBuilderModels().add(new BuilderModel(attributes, annotatedElement));
    }
}
