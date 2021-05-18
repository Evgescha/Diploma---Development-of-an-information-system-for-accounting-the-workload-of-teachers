package com.hescha.teacher_workload_accounting;

import com.hescha.teacher_workload_accounting.storage.StorageProperties;
import com.hescha.teacher_workload_accounting.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TeacherWorkloadAccountingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherWorkloadAccountingApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
