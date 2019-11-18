package com.fishinwater.annotations_complier;

import com.fishinwater.annotations.BindPath;
import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * 注解处理器
 *
 * AutoService 为 Google 的三方包
 * @author fishinwater-1999
 * @version 2019-11-18
 */
@AutoService(Processor.class)
public class AnnotationCompiler extends AbstractProcessor {

    /**
     * 生成一个文件的对象
     */
    Filer filer;


    /**
     * 初始化方法，获得 Filer 对象
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    /**
     * 返回这个注解处理器要处理的注解
     *
     * 声明了我这个注解处理器，就处理这个 types 里面的注解
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        // 如果这个注解处理器要处理器他注解，如下所示加入即可
        // types.add(Override.class.getCanonicalName());
        return types;
    }

    /**
     * 声明我们注解处理器，支持的 Java 版本
     *
     * 直接用父类成员变量获得即可
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 注解处理器核心要做的事情
     *
     * 写文件、写代码
     * 首先要拿到传入的参数、内容
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 拿到该模块中（ 注意是当个模块，不是整个 app ），所有用到 BindPath 的 Element（ 节点   ）
        Set<? extends Element> mElementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        // 这个 Map 用来结构化数据（ 括号里传入的 Key 值 ）
        Map<String, String> map = new HashMap<>();
        for (Element element : mElementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            // 通过节点，拿到注解，来获得传入参数（ key 值 ）
            BindPath path = typeElement.getAnnotation(BindPath.class);
            // 获取 key
            String key = path.value();
            String activityName = typeElement.getQualifiedName().toString();
        }
        return false;
    }

}