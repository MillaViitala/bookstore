package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void findByTitleShouldReturnAuthor() {
		List<Book> books = repository.findByTitle("IT");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Stephen King");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Hengitys Virtaa", "Minna Martin", "555-777", 2012, 20, new Category("Lifestyle"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

}
