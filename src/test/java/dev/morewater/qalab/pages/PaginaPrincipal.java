package dev.morewater.qalab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * La pantalla principal de qalab: la lista de las seis apps.
 *
 * Es la primera pagina que escribimos SIN que yo les diera los localizadores.
 * Todos los de aqui salieron del Inspector, en vivo.
 */
public class PaginaPrincipal extends BasePage {

    public PaginaPrincipal(WebDriver navegador) {
        super(navegador);
    }

    // --- LA TRANSICION ---
    public PaginaPrincipal abrir() {
        go("/");
        return this;
    }

    // --- LO QUE EL TEST PUEDE PREGUNTAR ---
    public boolean estaEnPantalla() {
        return present("app-list");
    }

    /** Se ve el mosaico de esta app? El id es air, bank, stay, eats, care o desk. */
    public boolean seVeLaApp(String id) {
        return present("app-" + id);
    }

    /**
     * Cuantos mosaicos hay DENTRO de la lista.
     *
     * Ojo con el selector: buscar [data-test^='app-'] en toda la pagina tambien
     * atrapa a app-list, que es la caja que los contiene. Por eso buscamos
     * adentro de la lista y no en toda la pantalla.
     */
    public int cuantasAppsHay() {
        return driver.findElements(
                By.cssSelector("[data-test='app-list'] [data-test^='app-']")).size();
    }

    public String saldoDelChip() {
        return text("wallet-chip");
    }

    // --- LA OTRA TRANSICION ---

    /**
     * Da clic en el chip del saldo y entrega la pagina a la que llegamos.
     *
     * Fijense en lo que devuelve: NO es un boolean ni un String, es otra
     * pagina. Asi se encadenan los Page Objects, y asi el test se lee como
     * una frase en lugar de como una lista de clics.
     */
    public PaginaDeBilletera irALaBilletera() {
        click("wallet-chip");
        return new PaginaDeBilletera(driver).esperarAQueCargue();
    }
}