package main;

public final class PlanoGratuito extends Plano{

    public PlanoGratuito(){
        super("Gratuito", 1);
    }

    @Override
    public double calcularMensalidade(){
        return 0.0;
    };

    @Override
    public boolean temAnuncios(){
        return true;
    };
}
