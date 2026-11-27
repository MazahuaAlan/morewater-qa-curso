package dev.morewater.qalab.pages;

import org.openqa.selenium.WebDriver;

public class PaginaDeBilletera extends BasePage {

    public PaginaDeBilletera(WebDriver navegador) {
        super(navegador);
    }

    public PaginaDeBilletera abrir() {
        go("/wallet/");
        return this;
    }

    /** Espera a que el saldo este en pantalla antes de dejar seguir al test. */
    public PaginaDeBilletera esperarAQueCargue() {
        visible("wallet-balance");
        return this;
    }

    public boolean estaEnPantalla() {
        return present("wallet-balance");
    }

    // --- DE AQUI PARA ABAJO LO ESCRIBES TU ---

    public long saldoEnCentavos() {
        return cents(text("wallet-balance"));
    }

    public boolean noHayMovimientos() {
        return present("no-movements");
    }

    public int cuantosMovimientos() {
        return driver.findElements(t("movement")).size();
    }
}