package de.hilling.training.annotations.processing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Diese Annotation markiert die annotatierte Klasse als "Value Object".
 * <p>
 * Der zugehörige Annotation Processor erzeugt aus dieser Klasse automatisch
 * einen Builder mit dem Basisnamen der Klasse und der Erweiterung <em>Builder</em>.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValueObject {
}
