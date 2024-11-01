package com.jiseguerra.reservation_system.config;

import com.jiseguerra.reservation_system.common.V1Controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author John Ivan Seguerra
 * @version $Id: WebConfig.java, 2024-11-01 3:02 PM $$
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/api/v1", HandlerTypePredicate.forAnnotation(V1Controller.class));
	}
}
