package main;

public abstract class Plano {

    private String nome;
    private int maxDispositivos;

    public Plano(String nome, int maxDispositivos){

        setMaxDispostivos(maxDispositivos);
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nome inválido: o nome não pode ser vazio.");
        }

        this.nome = nome;
    }

    public int getMaxDispostivos() {
        return maxDispositivos;
    }

    public void setMaxDispostivos(int maxDispositivos) {

        if (maxDispositivos <= 0) {
            throw new IllegalArgumentException(
                    "Número inválido: " + maxDispositivos +
                            ". O número de dispositivos deve ser maior que zero.");
        }

        this.maxDispositivos = maxDispositivos;
    }

    public abstract double calcularMensalidade();

    public abstract boolean temAnuncios();
}
