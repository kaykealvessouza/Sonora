package main;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private static int contadorId = 1;

    private String nome;
    private String email;

    private Plano plano;

    private ArrayList<Usuario> seguindo;

    public Usuario(String nome, String email) {

        setNome(nome);

        setEmail(email);

        this.id = contadorId;
        contadorId++;

        this.seguindo = new ArrayList<>();

        this.plano = new PlanoGratuito();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nome inválido: o nome não pode ser vazio.");
        }

        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "E-mail inválido: o e-mail não pode ser vazio.");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException(
                    "E-mail inválido: o e-mail deve conter @.");
        }

        this.email = email;
    }

    public void seguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException(
                    "Usuário inválido: não é possível seguir um usuário nulo.");
        }

        if (this == outro) {
            throw new IllegalArgumentException(
                    "Um usuário não pode seguir a si mesmo.");
        }

        if (seguindo.contains(outro)) {
            throw new IllegalArgumentException(
                    "O usuário já está sendo seguido.");
        }

        seguindo.add(outro);
    }

    public void deixarDeSeguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException(
                    "Usuário inválido: não é possível deixar de seguir um usuário nulo.");
        }

        seguindo.remove(outro);
    }

    public int getQuantidadeSeguindo() {
        return seguindo.size();
    }

    public ArrayList<Usuario> getSeguindo() {
        return seguindo;
    }

    public Plano getPlano() {
        return plano;
    }

    public void assinar(Plano novoPlano) {

        if (novoPlano == null) {
            throw new IllegalArgumentException(
                    "Plano inválido: não é possível assinar um plano nulo.");
        }

        this.plano = novoPlano;
    }
}
