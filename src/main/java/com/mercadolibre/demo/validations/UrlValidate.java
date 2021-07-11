package com.mercadolibre.demo.validations;


import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = URLValidator.class)
public @interface UrlValidate {
    /**
     *          * Requerido o no El predeterminado es requerido
     * @Return
     */
    boolean required() default true;
    /**
     *          * Mensaje de error de verificacion
     * @Return
     */
    String message() default "{la url ingresada es invalida}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
