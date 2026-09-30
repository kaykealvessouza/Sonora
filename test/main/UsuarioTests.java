package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsuarioTests {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;

    @BeforeEach
    void prepararTeste() {
        usuario1 = new Usuario("User1", "user1@teste.com");
        usuario2 = new Usuario("User2", "user2@teste.com");
        usuario3 = new Usuario("User3", "user3@teste.com");
    }

    @Test
    @DisplayName("Seguir usuário aumenta a quantidade de seguindo")
    void seguirUsuario() {
        usuario1.seguir(usuario2);

        assertEquals(1, usuario1.getQuantidadeSeguindo());
        assertTrue(usuario1.getSeguindo().contains(usuario2));
    }

    @Test
    @DisplayName("Usuário não pode seguir a si mesmo")
    void seguirASiMesmo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuario1.seguir(usuario1));
    }

    @Test
    @DisplayName("Usuário não pode seguir duas vezes o mesmo usuário")
    void seguirDuasVezes() {
        usuario1.seguir(usuario2);

        assertThrows(
                IllegalArgumentException.class,
                () -> usuario1.seguir(usuario2));

        assertEquals(1, usuario1.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Deixar de seguir remove o usuário da lista")
    void deixarDeSeguir() {
        usuario1.seguir(usuario2);
        usuario1.seguir(usuario3);

        usuario1.deixarDeSeguir(usuario2);

        assertEquals(1, usuario1.getQuantidadeSeguindo());
        assertFalse(usuario1.getSeguindo().contains(usuario2));
        assertTrue(usuario1.getSeguindo().contains(usuario3));
    }
}
