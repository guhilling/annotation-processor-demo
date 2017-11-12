package de.hilling.training.annotations.processing;

import javax.lang.model.type.TypeMirror;

/**
 * Information about an attribute of a bean.
 */
class BeanAttribute {
    final String     name;
    final TypeMirror type;

    BeanAttribute(String name, TypeMirror type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ": " + type;
    }
}
