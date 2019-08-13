package com.brain.book.library.controller;

import com.brain.book.library.dao.BookRepository;
import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GenreEnum;
import com.brain.book.library.service.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;
    private final BookLibraryService bookLibraryService;

    public BookRestController(BookRepository bookRepository, BookLibraryService bookLibraryService) {
        this.bookRepository = bookRepository;
        this.bookLibraryService = bookLibraryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getBookList(){
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/by-author")
    public ResponseEntity<List<Book>> findBooks(@RequestParam("name") String name,
                                                @RequestParam("secondName") String secondName,
                                                @RequestParam("lastName") String lastName){
        Author author = bookLibraryService.findAuthorByFullName("George", "Raymond", "Martin");
        return new ResponseEntity<>(bookRepository.findBooksByAuthor(author), HttpStatus.OK);
    }

    @GetMapping ("{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeBook(@PathVariable Long id){
        bookRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }

    @GetMapping("/search/by-genre")
    public ResponseEntity<List<Book>> findBooksByGenre(@RequestParam("genre") Collection<GenreEnum> genres){
        List<Book> book = bookRepository.findBooksByGenreIn(genres);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
