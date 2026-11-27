package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaDeBilletera;
import dev.morewater.qalab.pages.PaginaPrincipal;
import org.testng.annotations.Test;

public class BilleteraTests extends BaseTest {

    @Test(description = "La billetera empieza con $66,000.00")
    public void elSaldoInicialEsCorrecto() {

        PaginaDeBilletera billetera = new PaginaPrincipal(navegador).irALaBilletera();

        assertThat(billetera.saldoEnCentavos())
            .as("saldo inicial en centavos")
            .isEqualTo(66_000_00L);
    }

    @Test(description = "Al empezar no hay movimientos")
    public void alEmpezarNoHayMovimientos() {

        PaginaDeBilletera billetera = new PaginaPrincipal(navegador).irALaBilletera();

        assertThat(billetera.noHayMovimientos())
            .as("tiene que salir el aviso de que no hay movimientos")
            .isTrue();

        assertThat(billetera.cuantosMovimientos())
            .as("la tabla no debe tener filas")
            .isZero();
    }
}