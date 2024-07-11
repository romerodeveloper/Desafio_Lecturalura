package com.aluracursos.books;

import com.aluracursos.books.principal.Principal;
import com.aluracursos.books.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repositorio;
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal ejecucion = new Principal(repositorio);
		ejecucion.muestraElMenu();
	}
}
