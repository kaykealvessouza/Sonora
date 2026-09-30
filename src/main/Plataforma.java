package main;

import java.util.ArrayList;

public class Plataforma {

    private ArrayList<Musica> musicas = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public boolean cadastrarMusica(Musica musica) {

        if (musica == null) {
            return false;
        }

        musicas.add(musica);
        return true;
    }

    public Musica buscarMusicaGeral(String entrada) {

        if (entrada == null || entrada.trim().isEmpty()) {
            return null;
        }

        Musica encontrada = buscarMusica(entrada);

        if (encontrada == null) {
            try {
                int id = Integer.parseInt(entrada);
                encontrada = buscarMusica(id);
            } catch (NumberFormatException e) {
                // A entrada não é um ID válido
            }
        }

        return encontrada;
    }

    public Musica buscarMusica(int id) {

        if (id <= 0) {
            return null;
        }

        for (Musica musica : musicas) {
            if (id == musica.getId()) {
                return musica;
            }
        }

        return null;
    }

    public Musica buscarMusica(String titulo) {

        if (titulo == null || titulo.trim().isEmpty()) {
            return null;
        }

        for (Musica musica : musicas) {
            if (titulo.equals(musica.getTitulo())) {
                return musica;
            }
        }

        return null;
    }

    public Musica getMusicaNaPosicao(int indice) {
        if (indice <= 0 || indice > musicas.size()) {
            return null;
        }

        return musicas.get(indice - 1);
    }

    public int getTotalMusicas() {
        return musicas.size();
    }

    public boolean cadastrarUsuario(Usuario usuario) {

        if (usuario == null) {
            return false;
        }

        usuarios.add(usuario);
        return true;
    }

    public Usuario buscarUsuarioPorId(int id) {

        if (id <= 0) {
            return null;
        }

        for (Usuario usuario : usuarios) {
            if (id == usuario.getId()) {
                return usuario;
            }
        }

        return null;
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public boolean cadastrarPlaylist(Playlist playlist) {

        if (playlist == null) {
            return false;
        }

        playlists.add(playlist);
        return true;
    }

    public Playlist buscarPlaylist(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            return null;
        }

        for (Playlist playlist : playlists) {
            if (playlist.getNome().equals(nome)) {
                return playlist;
            }
        }

        return null;
    }

    public Playlist getPlaylistNaPosicao(int indice) {
        if (indice <= 0 || indice > playlists.size()) {
            return null;
        }

        return playlists.get(indice - 1);
    }

    public int getTotalPlaylist() {
        return playlists.size();
    }
}
