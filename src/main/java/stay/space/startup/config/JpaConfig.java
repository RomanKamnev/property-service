package stay.space.startup.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("stay.space.startup.persistence.repository")
@EntityScan("stay.space.startup.persistence.entity")
public class JpaConfig {

}
