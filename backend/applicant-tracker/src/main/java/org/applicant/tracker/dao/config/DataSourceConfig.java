package org.applicant.tracker.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /** Datasource для Hikari описан в application.spring.datasource */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource hikariPostgresDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
