package main;

public class PlanoFamilia extends PlanoPago {

    private int quantidadeMembros;

    public PlanoFamilia(double precoMensal, int quantidadeMembros) {
        super("Familia", 6, precoMensal);

        setQuantidadeMembros(quantidadeMembros);
    }

    public int getQuantidadeMembros() {
        return quantidadeMembros;
    }

    public void setQuantidadeMembros(int quantidadeMembros) {

        if (quantidadeMembros < 1 || quantidadeMembros > 6) {
            throw new IllegalArgumentException(
                    "Quantidade inválida: " + quantidadeMembros +
                            ". A quantidade de membros deve ser entre 1 e 6.");
        }

        this.quantidadeMembros = quantidadeMembros;
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal() + 4.90 * (quantidadeMembros - 1);
    }

    public String resumoFamilia() {
        return resumo() + ", " + quantidadeMembros + " membro(s)";
    }
}
