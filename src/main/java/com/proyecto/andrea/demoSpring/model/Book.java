package com.proyecto.andrea.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_book", unique = true, nullable = false)
    private  Long id_book;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "editorial", nullable = false)
    private String editorial;

    @ManyToMany (mappedBy = "books")
    private List<Author> authors;

    @JsonIgnoreProperties(value = {"book"}, allowSetters = true)
    @OneToMany( mappedBy = "book", cascade = {CascadeType.MERGE})
    private List<Exemplar> exemplars = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
        for(Author a: authors){
            List<Book> books = a.getBooks();
            if( books == null){
                books = new ArrayList<>();
            }
            if(!books.contains(this)){
                books.add(this);
            }
        }
    }

    public List<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<Exemplar> exemplars) {
        this.exemplars = exemplars;
        for( Exemplar e: exemplars){
            e.setBook(this);
        }
    }

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }
}


