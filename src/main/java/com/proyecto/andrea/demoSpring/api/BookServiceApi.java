package com.proyecto.andrea.demoSpring.api;


import com.proyecto.andrea.demoSpring.exceptions.RecordNotFoundException;
import com.proyecto.andrea.demoSpring.model.Book;
import com.proyecto.andrea.demoSpring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookServiceApi {

    @Autowired
    BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> list = service.getAllBook();

        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id_book}")
    public  ResponseEntity<Book> getBookById(@PathVariable("id_book") Long id_book) throws RecordNotFoundException {
        Book entity = service.getBookById(id_book);

        return  new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("title") String title) throws RecordNotFoundException {
        List<Book> list = service.getBookByTitle(title);

        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/editorial/{editorial}")
    public ResponseEntity<List<Book>> getBookByEditorial(@PathVariable("editorial") String editorial) throws RecordNotFoundException {
        List<Book> list = service.getBookByEditorial(editorial);

        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/book_write/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("author") Long id_book) throws RecordNotFoundException {
        List<Book> list = service.getBookByAuthor(id_book);

        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book myBook) {
        Book entity = service.createBook(myBook);

        return new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book myBook) throws RecordNotFoundException {
        Book entity = service.updateBook(myBook);

        return new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id_book}")
    public HttpStatus deleteBookById(@PathVariable("id_book") Long id_book) throws RecordNotFoundException {
        service.deleteBookById(id_book);

        return HttpStatus.FORBIDDEN;
    }


}
