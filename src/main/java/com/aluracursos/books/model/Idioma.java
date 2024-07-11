package com.aluracursos.books.model;

public enum Idioma {
    ESPAÑOL("es"),
    INGLES("en"),
    FRANCES("fr");
    private String idiomaGutendex;

    Idioma(String idiomaGutendex){
        this.idiomaGutendex = idiomaGutendex;
    }
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
