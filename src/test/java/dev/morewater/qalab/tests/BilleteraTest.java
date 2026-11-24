package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.BaseTest;
import dev.morewater.qalab.pages.PaginaDeBilletera;
import org.testng.annotations.Test;

/**
 * La billetera, al empezar.
 *
 * Cada prueba abre un Chrome nuevo con el perfil vacio, y qalab guarda todo en
 * el navegador. Por eso el saldo SIEMPRE arranca en $66,000.00, sin importar lo
 * que haya hecho la prueba anterior. Ese es el aislamiento de la clase 21,
 * ahora gratis.
 */
public class BilleteraTest extends BaseTest {

    private static final long SALDO_INICIAL = 66_000_00L;

    @Test(description = "La billetera empieza con $66,000.00")
    public void elSaldoEmpiezaEn66Mil() {

        PaginaDeBilletera billetera = new PaginaDeBilletera(navegador).abrir().esperarAQueCargue();

        assertThat(billetera.saldoEnCentavos())
            .as("el saldo inicial en centavos")
            .isEqualTo(SALDO_INICIAL);
    }

    @Test(description = "Al empezar no hay ningun movimiento")
    public void alEmpezarNoHayMovimientos() {

        PaginaDeBilletera billetera = new PaginaDeBilletera(navegador).abrir().esperarAQueCargue();

        assertThat(billetera.noHayMovimientos())
            .as("tiene que salir el aviso de que no hay movimientos")
            .isTrue();

        assertThat(billetera.cuantosMovimientosHay())
            .as("y la tabla no debe tener ni una fila")
            .isEqualTo(0);
    }
}