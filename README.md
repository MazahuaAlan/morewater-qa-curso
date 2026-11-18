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

> **Chrome tiene que estar instalado.** No necesitas descargar el chromedriver —eso lo
> consigue Selenium solo—, pero el navegador sí. Si no lo tienes, Selenium se baja uno
> entero (unos 150 MB) sin decirte nada, y parece que se colgó.

---

## 2. Clonar y correr

```bash
git clone https://github.com/MazahuaAlan/morewater-qa-curso.git
cd morewater-qa-curso
mvn test
```

**La primera vez tarda, y en dos tiempos.**

1. Maven baja Selenium, TestNG y AssertJ: unos 80 MB, con cientos de líneas de
   `Downloading from central`. Se ve lo que pasa.
2. Después aparece `T E S T S` y **la pantalla se queda quieta**. Ahí Selenium está
   bajando el chromedriver, y eso **no imprime nada**. Puede tardar un par de minutos.

> ⚠ **Esa pausa en silencio no es un cuelgue.** Es el momento en el que todo el mundo
> cree que se rompió. Déjalo trabajar. Si pasan más de cinco minutos sin que termine,
> ahí sí avisa.

De la segunda corrida en adelante, las tres pruebas tardan unos **veinte segundos**.

---

## 3. Lo que tiene que salir

```
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Y antes de eso, una línea que vale la pena leer:

```
   el navegador abrio y leyo: "Tu entorno funciona"
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

**Se queda quieto justo después de `T E S T S`**
Está bajando el chromedriver, en silencio. Espera. Si tarda demasiado, lo más probable
es que no tengas Chrome instalado y se esté bajando un navegador completo: instala
Chrome y vuelve a correr.

**Avisos de `CDP` o de `SLF4J`**
Si los ves, tienes una copia vieja del repo. Haz `git pull` — ya están silenciados.

Si nada de esto aplica: manda la terminal completa, desde el comando hasta
la última línea. No el pedazo rojo nada más.

---

## 8. Tu rama

Cada quien trabaja en su propia rama. Nadie empuja a `main`.

```bash
git checkout -b modulo/<tu-nombre>
```

Lo demás lo vemos en clase.
