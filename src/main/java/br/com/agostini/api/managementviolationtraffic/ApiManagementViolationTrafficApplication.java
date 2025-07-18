package br.com.agostini.api.managementviolationtraffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "br.com.agostini.api.managementviolationtraffic.adapter.out.repository.postgresql")
@ComponentScan(basePackages = "br.com.agostini.api.managementviolationtraffic")
@EntityScan(basePackages = "br.com.agostini.api.managementviolationtraffic.application.domain.model")
@SpringBootApplication
public class ApiManagementViolationTrafficApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiManagementViolationTrafficApplication.class, args);
	}

}
