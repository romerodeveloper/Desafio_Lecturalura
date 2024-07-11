package com.aluracursos.books.principal;

import com.aluracursos.books.model.*;
import com.aluracursos.books.repository.LibroRepository;
import com.aluracursos.books.service.ConsumoApi;
import com.aluracursos.books.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Principal {
    private final String URL = "http://gutendex.com/books/?page=2";
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoApi consumoApi = new ConsumoApi();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository repository;
    public Principal(LibroRepository repositorio) {
        this.repository = repositorio;
    }

    public void muestraElMenu() {
        var opcion = -1;
            while (opcion != 0) {
            var menu = """
                        1 - Buscar Libro 
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en un determinado año
                        5 - Listar libros por idioma                                      
                        0 - Salir
                        """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    listasPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listasPorIdioma() {
        System.out.println("""
            Ingrese el idioma del libro de interes
            - en
            - es
            - fr
        """);
        String busqueda = teclado.nextLine();
        List<Libro> libroBuscado = repository.findByIdioma(Idioma.fromString(busqueda));
        libroBuscado.stream().forEach(System.out::println);

    }

    private void autoresVivos() {
        System.out.println("Ingrese año en que que consulte autores aun con vida");
        var busqueda = teclado.nextLine();
        List<Autor> lista = repository.findAutoresVivos(Integer.valueOf(busqueda));
        lista.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> lista = repository.findAutoresAll();
        lista.forEach(System.out::println);
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese nombre del libro");
        var busqueda = teclado.nextLine();
        var json = consumoApi.obtenerDatos("http://gutendex.com/books/"+"?search=" + busqueda.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(t -> t.titulo().toUpperCase().contains(busqueda.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            return libroBuscado.get();
        }
        return null;

    }
    private void buscarLibro() {
        var resultado = getDatosLibro();
        if( resultado != null ) {
            List<Libro> dato = repository.findByTitulo(resultado.titulo());
            System.out.println(resultado);
            if (dato.size() == 0){
                Libro libro = new Libro(resultado);
                List<Autor> autores = resultado.autor().stream()
                                .map(e -> new Autor(e.nombre(), e.añoNacimiento(), e.añoMuerte()))
                                .collect(Collectors.toList());
                libro.setAutor(autores);
                repository.save(libro);
                System.out.println(libro.toString());
            }else {
                System.out.println(dato.get(0).toString());
            }
        }
    }

    public void listarLibros(){
        List<Libro> libros = repository.findAll();
        libros.forEach(System.out::println);
    }


}