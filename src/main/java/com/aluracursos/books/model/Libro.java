package com.aluracursos.books.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDescargas;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------LIBRO-----------").append("\n");
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Autores: ").append("\n");
        for (Autor autores : autor) {
            sb.append(" ").append(autores.getNombre()).append("\n");
        }
        sb.append("Idioma: ").append(idioma).append("\n");
        sb.append("NumeroDeDescargas: ").append(numeroDeDescargas).append("\n");
        sb.append("----------------------------").append("\n");
        return sb.toString();
    }

    public Libro(){}
    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.idioma = Idioma.fromString(String.valueOf(datos.idioma().get(0)));
        this.numeroDeDescargas = datos.descargas();

    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        autor.forEach(t->t.setLibro(this));
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
