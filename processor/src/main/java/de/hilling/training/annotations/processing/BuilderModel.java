package de.hilling.training.annotations.processing;

import java.util.List;

import javax.lang.model.element.TypeElement;

public class BuilderModel {
    public final List<BeanAttribute> attributes;
    public final TypeElement         annotatedElement;

    public BuilderModel(List<BeanAttribute> attributes, TypeElement annotatedElement) {
        this.attributes = attributes;
        this.annotatedElement = annotatedElement;
    }
}
