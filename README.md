# Desafio_Lecturalura

> Usando la libreria Gutendex se realizo un proyecto en consola que permite mapear esos datos del endpoint, 
> crear sus respectivos modelos para guardalos en base de datos y luego poder interactuar con las consultas
> JPQL Y Delivered Querys. Asi reforzando conocimientos y logrando cumplir con las especificaciones solicitadas.
> Cabe resaltar que se hizo el respectivo Modelo Service Repository para manejar un codigo limpio y estructurado.

### Se tiene un menu sencillo para el usuario.

```sh
1 - Buscar Libro
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
0 - Salir
```

### Y las siguientes son las consultas usadas para cada opcion


```java
    //Opcion 1
    List<Libro> findByTitulo(String titulo);

    //Opcion 3
    @Query("SELECT a FROM Autor a")
    List<Autor> findAutoresAll();

    //Opcion 4
    @Query("SELECT a FROM Autor a WHERE a.añoDeFallecimiento < :fecha")
    List<Autor> findAutoresVivos(Integer fecha);

    //Opcion 5
    List<Libro> findByIdioma(Idioma idioma);

    //Para la opcion 2 se uso findByAll() funcion por defecto del JPA
```
