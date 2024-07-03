package br.com.challenge_alura_one_t6.AluraForum;

import br.com.challenge_alura_one_t6.AluraForum.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AluraForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraForumApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return  new IdWorker(1,1);
	}

}
