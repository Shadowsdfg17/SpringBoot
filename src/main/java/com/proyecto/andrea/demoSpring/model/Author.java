package com.proyecto.andrea.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Author")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_author", unique = true, nullable = false)
    private  Long id_author;

    @Column(name = "name")
    private String name;

    @Column(name = "pseudonym")
    private String pseudonym;

    @JsonIgnoreProperties(value = "authors")
    @ManyToMany (cascade = CascadeType.MERGE)
    @JoinTable
    private List<Book> books;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> Books) {
        this.books = Books;
        for(Book a: Books){
            List<Author> Authors = a.getAuthors();
            if( Authors == null){
                Authors = new ArrayList<>();
            }
            if(!Authors.contains(this)){
                Authors.add(this);
            }
        }

        }

    public Long getId_author() {
        return id_author;
    }

    public void setId_author(Long id_author) {
        this.id_author = id_author;
    }
}

