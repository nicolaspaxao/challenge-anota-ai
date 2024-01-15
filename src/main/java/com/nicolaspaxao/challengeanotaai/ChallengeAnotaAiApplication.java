package com.nicolaspaxao.challengeanotaai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories()
public class ChallengeAnotaAiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChallengeAnotaAiApplication.class, args);
	}
}
