package com.proyecto.andrea.demoSpring.services;


import com.proyecto.andrea.demoSpring.exceptions.RecordNotFoundException;
import com.proyecto.andrea.demoSpring.model.Book;
import com.proyecto.andrea.demoSpring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> getAllBook(){
        List<Book> bookList = repository.findAll();
        if(bookList.size() > 0){
            return bookList;
        }else{
            return new ArrayList<>();
        }
    }

    public Book getBookById(Long id_book) throws RecordNotFoundException {
        Optional<Book> book = repository.findById(id_book);
        if (book.isPresent()){
            return book.get();
        }else{
            throw new RecordNotFoundException("No se ha encontrado ningún libro con esa id", id_book);
        }
    }

    public Book createBook(Book entity){
        entity= repository.save(entity);
        return entity;
    }

    public Book updateBook(Book entity) throws RecordNotFoundException{
        if(entity.getId_book() != null){
            Optional<Book> book = repository.findById(entity.getId_book());
            if(book.isPresent()){
                Book newEntity = book.get();
                newEntity.setTitle(entity.getTitle());
                newEntity.setIsbn(entity.getIsbn());
                newEntity.setEditorial(entity.getEditorial());

                newEntity = repository.save(newEntity);
                return newEntity;
            }else{
                throw new RecordNotFoundException("Libro no encontrado", entity.getId_book());
            }
        }else{
            throw new RecordNotFoundException("No se ha encontrado identificación de libro", 0l);
        }
    }

    public void deleteBookById(Long id_libro) throws RecordNotFoundException {
        Optional<Book> book = repository.findById(id_libro);
        if (book.isPresent()) {
            repository.deleteById(id_libro);
        } else {
            throw new RecordNotFoundException("No se ha encontrado ningún libro con esa id", id_libro);
        }
    }

    public List<Book> getBookByTitle(String title) throws RecordNotFoundException{
        List<Book> bookList = repository.getByTitle(title);
        if (bookList.size() > 0) {
            return bookList;
        } else {
            return new ArrayList<Book>();
        }
    }

    public List<Book> getBookByEditorial(String editorial) throws RecordNotFoundException{
        List<Book> list = repository.getByEditorial(editorial);
        if (list.size() > 0) {
            return list;
        } else {
            return new ArrayList<Book>();
        }
    }

    public List<Book> getBookByAuthor(Long id_book) throws RecordNotFoundException{
        List<Book> book = repository.getBookByAuthor(id_book);
        if (book != null){
            return book;
        } else {
            throw new RecordNotFoundException("No se ha encontrado ningún autor con esa id" , id_book);
        }
    }
}
