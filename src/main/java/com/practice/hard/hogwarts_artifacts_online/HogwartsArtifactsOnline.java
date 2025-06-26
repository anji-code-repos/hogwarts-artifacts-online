package com.practice.hard.hogwarts_artifacts_online;

import com.practice.hard.hogwarts_artifacts_online.artifact.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HogwartsArtifactsOnline {

	public static void main(String[] args) {
		SpringApplication.run(HogwartsArtifactsOnline.class, args);
	}


	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1,1);
	}

}
