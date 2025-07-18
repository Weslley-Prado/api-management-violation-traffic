package br.com.agostini.api.managementviolationtraffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EnableJpaRepositories(basePackages = "br.com.agostini.agostini.adapters.outbound.postgresql")
@EntityScan(basePackages = "br.com.agostini.agostini.adapters.outbound.postgresql")
@SpringBootApplication
public class ApiManagementViolationTrafficApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiManagementViolationTrafficApplication.class, args);
	}

}
