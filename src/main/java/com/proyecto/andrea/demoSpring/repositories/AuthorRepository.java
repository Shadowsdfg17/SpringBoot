package com.proyecto.andrea.demoSpring.repositories;


import com.proyecto.andrea.demoSpring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT * FROM author AS a WHERE a.name LIKE %?1%", nativeQuery = true)
    public List<Author> getByName(String name);

    @Query(value = "SELECT * FROM author AS a WHERE a.pseudonym LIKE %?1%", nativeQuery = true)
    public List<Author> getAuthorByPseudonym(String pseudonym);

    @Query(value = "SELECT * FROM book AS b " +
            "INNER JOIN author_write_book AS awb ON awb.id_book = b.id " +
            "INNER JOIN author a ON a.id=awb.id_author" +
            "WHERE b.id=?1", nativeQuery = true)
    public List<Author> getAuthorByBooks(Long id);

}
