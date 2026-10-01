package main;

public class PodCast extends Conteudo {

    private String apresentador;

    private int numeroEpisodio;

    public PodCast(String titulo, int duracaoSegundos, String apresentador, int numeroEpisodio){
        
        super(titulo, duracaoSegundos);

        setApresentador(apresentador);
        setNumeroEpisodio(numeroEpisodio);
    }

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {

        if (apresentador == null || apresentador.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Apresentador inválido: o apresentador não pode ser vazio.");
        }

        this.apresentador = apresentador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {

        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException(
                    "Número inválido: " + numeroEpisodio +
                            ". O número do episódio deve ser maior que zero.");
        }

        this.numeroEpisodio = numeroEpisodio;
    }

    @Override
    public String getCreditos() {
        return "Episódio " + numeroEpisodio + " - " + apresentador;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + getCreditos();
    }
}
