package com.proyecto.andrea.demoSpring.services;


import com.proyecto.andrea.demoSpring.exceptions.RecordNotFoundException;
import com.proyecto.andrea.demoSpring.model.Author;
import com.proyecto.andrea.demoSpring.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public List<Author> getAllAuthor(){
        List<Author> authorList = repository.findAll();
        if( authorList.size() > 0){
            return authorList;
        }else{
            return new ArrayList<>();
        }
    }

    public Author getAuthorById(Long id_author) throws RecordNotFoundException {
        Optional<Author> author = repository.findById(id_author);

        if(author.isPresent()) {
            return author.get();
        }else{
            throw new RecordNotFoundException("No se ha encontrado ningún autor con esa id", id_author);
        }
    }

    public  Author createAuthor(Author entity){
        entity = repository.save(entity);
        return entity;
    }

    public Author updateAuthor(Author entity) throws RecordNotFoundException{
        if(entity.getId_author() != null){
            Optional<Author> author = repository.findById(entity.getId_author());
            if(author.isPresent()){
                Author newEntity = author.get();
                newEntity.setName(entity.getName());
                newEntity.setPseudonym(entity.getPseudonym());
                newEntity.setBooks(entity.getBooks());

                newEntity = repository.save(newEntity);
                return newEntity;
            }else{
                throw new RecordNotFoundException("Author no encontrado", entity.getId_author());
            }
        }else{
            throw new RecordNotFoundException("No se ha encontrado identificación de author", 0l);
        }
    }


    public void deleteAuthorById(Long id) throws RecordNotFoundException {
        Optional<Author> author = repository.findById(id);
        if (author.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No se ha encontrado ningún autor con esa id", id);
        }
    }

    public List<Author> getAuthorByName(String name) throws RecordNotFoundException{
        List<Author> authorsList = repository.getByName(name);
        if (authorsList.size() > 0) {
            return authorsList;
        } else {
            return new ArrayList<Author>();
        }
    }

    public List<Author> getAuthorByPseudonym(String pseudonym) throws RecordNotFoundException{
        List<Author> list = repository.getAuthorByPseudonym(pseudonym);
        if (list.size() > 0) {
            return list;
        } else {
            return new ArrayList<Author>();
        }
    }

    public List<Author> getAuthorByBooks(Long id_author) throws RecordNotFoundException{
        List<Author> authors = repository.getAuthorByBooks(id_author);

        if (authors != null){
            return authors;
        } else {
            throw new RecordNotFoundException("No se ha encontrado ningún libro con esa id" , id_author);
        }
    }





}
