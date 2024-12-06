package ru.coffe.libs.propertygenerator.processor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.coffe.libs.propertygenerator.annotation.PropertyField;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@SupportedAnnotationTypes("ru.coffe.libs.propertygenerator.annotation.PropertyField")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class PropertyFieldAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(PropertyField.class).stream().forEach(element -> {
            String className = element.getSimpleName().toString();
            String[] fields = element.getAnnotation(PropertyField.class).value();
            String propertyPrefix = element.getAnnotation(ConfigurationProperties.class).prefix();
            String classPackage = processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
            List<String> imports = new ArrayList<>(List.of(
                "import lombok.Data;",
                "import lombok.extern.slf4j.Slf4j;"
            ));
            boolean hasConfigurationProperty = classPackage != null;
            if (!hasConfigurationProperty) {
                imports.add("import org.springframework.boot.context.properties.ConfigurationProperties;");
            }


        });


        return false;
    }
}
