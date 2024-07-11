package com.aluracursos.books.repository;


import com.aluracursos.books.model.Autor;
import com.aluracursos.books.model.Idioma;
import com.aluracursos.books.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a")
    List<Autor> findAutoresAll();

    @Query("SELECT a FROM Autor a WHERE a.añoDeFallecimiento < :fecha")
    List<Autor> findAutoresVivos(Integer fecha);

    List<Libro> findByIdioma(Idioma idioma);
}
