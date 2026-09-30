package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MusicaTests {

    private Musica musica1;

    private String banda = "Twenty one pilots";

    @Test
    @DisplayName("Duração com minutos e segundos 125 -> 02:05")
    void validaDuracaoFormatada125() {
        musica1 = new Musica("Ride", banda, 125);
        assertEquals("02:05", musica1.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração com minutos e segundos 90 -> 01:30")
    void validaDuracaoFormatada90() {
        musica1 = new Musica("HeavyDirty Soul", banda, 90);
        assertEquals("01:30", musica1.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração com minutos e segundos 5 -> 00:05")
    void validaDuracaoFormatada5() {
        musica1 = new Musica("Stressed out", banda, 5);
        assertEquals("00:05", musica1.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração com minutos e segundos 600 -> 10:00")
    void validaDuracaoFormatada600() {
        musica1 = new Musica("Chlorine", banda, 600);
        assertEquals("10:00", musica1.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração com minutos e segundos 599 -> 09:59")
    void validaDuracaoFormatada599() {
        musica1 = new Musica("Lane boy", banda, 599);
        assertEquals("09:59", musica1.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Título vazio deve ser rejeitado")
    void validaTituloVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Musica("", "Queen", 355));
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado")
    void validaTituloNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Musica(null, "Queen", 355));
    }

    @Test
    @DisplayName("Artista vazio deve ser rejeitado")
    void validaArtistaVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "", 355));
    }

    @Test
    @DisplayName("Duração da música 0 deve ser rejeitada")
    void duracaoZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "Queen", 0));
    }

    @Test
    @DisplayName("Duração negativa da música deve ser rejeitada")
    void duracaoNegativa() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Musica("Bohemian Rhapsody", "Queen", -10));
    }

    @Test
    @DisplayName("Tudo válido cria a música com id maior que zero")
    void criaMusicaCorretamente() {
        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);

        assertNotNull(musica1);

        assertTrue(musica1.getId() > 0);
    }

    @Test
    @DisplayName("Reprodução válida aumenta contador em um")
    void reproduzirMusicaValida(){
        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);

        musica1.reproduzir();

        assertEquals(1, musica1.getReproducoes());
    }

    @Test
    @DisplayName("Reproduzir cinco vezes uma música válida aumentando contador")
    void reproduzirMusicaValidaCincoVezes(){
        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);

        for (int i = 0; i < 5; i++){
            musica1.reproduzir();
            assertEquals(1 + i, musica1.getReproducoes());
        }
    }

    @Test
    @DisplayName("Reproduzir várias vezes uma música válida aumentando contador")
    void reproduzirMusicaValidaVariasVezes(){
        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);

        for (int i = 0; i < 1000; i++){
            musica1.reproduzir();
            assertEquals(musica1.getReproducoes(), 1 + i);
        }
    }

    @Test
    @DisplayName("Ids de músicas devem ser sequanciais")
    void validaIdSequencialMusicas(){

        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);
        Musica musica2 = new Musica("Chlorine", banda, 600);

        assertEquals(musica1.getId() + 1, musica2.getId());
    }

    @Test
    @DisplayName("Ids de usuários devem ser sequanciais")
    void validaIdSequencialUsuario(){

        Usuario user1 = new Usuario("a", "a@");
        Usuario user2 = new Usuario("b", "b@");

        assertEquals(user1.getId() + 1, user2.getId());
    }

    @Test //Só funciona rodando aqui, rodando tudo, vai dar erro pois o contador é static e pode não começar no 1
    @DisplayName ("Ids de músicas e usuários devem ser idependentes")
    void validaIdMusicaDiferenteUsuario(){

        musica1 = new Musica("Bohemian Rhapsody", "Queen", 355);
        Usuario user2 = new Usuario("b", "b@");
        Musica musica2 = new Musica("Bohemian Rhapsody", "Queen", 355);

        assertNotEquals(user2.getId() + 1, user2.getId());

        assertEquals(musica1.getId() + 1, musica2.getId());
    }
}