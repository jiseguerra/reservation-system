package com.jiseguerra.reservation_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

/**
 * @author John Ivan Seguerra
 * @version $Id: AppConfig.java, 2024-10-31 2:24 PM $$
 */
//@Configuration
//public class AppConfig {
//
//	@Value("${spring.datasource.url}")
//    private String url;
//
//    @Bean
//    public DataSource dataSource() {
//        SQLiteDataSource dataSource = new SQLiteDataSource();
//        dataSource.setUrl(url);
//        return dataSource;
//    }
//}
