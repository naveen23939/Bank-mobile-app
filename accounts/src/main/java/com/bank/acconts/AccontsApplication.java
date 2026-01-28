package com.bank.acconts;

import com.bank.acconts.dto.AccountContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditaware")
@EnableConfigurationProperties(value = {AccountContactInfoDto.class})
public class AccontsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccontsApplication.class, args);
	}

}
