package com.jiseguerra.reservation_system.common;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author John Ivan Seguerra
 * @version $Id: V1Controller.java, 2024-11-01 3:03 PM $$
 */
@RestController
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface V1Controller {
}
