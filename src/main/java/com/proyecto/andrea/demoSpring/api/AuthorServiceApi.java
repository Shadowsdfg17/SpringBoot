package com.proyecto.andrea.demoSpring.api;


import com.proyecto.andrea.demoSpring.exceptions.RecordNotFoundException;
import com.proyecto.andrea.demoSpring.model.Author;
import com.proyecto.andrea.demoSpring.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorServiceApi {

    @Autowired
    AuthorService service;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthor(){
        List<Author> list = service.getAllAuthor();

        return new ResponseEntity<List<Author>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id_author}")
    public  ResponseEntity<Author> getAuthorById(@PathVariable("id_author") Long id_author) throws RecordNotFoundException {
        Author entity = service.getAuthorById(id_author);

        return  new ResponseEntity<Author>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Author>> getAuthorByName(@PathVariable("name") String name) throws RecordNotFoundException {
        List<Author> list = service.getAuthorByName(name);

        return new ResponseEntity<List<Author>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/pseudonym/{pseudonym}")
    public ResponseEntity<List<Author>> getAuthorByPseudonym(@PathVariable("pseudonym") String pseudonym) throws RecordNotFoundException {
        List<Author> list = service.getAuthorByPseudonym(pseudonym);

        return new ResponseEntity<List<Author>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/book_write/{book}")
    public ResponseEntity<List<Author>> getAuthorByBooks(@PathVariable("book") Long id_author) throws RecordNotFoundException {
        List<Author> list = service.getAuthorByBooks(id_author);

        return new ResponseEntity<List<Author>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author myAuthor) {
        Author entity = service.createAuthor(myAuthor);

        return new ResponseEntity<Author>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Author> updateAuthor(@Valid @RequestBody Author myAuthor) throws RecordNotFoundException {
        Author entity = service.updateAuthor(myAuthor);

        return new ResponseEntity<Author>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id_author}")
    public HttpStatus deleteAuthorById(@PathVariable("id_author") Long id_author) throws RecordNotFoundException {
        service.deleteAuthorById(id_author);

        return HttpStatus.FORBIDDEN;
    }





}
