package com.example.bookstore;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Save books");
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Science Fiction"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Horror"));

			brepository.save(
					new Book("IT", "Stephen King", "1234-1234", 1986, 18, crepository.findByName("Horror").get(0)));
			brepository.save(new Book("Ihmisen lyhyt historia", "Yuval Noah Harari", "2346-3456", 2011, 15,
					crepository.findByName("History").get(0)));
			
			User user1 = new User("user","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("Get all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
