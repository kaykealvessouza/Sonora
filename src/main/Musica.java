package main;
public class Musica {

    private int id;
    private static int contadorId = 1;

    private String titulo;
    private String artista;

    private int duracaoSegundos;
    private int reproducoes;

    public Musica(String titulo, String artista, int duracaoSegundos) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Título inválido: o título não pode ser vazio.");
        }

        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Artista inválido: o artista não pode ser vazio.");
        }

        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException(
                    "Duração inválida: " + duracaoSegundos +
                            ". A duração deve ser maior que zero.");
        }

        this.id = contadorId;
        contadorId++;

        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.reproducoes = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public void reproduzir() {
        this.reproducoes++;
    }

    public String getDuracaoFormatada() {
        int segundos = this.duracaoSegundos;

        int minutos = segundos / 60;
        segundos = segundos % 60;

        return String.format("%02d:%02d", minutos, segundos);
    }
}