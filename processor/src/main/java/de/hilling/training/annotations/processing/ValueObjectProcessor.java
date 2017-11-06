package de.hilling.training.annotations.processing;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("de.hilling.training.annotations.processing.ValueObject")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ValueObjectProcessor extends AbstractProcessor {

    public static final String DEFAULT_ERROR_MESSAGE = "@ValueObject type must be a non-abstract, public class";
    private ProcessingContext     context;
    private int                   round;
    private Messager              messager;

    @Override
    public void init(ProcessingEnvironment env) {
        context = new ProcessingContext(env);
        messager = env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        context.clear();
        new AnnotationValidator(roundEnv, context).invoke();
        if (!context.failed()) {
            new BuilderModelGenerator(roundEnv, context).invoke();
        }
        if (!context.failed()) {
            for (BuilderModel builderModel : context.getBuilderModels()) {
                new BuilderCodeGenerator(builderModel, context).invoke();
            }
        }
        context.printErrors();
        round++;
        if (round > 20) {
            messager.printMessage(Diagnostic.Kind.ERROR, "possible infinite loop, giving up");
        }
        return false;
    }

}
