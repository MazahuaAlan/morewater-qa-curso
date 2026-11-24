package dev.morewater.qalab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaDeEats extends BasePage {

    public PaginaDeEats(WebDriver driver) {
        super(driver);
    }
    public PaginaDeEats abrir() {
        go("/eats/");
        return this;
    }

    public PaginaDeEats esperarAQueCargue() {
        visible("eats-list");
        visible("eats-cuisines");
        return this;
    }

    public boolean estaEnPantalla() {
        return present("eats-list");
    }

    public boolean muestraDireccion() {
        return present("eats-address");
    }

    public boolean muestraCampoBusqueda() {
        return present("eats-search");
    }

    public boolean noHayRestaurante() {
        return present("eats-empty");
    }

    public boolean muestraFiltroComida(String id) {
        //El id es Tacos, Pizza, Sushi, Hamburguesas, Mariscos, Saludable, Postres, Café
        return present("eats-chip-" + id);
    }

    public int cuantosFiltrosComidaMuestra() {
        return driver.findElements(
            By.cssSelector("[data-test^='eats-chip-']")).size();    
    }

    public String contadorRestaurantes() {
        return text("eats-count");
    }

    public int cuantosRestaurantesMuestra() {
        return driver.findElements(
            By.cssSelector("[data-test^='eats-rest-name']")).size();    
    }

    public String estadoDelRestaurante(String id) {
        return text("eats-rest-status-" + id);
    }

    public String articulosEnCarrito() {
        return text("nav-eats-checkout");
    }


}

