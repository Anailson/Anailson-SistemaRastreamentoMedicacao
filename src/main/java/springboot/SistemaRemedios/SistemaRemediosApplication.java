package springboot.SistemaRemedios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "springboot.remedios.model")  //CHAMA A CLASSE MODEL
@ComponentScan(basePackages =  {"springboot.*"})  //MAPEAR TODO OS PACOTES EVITANDO O ERRO - This application has no explicit mapping for /error, so you are seeing this as a fallbac
@EnableJpaRepositories(basePackages = {"springboot.remedios.repository"})  //PASSA O PACOTE AO CRIAR OS REPOSITORY
@EnableTransactionManagement
public class SistemaRemediosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaRemediosApplication.class, args);
	}

}
