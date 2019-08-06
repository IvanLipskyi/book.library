package com.brain.book.library;

import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GenreEnum;
import com.brain.book.library.service.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application {

	@Autowired
	private BookLibraryService bookLibraryService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void run(){
		addTestNovel();
		addTestFantasy();
		printAuthorBooks();
		printFilteredBooks();
	}

	private void addTestNovel() {
		Book book = new Book();
		book.setBooking("The song of ice and fire");
		book.setName("Winter is coming");
		book.setPagesCount(815);
		book.setReleaseDate(Year.of(2012));

		book.setGenre(GenreEnum.NOVEL);

		Author author = new Author();
		author.setName("George");
		author.setSecondName("Raymond");
		author.setLastName("Martin");
		author.setBirthDate(LocalDate.of(1948, 9, 20));
		book.setAuthor(author);
		bookLibraryService.addNewBook(book);
	}

	private void addTestFantasy(){
		Book book = new Book();
		book.setBooking("The lord of the rings");
		book.setName("Two towers");
		book.setPagesCount(645);
		book.setReleaseDate(Year.of(1948));

		book.setGenre(GenreEnum.FANTASY);

		Author author = bookLibraryService
				.findAuthorByFullName("George", "Raymond", "Martin");
		book.setAuthor(author);
		bookLibraryService.addNewBook(book);
	}

	private void printAuthorBooks(){
		List<Book> books = bookLibraryService
				.findBooksByAuthor("George", "Raymond", "Martin");
		books.forEach(System.out::println);
	}

	private void printFilteredBooks(){
		Set<GenreEnum> genres = new HashSet<>();
				genres.add(GenreEnum.FANTASY);
				genres.add(GenreEnum.NOVEL);

				List<Book>books = bookLibraryService
						.findBooksByGenres(genres);
				books.forEach(System.out::println);
	}
}
