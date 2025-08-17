package com.literalura.LiterAlura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo, 
        @JsonAlias("authors") List<DadosAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Long numeroDowloads) {

    @Override
    public String toString() {
        return "\n----- LIVRO -----\nTítulo: " + titulo +
                "\nAutor: " + autor.get(0).nome() +
                "\nIdioma: " + idiomas.get(0) +
                "\nNúmero de dowloads: " + numeroDowloads + 
                "\n---------------\n";
    }
}
