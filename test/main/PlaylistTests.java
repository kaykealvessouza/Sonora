package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaylistTests {

    private Playlist playlist;
    private Musica musica1;
    private Musica musica2;

    @BeforeEach
    void prepararTeste() {
        Usuario user = new Usuario("User1", "User@");
        playlist = new Playlist("Play  ", user);

        musica1 = new Musica("Ride", "Twenty on Pilots", 134, "album");
        musica2 = new Musica("Do I Wanna Know?", "Artic Monkeys", 134, "album");
    }

    private void preencherPlaylist(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            playlist.adicionar(new Musica("Musica " + i, "Artista", 180, "album"));
        }
    }

    @Test
    @DisplayName("Adicionar música numa playlist e quantidade aumenta")
    void adicionarPlaylist() {

        playlist.adicionar(musica1);
        assertEquals(1, playlist.getQuantidade());

        playlist.adicionar(musica2);
        assertEquals(2, playlist.getQuantidade());
    }

    @Test
    @DisplayName("Adicionar mais de 100 músicas sem limite fixo")
    void adicionarMaisDe100Musicas() {
        preencherPlaylist(101);

        assertEquals(101, playlist.getQuantidade());
    }

    @Test
    @DisplayName("Adicionar música vazia")
    void adicionaMusicaVazia() {

        assertThrows(
                IllegalArgumentException.class,
                () -> playlist.adicionar(null));
    }

    @Test
    @DisplayName("Procura posição válida")
    void procuraMusicaValida() {

        assertTrue(playlist.adicionar(musica1));

        assertEquals(playlist.getNaPosicao(1), musica1);
    }

    @Test
    @DisplayName("Índice negativo lança IndexOutOfBoundsException")
    void indiceNegativo() {

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> playlist.getNaPosicao(-1));
    }

    @Test
    @DisplayName("Índice além da quantidade lança IndexOutOfBoundsException")
    void indiceMaiorQueQuantidade() {

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> playlist.getNaPosicao(playlist.getQuantidade() + 1));
    }

    @Test
    @DisplayName("Remover música válida da playlist")
    void removerMusicaValida() {

        Musica musica3 = new Musica("505", "Artic Monkeys", 134, "album");
        Musica musica4 = new Musica("Why'd You Only Call Me When You're High?", "Artic Monkeys", 134, "album");
        Musica musica5 = new Musica("Jane!", "The Long Faces", 134, "album");

        assertTrue(playlist.adicionar(musica1));
        assertTrue(playlist.adicionar(musica2));
        assertTrue(playlist.adicionar(musica3));
        assertTrue(playlist.adicionar(musica4));
        assertTrue(playlist.adicionar(musica5));

        assertTrue(playlist.removerNaPosicao(3));

        assertEquals(4, playlist.getQuantidade());
        assertEquals(musica4, playlist.getNaPosicao(3));
        assertEquals(musica5, playlist.getNaPosicao(4));
    }

    @Test
    @DisplayName("Índice negativo para remover lança IndexOutOfBoundsException")
    void indiceNegativoParaRemover() {

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> playlist.removerNaPosicao(-1));
    }

    @Test
    @DisplayName("Índice além da quantidade para remover lança IndexOutOfBoundsException")
    void indiceAlemDaQuantidadeParaRemover() {

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> playlist.removerNaPosicao(playlist.getQuantidade() + 1));
    }
}
