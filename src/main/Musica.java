package main;

public class Musica extends Conteudo {


    private String artista;

    private String album;

    public Musica(String titulo, String artista, int duracaoSegundos, String album) {

        super(titulo, duracaoSegundos);

        setArtista(artista);
        setAlbum(album);
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista){

        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Artista inválido: o artista não pode ser vazio.");
        }

        this.artista = artista;
    }

    public String getAlbum(){
        return this.album;
    }

    public void setAlbum(String album){

        if (album == null || album.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Álbum inválido: o álbum não pode ser vazio.");
        }

        this.album = album;
    }

    @Override
    public String getCreditos() {
        return artista + " (" + album + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " - " + getCreditos();
    }
}