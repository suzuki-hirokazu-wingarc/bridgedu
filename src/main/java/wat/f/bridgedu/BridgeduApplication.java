package wat.f.bridgedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "wat.f.bridgedu.domain.repository")
public class BridgeduApplication {

	public static void main(String[] args) {
		SpringApplication.run(BridgeduApplication.class, args);
	}

}
