package dev.morewater.qalab.tests;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaDeEats;

public class EatsTest extends BaseTest{
    
    private static final String[] categoriasComida = { "tacos", "pizza", "sushi", "hamburguesas", 
    "mariscos", "saludable", "postres", "cafe" };


    @Test(description = "Abrir app de Eats y visualizar la busqueda, filtros y lista de restaurantes")
    public void muestraListaDeRestaurantes() {

        PaginaDeEats eats = new PaginaDeEats(navegador).abrir().esperarAQueCargue();

        assertThat(eats.muestraDireccion())
            .as("tiene que verse la direccion de entrega")
            .isTrue();

        assertThat(eats.muestraCampoBusqueda())
            .as("tiene que verse el campo de busqueda")
            .isTrue();

        for (String categoria : categoriasComida) {
            assertThat(eats.muestraFiltroComida(categoria))
                .as("falta el mosaico de la categoria '" + categoria + "'")
                .isTrue();
        }

        assertThat(eats.contadorRestaurantes())
            .as("tiene que verse el contador de restaurantes")
            .isEqualTo("8 restaurantes");

        assertThat(eats.estaEnPantalla())
            .as("tiene que verse la lista de restaurantes")
            .isTrue();
    
        assertThat(eats.cuantosRestaurantesMuestra())
            .as("tiene que mostrar los 8 restaurantes")
            .isEqualTo(8);    
        }

}
