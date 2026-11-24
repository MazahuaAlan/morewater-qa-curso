package dev.morewater.qalab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaEats extends BasePage {

    public PaginaEats(WebDriver navegador) {
        super(navegador);
    }

    // --- TRANSICION ---
    public PaginaEats abrir() {
        go("/eats/");
        visible("eats-rest-name-0"); 
        return this;
    }

    //comprobar que esta en pantalla
    public boolean estaEnPantalla() {
        return present("eats-search");
    }
    
    // --- PREGUNTAS ---
    public int cuantosRestaurantesHay() {
        return driver.findElements(By.cssSelector("[data-test^='eats-rest-name-']")).size();
    }

    public int cuantasCategoriasHay() {
        return driver.findElements(By.cssSelector("[data-test^='eats-chip-']")).size();
    }

    public String textoDelContador() {
        return text("eats-count");
    }
}