package ws.microservices.insurance.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    @ConfigurationProperties(prefix = "db.insurance")
    public DBConfigProperties dbConfigProperties() {
        return new DBConfigProperties();
    }

    @Bean
    public DataSource dataSource(DBConfigProperties dbConfigProperties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("insurance");
        dataSource.setJdbcUrl(dbConfigProperties.getJdbcUrl());
        dataSource.setUsername(dbConfigProperties.getUsername());
        dataSource.setPassword(dbConfigProperties.getPassword());
        return dataSource;
    }


    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/migration/dbchangelog.yaml");
        return liquibase;
    }


}
