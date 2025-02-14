package stay.space.startup.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class LiquibaseConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @LiquibaseDataSource
    @Bean
    public DataSource liquibaseDataSource() {
        DataSource ds = DataSourceBuilder.create()
                .username("postgres")
                .password("postgres")
                .url("jdbc:postgresql://localhost:5432/real_estate_db")
                .build();
        if (ds instanceof HikariDataSource) {
            ((HikariDataSource) ds).setMaximumPoolSize(2);
            ((HikariDataSource) ds).setPoolName("Liquibase Pool");
        }
        return ds;
    }
}
