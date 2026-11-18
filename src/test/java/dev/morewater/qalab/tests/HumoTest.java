package dev.morewater.qalab.tests;

import static org.assertj.core.api.Assertions.assertThat;

import dev.morewater.qalab.core.DriverFactory;
import dev.morewater.qalab.core.Evidencia;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * PRUEBA DE HUMO — no prueba qalab, prueba TU MAQUINA.
 *
 * Abre una pagina que vive en este mismo proyecto. Si esta pasa:
 *   - Maven bajo las librerias
 *   - Chrome esta instalado
 *   - Selenium Manager consiguio el chromedriver
 *
 * No toca internet (mas que la primera vez, cuando Maven descarga).
 * Si esta pasa y AccesoTest falla, el problema no es tu entorno:
 * es la red o el sitio.
 */
public class HumoTest {

    private WebDriver navegador;

    @BeforeMethod
    public void antesDeCadaPrueba() {
        navegador = DriverFactory.create();
    }

    @Test(description = "El entorno funciona: Maven, Chrome y el driver")
    public void elEntornoFunciona() {

        String pagina = Paths.get("src", "test", "resources", "humo.html")
                             .toAbsolutePath().toUri().toString();

        navegador.get(pagina);

        String titulo = navegador.findElement(By.cssSelector("[data-test='humo-titulo']")).getText();

        System.out.println();
        System.out.println("   el navegador abrio y leyo: \"" + titulo + "\"");

        assertThat(titulo).isEqualTo("Tu entorno funciona");
    }

    @AfterMethod
    public void despuesDeCadaPrueba(ITestResult resultado) {
        Evidencia.guardarSiFallo(resultado);
        DriverFactory.quit();
    }
}
