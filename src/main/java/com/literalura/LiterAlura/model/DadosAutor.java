package com.literalura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor(@JsonAlias("name") String nome,
    @JsonAlias("birth_year") Integer anoAniversario,
    @JsonAlias("death_year") Integer anoMorte) {

    @Override
    public String toString() {
        return "\nAutor(a): " + nome +
                "\nAno de nascimento: " + anoAniversario +
                "\nAno de falecimento: " + anoMorte +"\n";
    }
}
