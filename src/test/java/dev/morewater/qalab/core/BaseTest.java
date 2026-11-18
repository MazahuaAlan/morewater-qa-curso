package dev.morewater.qalab.core;

import dev.morewater.qalab.pages.PaginaDeAcceso;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * De aqui hereda toda prueba que necesite estar DENTRO de la plataforma.
 *
 * Es el antesDeCadaCaso() de la clase 21, pero ahora lo llama TestNG en vez
 * del corredor que escribimos a mano:
 *
 *     antesDeCadaCaso()  ->  @BeforeMethod
 *     despuesDeCadaCaso()->  @AfterMethod
 *     el corredor        ->  TestNG
 *
 * Cada prueba arranca con un navegador nuevo y la sesion ya iniciada.
 * Eso es el aislamiento del que hablamos: ninguna prueba hereda el
 * estado de la anterior.
 */
public abstract class BaseTest {

    protected WebDriver navegador;

    @BeforeMethod
    public void antesDeCadaPrueba() {
        navegador = DriverFactory.create();
        new PaginaDeAcceso(navegador).abrir().entrarConElUsuarioConfigurado();
    }

    @AfterMethod
    public void despuesDeCadaPrueba(ITestResult resultado) {
        Evidencia.guardarSiFallo(resultado);
        DriverFactory.quit();
    }
}
