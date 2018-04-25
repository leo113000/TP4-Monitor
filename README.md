# TP4-Monitor

# Maven
## Goals

- mvn clean: Limpia todos los archivos generados en la última compilación.
- mvn compile: Compila el código fuente.
- mvn package: Toma el código compilado y lo empaqueta en un formato determinado para su distribución (un JAR, por ejemplo)-
- mvn install: Instala el paquete en el repositorio local para usar como dependencia en proyectos locales.

## Scopes

- compile : Scope por defecto. Las dependencias estarán disponibles en todas las fases.
- provided: --
- runtime: Las dependencias con este scope no son requeridas para la fase de compilación.
Solo estarán disponibles para las fases de test y de tiempo de ejecución
- test:  Las dependencias con este scope no son requeridas en el uso normal de la aplicación. Solo serán usadas en la fase de test.
- system : ---
- import : ---
