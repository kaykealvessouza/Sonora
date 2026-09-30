package main;

import java.util.ArrayList;

public class Playlist {

    private String nome;
    private Usuario dono;

    private ArrayList<Musica> musicas;

    public Playlist(String nome, Usuario dono) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nome inválido: o nome da playlist não pode ser vazio.");
        }

        if (dono == null) {
            throw new IllegalArgumentException(
                    "Dono inválido: uma playlist precisa ter um dono.");
        }

        this.nome = nome;
        this.dono = dono;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

    public int getQuantidade() {
        return musicas.size();
    }

    public boolean adicionar(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException(
                    "Música inválida: não é possível adicionar uma música nula.");
        }

        musicas.add(musica);
        return true;
    }

    public Musica getNaPosicao(int indice) {

        if (indice <= 0 || indice > musicas.size()) {
            throw new IndexOutOfBoundsException(
                    "Índice inválido: " + indice +
                            ". A playlist possui " + musicas.size() + " música(s).");
        }

        return musicas.get(indice - 1);
    }

    public boolean removerNaPosicao(int indice) {
        if (indice <= 0 || indice > musicas.size()) {
            throw new IndexOutOfBoundsException(
                    "Índice inválido: " + indice +
                            ". A playlist possui " + musicas.size() + " música(s).");
        }

        musicas.remove(indice - 1);
        return true;
    }

    public int getPosicaoDaMusica(String titulo) {

        for (int i = 0; i < musicas.size(); i++) {
            if (musicas.get(i).getTitulo().equals(titulo)) {
                return i + 1;
            }
        }

        return -1;
    }

    public int getPosicaoDaMusica(int id) {

        for (int i = 0; i < musicas.size(); i++) {
            if (id == musicas.get(i).getId()) {
                return i + 1;
            }
        }

        return -1;
    }

    public int getPosicaoDaMusica(Musica musica) {

        for (int i = 0; i < musicas.size(); i++) {
            if (musica == musicas.get(i)) {
                return i + 1;
            }
        }

        return -1;
    }

    public int getDuracaoTotalSegundos() {
        int totalSegundos = 0;

        for (Musica musica : musicas) {
            totalSegundos += musica.getDuracaoSegundos();
        }

        return totalSegundos;
    }

    public void reproduzirTudo() {

        for (Musica musica : musicas) {
            musica.reproduzir();
            System.out.println("\nReproduzindo: " + musica.getTitulo());
        }
    }
}
