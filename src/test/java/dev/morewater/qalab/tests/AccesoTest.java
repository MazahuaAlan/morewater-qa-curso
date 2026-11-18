package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.DriverFactory;
import dev.morewater.qalab.core.Evidencia;
import dev.morewater.qalab.pages.PaginaDeAcceso;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * El primer caso de verdad, contra qalab.morewater.dev.
 *
 * No hereda de BaseTest porque BaseTest ya entra a la plataforma, y
 * aqui lo que estamos probando ES entrar.
 */
public class AccesoTest {

    @AfterMethod
    public void despuesDeCadaPrueba(ITestResult resultado) {
        Evidencia.guardarSiFallo(resultado);
        DriverFactory.quit();
    }

    @Test(description = "El usuario estandar entra y ve su chip de usuario")
    public void elUsuarioEstandarEntra() {

        PaginaDeAcceso acceso = new PaginaDeAcceso(DriverFactory.create()).abrir();

        assertThat(acceso.estaEnPantalla())
            .as("la pantalla de acceso tiene que estar ahi antes de escribir")
            .isTrue();

        acceso.entrarComo("estandar", "qalab123").esperarRespuesta();

        assertThat(acceso.entro())
            .as("despues de entrar tiene que aparecer el chip del usuario")
            .isTrue();
    }

    @Test(description = "Una contrasena incorrecta muestra un mensaje de error")
    public void laContrasenaMalaMuestraError() {

        PaginaDeAcceso acceso = new PaginaDeAcceso(DriverFactory.create())
                                    .abrir()
                                    .entrarComo("estandar", "esta-no-es")
                                    .esperarRespuesta();

        assertThat(acceso.mensajeDeError()).contains("incorrectos");

        assertThat(acceso.entro())
            .as("con la contrasena mala NO debe haber sesion")
            .isFalse();
    }
}
