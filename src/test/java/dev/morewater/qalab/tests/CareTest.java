package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaCare;
import org.testng.annotations.Test;

public class CareTest extends BaseTest {

    @Test(description = "La pantalla de entrada de Care abre sin citas proximas y permite agendar")
    public void pantallaDeEntradaDeCare() {

        PaginaCare care = new PaginaCare(navegador).abrir();

        assertThat(care.estaEnPantalla())
            .as("la pantalla de Care debe estar visible")
            .isTrue();

        assertThat(care.noTieneCitasProximas())
            .as("debe mostrar el aviso de que no hay citas proximas")
            .isTrue();

        assertThat(care.mensajeSinCitas())
            .as("el texto debe indicar que no hay citas proximas")
            .isEqualTo("No tienes citas próximas.");

        assertThat(care.sePuedeAgendarCita())
            .as("el boton para agendar cita debe estar visible")
            .isTrue();
    }
}