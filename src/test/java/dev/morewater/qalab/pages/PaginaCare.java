package dev.morewater.qalab.pages;

import org.openqa.selenium.WebDriver;

public class PaginaCare extends BasePage {

    public PaginaCare(WebDriver navegador) {
        super(navegador);
    }

    // --- TRANSICION ---
    public PaginaCare abrir() {
        go("/care/");
        visible("care-no-next");
        return this;
    }

    // --- COMPROBACION ---
    public boolean estaEnPantalla() {
        return present("care-rolebar");
    }

    // --- PREGUNTAS ---
    public boolean noTieneCitasProximas() {
        return present("care-no-next");
    }

    public String mensajeSinCitas() {
        return text("care-no-next");
    }

    public boolean sePuedeAgendarCita() {
        return present("care-go-book");
    }
}