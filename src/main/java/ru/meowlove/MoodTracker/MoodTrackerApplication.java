package ru.meowlove.MoodTracker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MoodTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodTrackerApplication.class, args);
//		System.out.println();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// TODO - Сделать возврат ошибок (в нормальном виде) при валидации через json

}
