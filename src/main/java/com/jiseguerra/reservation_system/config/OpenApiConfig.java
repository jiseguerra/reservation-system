package com.jiseguerra.reservation_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author John Ivan Seguerra
 * @version $Id: OpenApiDoc.java, 2024-10-31 2:36 PM $$
 */
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		final String apiTitle = String.format("%s API", StringUtils.capitalize("restaurant reservation"));
		return new OpenAPI().info(new Info().title(apiTitle));
	}
}
