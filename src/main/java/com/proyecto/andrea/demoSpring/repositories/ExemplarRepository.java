package com.proyecto.andrea.demoSpring.repositories;


import com.proyecto.andrea.demoSpring.model.Author;
import com.proyecto.andrea.demoSpring.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    @Query(value = "SELECT * FROM exemplar AS e WHERE e.location LIKE %?1%", nativeQuery = true)
    public List<Exemplar> getByExemplarLocation(String location);

    @Query(value = "SELECT * FROM exemplar AS e " +
            "INNER JOIN book AS b ON b.id = e.id_book" +
            "WHERE b.title=?1", nativeQuery = true)
    public List<Exemplar> getExemplarByBooks(Long id_exemplar);

}
