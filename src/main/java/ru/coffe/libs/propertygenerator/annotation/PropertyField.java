package ru.coffe.libs.propertygenerator.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface PropertyField {
    String[] propertyFields();

    @AliasFor("propertyFields")
    String[] value();
}
