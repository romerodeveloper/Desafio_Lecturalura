package com.aluracursos.books.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer añoDeNacimiento;
    private Integer añoDeFallecimiento;
    @ManyToOne
    private Libro libro;

    public Autor(){}

    public Autor(String nombre, Integer nacimiento, Integer muerte) {
        this.nombre = nombre;
        this.añoDeNacimiento = nacimiento;
        this.añoDeFallecimiento = muerte;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(Integer añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public Integer getAñoDeFallecimiento() {
        return añoDeFallecimiento;
    }

    public void setAñoDeFallecimiento(Integer añoDeFallecimiento) {
        this.añoDeFallecimiento = añoDeFallecimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return  "-----------------------------------------" + "\n" +
                "nombre: " + nombre + "\n" +
                "Nacimiento: " + añoDeNacimiento + "\n" +
                "Fallecimiento: " + añoDeFallecimiento + "\n" +
                "Libros: " + libro.getTitulo() + "\n" +
                "-----------------------------------------";
    }

}
