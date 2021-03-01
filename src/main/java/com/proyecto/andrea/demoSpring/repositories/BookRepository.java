package com.proyecto.andrea.demoSpring.repositories;


import com.proyecto.andrea.demoSpring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book AS b WHERE b.title LIKE %?1%", nativeQuery = true)
    public List<Book> getByTitle(String title);

    @Query(value = "SELECT * FROM book AS b WHERE b.editorial LIKE %?1%", nativeQuery = true)
    public List<Book> getByEditorial(String editorial);

    @Query(value = "SELECT * FROM author AS a " +
            "INNER JOIN author_write_book AS awb ON awb.id_book = b.id " +
            "INNER JOIN book AS b ON b.id=awb.id_author" +
            "WHERE a.id=?1", nativeQuery = true)
    public List<Book> getBookByAuthor(Long id_book);
}
