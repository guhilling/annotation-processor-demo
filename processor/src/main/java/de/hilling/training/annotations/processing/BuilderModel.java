package de.hilling.training.annotations.processing;

import java.util.List;

import javax.lang.model.element.TypeElement;

// tag::declaration[]
class BuilderModel {
    final List<BeanAttribute> attributes;
    final TypeElement         annotatedElement;
// end::declaration[]

    BuilderModel(List<BeanAttribute> attributes, TypeElement annotatedElement) {
        this.attributes = attributes;
        this.annotatedElement = annotatedElement;
    }
}
