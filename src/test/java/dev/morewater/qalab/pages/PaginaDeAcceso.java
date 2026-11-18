package dev.morewater.qalab.pages;

import dev.morewater.qalab.config.Config;
import org.openqa.selenium.WebDriver;

/**
 * La pantalla de acceso de qalab, ahora de verdad.
 *
 * Es el mismo Page Object de la clase 15, con una diferencia: los metodos
 * que antes narraban en la consola ahora mueven un navegador. La FORMA
 * de la clase no cambio.
 */
public class PaginaDeAcceso extends BasePage {

    public PaginaDeAcceso(WebDriver navegador) {
        super(navegador);
    }

    // --- LA TRANSICION ---
    public PaginaDeAcceso abrir() {
        go("/id/");
        return this;
    }

    // --- LAS ACCIONES ---
    public PaginaDeAcceso entrarComo(String usuario, String contrasena) {
        type("username", usuario);
        type("password", contrasena);
        click("login-button");
        return this;
    }

    /**
     * El click no es instantaneo: el sitio pregunta al servidor y luego cambia
     * de pantalla. Aqui esperamos a que pase UNA de las dos cosas posibles:
     * aparecio el chip del usuario (entro) o aparecio el error (no entro).
     *
     * Esto es la espera explicita de la clase 16, aplicada a un navegador real.
     */
    public PaginaDeAcceso esperarRespuesta() {
        wait.until(d -> present("user-chip") || present("login-error"));
        return this;
    }

    /** Entra con el usuario de -Dqalab.user (por defecto «estandar»). */
    public void entrarConElUsuarioConfigurado() {

        entrarComo(Config.user(), Config.password()).esperarRespuesta();

        if (!entro()) {
            throw new AssertionError("No se pudo entrar como '" + Config.user() + "': " + mensajeDeError());
        }
    }

    // --- LO QUE EL TEST SI PUEDE PREGUNTAR ---
    public String mensajeDeError() {
        return text("login-error");
    }

    public boolean estaEnPantalla() {
        return present("login-form");
    }

    public boolean entro() {
        return present("user-chip");
    }
}
