package org.vj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EntityScan("org.vj.entity")
@ComponentScan({"org.vj.service", "org.vj.router", "org.vj.handler"})
@EnableR2dbcRepositories("org.vj.repository")
public class SpringWebfluxCurdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxCurdApplication.class, args);
	}

}
