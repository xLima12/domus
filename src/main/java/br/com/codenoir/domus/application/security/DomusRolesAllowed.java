package br.com.codenoir.domus.application.security;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DomusRolesAllowed {

    String[] value() default {};

}
