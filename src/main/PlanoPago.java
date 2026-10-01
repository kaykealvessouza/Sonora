package main;

public abstract class PlanoPago extends Plano {

    private double precoMensal;

    public PlanoPago(String nome, int maxDispositivos, double precoMensal){
        super(nome, maxDispositivos);

        setPrecoMensal(precoMensal);
    }

    public double getPrecoMensal() {
        return precoMensal;
    }

    public void setPrecoMensal(double precoMensal) {

        if (precoMensal <= 0) {
            throw new IllegalArgumentException(
                    "Preço inválido: " + precoMensal +
                            ". O preço deve ser maior que zero.");
        }

        this.precoMensal = precoMensal;
    }

    @Override
    public boolean temAnuncios(){
        return false;
    }
}