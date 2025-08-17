package com.literalura.LiterAlura.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.literalura.LiterAlura.model.DadosAutor;
import com.literalura.LiterAlura.model.DadosBusca;
import com.literalura.LiterAlura.model.DadosLivro;
import com.literalura.LiterAlura.service.ConsumoAPI;
import com.literalura.LiterAlura.service.ConverteDados;

public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/";
    private final String SEARCH = "?search=";
    private List<DadosLivro> listaDeLivrosBuscados = new ArrayList<>();

    public void exibirMenu(){
        var loop = true;
        while(loop){
            System.out.println("\n**** MENU ****\n" + 
            "1- buscar livro pelo título\n"+
            "2- listar livros registrados\n" + 
            "3- listar autores registrados\n" + 
            "4- listar autores vivos em um determinado ano\n"+
            "5- listar livros em um determinado idioma\n" +
            "0- sair\n");
            var resposta = sc.nextInt();
            sc.nextLine();

            chamarOpcao(resposta);
        }
    }

    private void chamarOpcao(int resposta) {
        switch (resposta) {
            case 1:
                System.out.print("\nQual título deseja: ");
                var titulo = sc.nextLine();
                buscarLivroPeloTitulo(titulo);
                break;
            case 2:
                System.out.println("\n=== Livros já registrados ===\n");
                listarLivrosRegistrados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                System.out.print("\nQue ano deseja buscar: ");
                var ano = sc.nextInt();
                listarAutoresVivosNumAno(ano);
                break;
            case 5:
                listarLivrosEmUmIdioma();
                break;
            case 0:
                System.out.println("\nSaindo...");
                break;
            default:
                break;
        }
    }

    private void buscarLivroPeloTitulo(String titulo) {
        titulo = titulo.trim();
        titulo=titulo.replace(" ", "%20");
        var json = consumo.obterDados(ENDERECO+SEARCH+titulo);
        var dadosDaBusca = conversor.obterDados(json, DadosBusca.class);
        if (dadosDaBusca != null && !dadosDaBusca.resultados().isEmpty()) {
            var livro = dadosDaBusca.resultados().get(0);
            System.out.println("\n"+livro);
            listaDeLivrosBuscados.add(livro);
        } else {
            System.out.println("\nNenhum livro encontrado com o título: " + titulo);
        }
    }

    private void listarLivrosRegistrados() {
        listaDeLivrosBuscados.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        for (DadosLivro l : listaDeLivrosBuscados) {
            System.out.println(l.autor().toString());
        }
    }

    private void listarAutoresVivosNumAno(int ano) {
        List<DadosAutor> autoresVivos = new ArrayList<>();
        for (DadosLivro l : listaDeLivrosBuscados) {
            int morte = l.autor().get(0).anoMorte();
            int nasc = l.autor().get(0).anoAniversario();
            if(ano<morte && ano>nasc)
                autoresVivos.add(l.autor().get(0));
        }
        if(!autoresVivos.isEmpty())
            autoresVivos.forEach(System.out::println);
        else
            System.out.println("Nenhum autor buscado estava vivo em " + ano);
    }

    private void listarLivrosEmUmIdioma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarLivrosEmUmIdioma'");
    }
}
