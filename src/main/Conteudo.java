package main;

public abstract class Conteudo {

    private int id;
    private static int contadorId = 0;

    private String titulo;

    private int duracaoSegundos;
    private int reproducoes;

    public Conteudo(String titulo, int duracaoSegundos) {

        setDuracaoSegundos(duracaoSegundos);

        setTitulo(titulo);

        this.id = ++contadorId;
        this.reproducoes = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Título inválido: o título não pode ser vazio.");
        }

        this.titulo = titulo;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {

        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException(
                    "Duração inválida: " + duracaoSegundos +
                            ". A duração deve ser maior que zero.");
        }

        this.duracaoSegundos = duracaoSegundos;
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public final void reproduzir() {
        this.reproducoes++;

        System.out.println("Reproduzindo: " + getTitulo() +
                " - " + getCreditos());
    }

    public String getDuracaoFormatada() {
        int segundos = this.duracaoSegundos;

        int minutos = segundos / 60;
        segundos = segundos % 60;

        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " (" + getDuracaoFormatada() + ")";
    }

    public abstract String getCreditos();
}
