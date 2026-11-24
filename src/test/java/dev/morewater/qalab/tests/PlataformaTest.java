package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaDeBilletera;
import dev.morewater.qalab.pages.PaginaPrincipal;
import org.testng.annotations.Test;

/**
 * La plataforma: lo que todos ven antes de entrar a su modulo.
 *
 * Hereda de BaseTest, asi que llega con el navegador abierto y la sesion
 * iniciada. No tiene que entrar: eso ya paso.
 */
public class PlataformaTest extends BaseTest {

    private static final String[] LAS_SEIS = { "air", "bank", "stay", "eats", "care", "desk" };

    @Test(description = "La pantalla principal muestra las seis apps")
    public void laPlataformaMuestraLasSeisApps() {

        PaginaPrincipal principal = new PaginaPrincipal(navegador).abrir();

        assertThat(principal.estaEnPantalla())
            .as("tiene que verse la lista de apps")
            .isTrue();

        for (String app : LAS_SEIS) {
            assertThat(principal.seVeLaApp(app))
                .as("falta el mosaico de la app '" + app + "'")
                .isTrue();
        }

        assertThat(principal.cuantasAppsHay())
            .as("no deben sobrar ni faltar mosaicos")
            .isEqualTo(6);
    }

    @Test(description = "El chip del saldo lleva a la billetera")
    public void elChipDelSaldoLlevaALaBilletera() {

        PaginaDeBilletera billetera = new PaginaPrincipal(navegador)
                                            .abrir()
                                            .irALaBilletera();

        assertThat(billetera.estaEnPantalla())
            .as("despues del clic tenemos que estar en la billetera")
            .isTrue();
    }
}