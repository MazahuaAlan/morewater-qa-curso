package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaEats;
import org.testng.annotations.Test;

public class EatsTest extends BaseTest {

    @Test(description = "Pantalla de entrada de Eats, muestra restaurantes y categorias")
    
    public void pantallaDeEntradaDeEats() {

        PaginaEats eats = new PaginaEats(navegador).abrir();

        assertThat(eats.estaEnPantalla())
            .as("la pantalla de Eats debe estar visible")
            .isTrue();

        assertThat(eats.cuantosRestaurantesHay())
            .as("deben aparecer 8 restaurantes")
            .isEqualTo(8);

        assertThat(eats.cuantasCategoriasHay())
            .as("deben aparecer 8 categorias")
            .isEqualTo(8);

        assertThat(eats.textoDelContador())
            .as("el contador debe indicar los 8 restaurantes")
            .isEqualTo("8 restaurantes");
    }
}