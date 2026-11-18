# MoreWater QA Evolution — Framework del curso

Este es el proyecto con el que vamos a trabajar las últimas tres semanas.
Aquí dejas de narrar en la consola y empiezas a mover un navegador de verdad
contra **https://qalab.morewater.dev**.

No hay nada que inventar en la primera clase: clonas, corres, y tiene que pasar.

---

## 1. Lo que necesitas instalado

| Qué | Versión | Cómo lo compruebas |
|---|---|---|
| Java (JDK) | 17 o más | `java -version` |
| Maven | 3.8 o más | `mvn -v` |
| Google Chrome | la que tengas | ábrelo |
| Git | cualquiera | `git --version` |

Los tres primeros comandos tienen que contestar algo. Si uno dice
«command not found», ese es el que falta.

> No necesitas descargar el chromedriver. Selenium lo consigue solo.

---

## 2. Clonar y correr

```bash
git clone https://github.com/MazahuaAlan/morewater-qa-curso.git
cd morewater-qa-curso
mvn test
```

**La primera vez tarda.** Maven baja Selenium, TestNG y AssertJ: son unos
80 MB y los guarda en `~/.m2`. De la segunda vez en adelante arranca en
segundos. Si ves cientos de líneas de `Downloading from central`, no está
roto: está bajando.

---

## 3. Lo que tiene que salir

```
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Tres pruebas:

| Prueba | Qué comprueba | ¿Usa internet? |
|---|---|---|
| `HumoTest.elEntornoFunciona` | Tu máquina: Maven, Chrome y el driver | No |
| `AccesoTest.elUsuarioEstandarEntra` | Que `estandar` entra a qalab | Sí |
| `AccesoTest.laContrasenaMalaMuestraError` | Que una contraseña mala da error | Sí |

Ese reparto es a propósito y es tu primer diagnóstico:

- **Humo falla** → el problema está en tu máquina (Java, Maven o Chrome).
- **Humo pasa y Acceso falla** → tu máquina está bien. Es la red o el sitio.

---

## 4. Ver el navegador

Por defecto corre *headless*: Chrome se abre sin ventana. Para verlo trabajar:

```bash
mvn test -Dqalab.headless=false
```

Vale la pena hacerlo la primera vez. Es la diferencia entre creer que
funciona y verlo.

---

## 5. Correr una sola cosa

```bash
mvn test -Dtest=HumoTest                       # una clase
mvn test -Dtest=AccesoTest#elUsuarioEstandarEntra   # un método
```

---

## 6. Qué hay dentro

```
pom.xml                 las librerías y la versión de Java
testng.xml              la suite: qué clases corren
src/test/java/dev/morewater/qalab/
  config/Config.java            de dónde salen la URL, el usuario y los tiempos
  core/DriverFactory.java       quién abre y cierra Chrome
  core/BaseTest.java            el antes y el después de cada prueba
  core/Evidencia.java           la captura de pantalla cuando algo falla
  pages/BasePage.java           lo que toda página sabe hacer (clic, escribir, esperar)
  pages/PaginaDeAcceso.java     la pantalla de acceso como Page Object
  tests/HumoTest.java           la prueba de humo
  tests/AccesoTest.java         el primer caso contra el sitio real
src/test/resources/humo.html    la página local de la prueba de humo
scripts/report.sh|.ps1          sube el resultado al tablero
```

`BasePage` y `PaginaDeAcceso` son **las mismas clases que escribimos a mano**
en las clases 15 a 21. Ábrelas y compáralas. Lo único que cambió es que ahora
los métodos mueven un navegador en lugar de imprimir en la consola.

---

## 7. Cuando algo truena

**`java: command not found` o `mvn: command not found`**
No está instalado o no está en el PATH. Instálalo y abre una terminal nueva.

**`release version 17 not supported` / `invalid target release`**
Tu Java es anterior al 17. `java -version` te dice cuál tienes.

**`Could not transfer artifact ... repo.maven.apache.org`**
No hay internet, o un proxy lo está bloqueando. Maven necesita salir la
primera vez.

**`SessionNotCreatedException: This version of ChromeDriver only supports...`**
Chrome se actualizó y quedó un driver viejo cacheado. Borra
`~/.cache/selenium` y vuelve a correr.

**Pasa `HumoTest` pero fallan los dos de `AccesoTest`**
Abre https://qalab.morewater.dev en tu navegador. Si no carga, es la red.

**`TimeoutException` esperando `user-chip`**
El sitio tardó más de 15 segundos. Súbelo: `mvn test -Dqalab.timeout=40`

Si nada de esto aplica: manda la terminal completa, desde el comando hasta
la última línea. No el pedazo rojo nada más.

---

## 8. Tu rama

Cada quien trabaja en su propia rama. Nadie empuja a `main`.

```bash
git checkout -b modulo/<tu-nombre>
```

Lo demás lo vemos en clase.
