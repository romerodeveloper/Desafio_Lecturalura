# Desafio_Lecturalura

>Usando la libreria Gutendex se realizo un proyecto en consola que permite mapear esos datos del endpoint, 
>crear sus respectivos modelos para guardalos en base de datos y luego poder interactuar con las consultas
>JPQL Y Delivered Querys. Asi reforzando conocimientos y logrando cumplir con las especificaciones solicitadas.
>Cabe resaltar que se hizo el respectivo Modelo Service Repository para manejar un codigo limpio y estructurado.

###Se tiene un menu sencillo para el usuario.

```sh
1 - Buscar Libro
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
0 - Salir
```

###Y las siguientes son las consultas usadas para cada opcion


```java
    List<Libro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a")
    List<Autor> findAutoresAll();

    @Query("SELECT a FROM Autor a WHERE a.añoDeFallecimiento < :fecha")
    List<Autor> findAutoresVivos(Integer fecha);

    List<Libro> findByIdioma(Idioma idioma);
```
