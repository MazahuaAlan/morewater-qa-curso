package dev.morewater.qalab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaDeStay extends BasePage {

    public PaginaDeStay(WebDriver driver) {
        super(driver);
    }
    
    public PaginaDeStay abrir() {
        go("/stay/");
        return this;
    }

    
    public boolean estaEnPantalla() {
        return present("stay-search-form");   
    }

    /** Espera a que el formulario de busqueda este en pantalla antes de dejar seguir al test. */
    public PaginaDeStay esperarAQueCargue() {
        visible("stay-search-form");
        return this;
    }

    public boolean muestraCampoDestino() {
        return present("stay-city");
    }

    public String destinoSeleccionado() {
        return text("stay-city");
    }

    public int cantidadDeAdultos() {
        return Integer.parseInt(text("stay-adults-value"));
    }

    public int cantidadDeMenores() {
        return Integer.parseInt(text("stay-kids-value"));
    }

    public String muestraHintCalendario() {
        return text("cal-hint");
    }

    public String resumenDeBusqueda() {
        return text("stay-range-summary");
    }

    public String muestraCantidadDeNoches() {
        return text("stay-nights");
    }

    public boolean muestraBotonBuscar() {
        return present("stay-search-submit");
    }
}
