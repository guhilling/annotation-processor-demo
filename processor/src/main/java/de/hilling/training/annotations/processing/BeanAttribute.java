package de.hilling.training.annotations.processing;

import javax.lang.model.type.TypeMirror;

/**
 * Information about an attribute of a bean.
 */
public class BeanAttribute {
    public final String     name;
    public final TypeMirror type;

    public BeanAttribute(String name, TypeMirror type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ": " + type;
    }
}
