package com.techcrack.bookwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookWiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookWiseApplication.class, args);
	}

}
