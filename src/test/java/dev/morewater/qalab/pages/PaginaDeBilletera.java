package dev.morewater.qalab.pages;

import org.openqa.selenium.WebDriver;

/**
 * La billetera: el saldo que comparten las seis apps.
 *
 * ESTA CLASE LA ESCRIBIERON USTEDES. Los localizadores salieron del Inspector,
 * no de mi. Si la suya quedo distinta y pasa, esta bien: no hay una sola forma
 * correcta. Lo que si tiene que cumplir es que el test no sepa ni un data-test.
 */
public class PaginaDeBilletera extends BasePage {

    public PaginaDeBilletera(WebDriver navegador) {
        super(navegador);
    }

    // --- LA TRANSICION ---
    public PaginaDeBilletera abrir() {
        go("/wallet/");
        return this;
    }

    /** Espera a que el saldo este en pantalla antes de dejar seguir al test. */
    public PaginaDeBilletera esperarAQueCargue() {
        visible("wallet-balance");
        return this;
    }

    // --- LO QUE EL TEST PUEDE PREGUNTAR ---
    public boolean estaEnPantalla() {
        return present("wallet-balance");
    }

    /**
     * El saldo, en CENTAVOS.
     *
     * La pantalla dice "$66,000.00". Si devolvieramos ese texto, el test
     * tendria que compararlo con otro texto, y el dia que cambien el separador
     * de miles se cae todo. cents() lo convierte a 6600000 y comparamos
     * numeros.
     */
    public long saldoEnCentavos() {
        return cents(text("wallet-balance"));
    }

    public boolean noHayMovimientos() {
        return present("no-movements");
    }

    public int cuantosMovimientosHay() {
        return driver.findElements(t("movement")).size();
    }
}