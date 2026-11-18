package dev.morewater.qalab.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

/**
 * Guarda una captura de pantalla cuando una prueba falla.
 *
 * Se llama desde el @AfterMethod, ANTES de cerrar el navegador. Ese orden
 * importa: si cierras primero, ya no hay nada que fotografiar.
 *
 * Fijate en TakesScreenshot: es una INTERFACE, igual que WebDriver. Le pides
 * la foto al navegador sin saber cual navegador es.
 */
public final class Evidencia {

    private Evidencia() {}

    public static void guardarSiFallo(ITestResult resultado) {

        if (resultado.isSuccess()) {
            return;
        }

        WebDriver navegador = DriverFactory.current();
        if (navegador == null) {
            return;
        }

        try {
            Path carpeta = Path.of("target", "screenshots");
            Files.createDirectories(carpeta);

            String nombre = resultado.getTestClass().getRealClass().getSimpleName()
                          + "." + resultado.getMethod().getMethodName() + ".png";

            File foto = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
            Files.copy(foto.toPath(), carpeta.resolve(nombre), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("   evidencia: target/screenshots/" + nombre);

        } catch (Exception e) {
            // Guardar la evidencia nunca debe romper la prueba.
            System.out.println("   no se pudo guardar la captura: " + e.getMessage());
        }
    }
}
