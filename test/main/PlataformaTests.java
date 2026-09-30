package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlataformaTests {

    Plataforma sonora;
    Musica musica1;
    Musica musica2;
    Musica musica3;

    @BeforeEach
    void prepararTestes(){
        sonora = new Plataforma();
        
        musica1 = new Musica("Ride", "Twenty one pilots", 125);
        musica2 = new Musica("HeavyDirty Soul", "Twenty one pilots", 90);
        musica3 = new Musica("Stressed out", "Twenty one pilots", 5);
    }

    @Test
    @DisplayName("Buscar música válida por id")
    void buscarMusicaValidaPorId() {

        sonora.cadastrarMusica(musica3);

        assertEquals(musica3, sonora.buscarMusica(musica3.getId()));
        assertEquals(musica3, sonora.buscarMusicaGeral(String.valueOf(musica3.getId())));
    }

    @Test
    @DisplayName("Buscar música válida por título")
    void buscarMusicaValidaPorTitulo() {

        sonora.cadastrarMusica(musica1);

        assertEquals(musica1, sonora.buscarMusica("Ride"));
        assertEquals(musica1, sonora.buscarMusicaGeral("Ride"));
    }

    @Test
    @DisplayName("Buscar música com Id inválido")
    void buscarMusicaComIdInvalido() {

        assertNull(sonora.buscarMusica(101));
        assertNull(sonora.buscarMusicaGeral("101"));
    }

    @Test
    @DisplayName("Buscar música com título inválido")
    void buscarMusicaComTituloInvalido() {
    
        assertNull(sonora.buscarMusica("Riding"));
        assertNull(sonora.buscarMusicaGeral("Riding"));
    }
    @Test
    @DisplayName("Plataforma aceita mais de 500 músicas")
    void cadastrarMaisDe500Musicas() {
        for (int i = 1; i <= 501; i++) {
            assertTrue(sonora.cadastrarMusica(
                    new Musica("Musica " + i, "Artista", 180)));
        }

        assertEquals(501, sonora.getTotalMusicas());
    }

    @Test
    @DisplayName("Plataforma aceita mais de 500 usuários")
    void cadastrarMaisDe500Usuarios() {
        for (int i = 1; i <= 501; i++) {
            assertTrue(sonora.cadastrarUsuario(
                    new Usuario("Usuario " + i, "usuario" + i + "@teste.com")));
        }

        assertEquals(501, sonora.getTotalUsuarios());
    }

}
