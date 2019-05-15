package org.annotationprocessor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author: zyr
 * @date: 2019/5/9
 * @description: 自定义注解处理器
 */
public class MyProcessor extends AbstractProcessor {
    /**
     * 注意,每一个注解处理器类必须要有一个空的构造函数
     */
    protected MyProcessor() {
        super();
    }

    @Override public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    /**
     * jdk7之后也可以使用注解方式
     */
    @Override public Set<String> getSupportedAnnotationTypes() {
        // todo
        return super.getSupportedAnnotationTypes();
    }

    /**
     * jdk7之后也可以使用注解方式
     */
    @Override public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation,
            ExecutableElement member, String userText) {
        return super.getCompletions(element, annotation, member, userText);
    }

    @Override protected synchronized boolean isInitialized() {
        return super.isInitialized();
    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
