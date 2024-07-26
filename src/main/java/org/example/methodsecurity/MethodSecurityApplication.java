package org.example.methodsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class MethodSecurityApplication {

	@Bean
	BankAccountService bankAccountService() {
		return new BankAccountServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(MethodSecurityApplication.class, args);
	}

}
