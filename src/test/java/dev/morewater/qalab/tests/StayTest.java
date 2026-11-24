package dev.morewater.qalab.tests;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaDeStay;


public class StayTest extends BaseTest{
        
    @Test(description = "Abrir app de Stay y visualiza el formulario de busqueda")
    public void muestraFormularioDeBusqueda() {

        PaginaDeStay stay = new PaginaDeStay(navegador).abrir().esperarAQueCargue();

        assertThat(stay.estaEnPantalla())
            .as("tiene que verse el formulario de busqueda")
            .isTrue();

        assertThat(stay.muestraHintCalendario())
            .as("tiene que verse el hint del calendario")
            .isEqualTo("Elige la fecha de entrada");
        
        assertThat(stay.muestraCampoDestino())
            .as("tiene que verse el campo de destino")
            .isTrue();

        assertThat(stay.cantidadDeAdultos())
            .as("tiene que verse la cantidad de adultos")
            .isBetween(1, 4);

        assertThat(stay.cantidadDeMenores())
            .as("tiene que verse la cantidad de menores")
            .isBetween(0, 3);

        assertThat(stay.muestraBotonBuscar())
            .as("tiene que verse el boton de buscar")
            .isTrue();
    }
}
