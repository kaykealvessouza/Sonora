package main;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private static int contadorId = 1;

    private String nome;
    private String email;

    private ArrayList<Usuario> seguindo;

    public Usuario(String nome, String email) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nome inválido: o nome não pode ser vazio.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "E-mail inválido: o e-mail não pode ser vazio.");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException(
                    "E-mail inválido: o e-mail deve conter @.");
        }

        this.id = contadorId;
        contadorId++;

        this.nome = nome;
        this.email = email;
        this.seguindo = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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
}
