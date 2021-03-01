package com.proyecto.andrea.demoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Exemplar")

public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exemplar", unique = true, nullable = false)
    private  Long id_exemplar;

    @Column(name = "location" , nullable = false)
    private String location;


    @JsonIgnoreProperties(value = {"exemplars"}, allowSetters = true)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_book", nullable = false)
    private Book book;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        List<Exemplar> exemplars = this.book.getExemplars();
        if(exemplars == null){
            exemplars = new ArrayList<>();

        }
        if(!exemplars.contains(this)){
            exemplars.add(this);
        }
    }

    public Long getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(Long id_exemplar) {
        this.id_exemplar = id_exemplar;
    }
}
