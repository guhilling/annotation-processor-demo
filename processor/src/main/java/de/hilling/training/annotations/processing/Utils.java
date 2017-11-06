package de.hilling.training.annotations.processing;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;

public class Utils {
    /**
     * Find the correct annotation mirror, in our case the one referring to {@link ValueObject}.
     * @param annotatedElement
     * @return AnnotationMirror
     */
    static AnnotationMirror getAnnotationMirror(Element annotatedElement) {
        /*
        for (AnnotationMirror annotationMirror : annotatedElement.getAnnotationMirrors()) {
            if(annotationMirror.getAnnotationType().getAnnotation(ValueObject.class) != null){
                return annotationMirror;
            }
        }
        */
        return annotatedElement.getAnnotationMirrors().get(0);
    }
}
