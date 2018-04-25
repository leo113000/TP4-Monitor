# TP4-Monitor

# Maven
## Goals

- mvn clean: Limpia todos los archivos generados en la última compilación.
- mvn compile: Compila el código fuente.
- mvn package: Toma el código compilado y lo empaqueta en un formato determinado para su distribución (un JAR, por ejemplo)-
- mvn install: Instala el paquete en el repositorio local para usar como dependencia en proyectos locales.

## Scopes

- compile : Scope por defecto. Las dependencias estarán disponibles en todas las fases.
- provided: Es como la anterior, pero se espera que el contenedor ya tenga esa libreria. Un claro ejemplo es cuando desplegamos en un servidor de aplicaciones, que por defecto, tiene bastantes librerías que utilizaremos en el proyecto, así que no necesitamos desplegar la dependencia.
- runtime: Las dependencias con este scope no son requeridas para la fase de compilación.
Solo estarán disponibles para las fases de test y de tiempo de ejecución
- test:  Las dependencias con este scope no son requeridas en el uso normal de la aplicación. Solo serán usadas en la fase de test.
- system : Similar a provided pero hay que especificar la ruta de la dependencia mediante la etiqueta <systemPath>
- import : Es utilizado en la sección <dependencyManagment>

**Archetype:** Es una plantilla de un proyecto Maven. La diferencia con un Artifact es que este es la salida de un proyecto Maven ya empaquetado, listo para ser usado como dependencia.

La **estructura base de un proyecto Maven**, en el caso de que no hayamos utilizado ningún Archetype, será:
- Una estructura de carpetas con el mismo nombre que el GroupId que especifiquemos, dentro de esto estará la carpeta src con el código.
- Carpeta de test
- Archivos de Maven donde el más destacado es el POM.xml donde se configurara el proyecto, sobre todo las dependencias que este tendrá.

# Spring
- @Component: Anotación de propósito general para definir un componente manejado por Spring.
- @Repository: Especialización de @Componente para indicar que la clase es un repositorio de persistencia.
- @Service: Especialización de @Componente. Indica que será un servicio que manejará la lógica de negicio
- @Controller: Especialización de @Componente. Indica que será una controladora, aquí se usará la anotación @RequestMapping para definir los Endpoints.

# REST
- GET: Solicita una representación de un recurso específico.
Las peticiones que usan el método GET sólo deben recuperar datos.
- HEAD: Pide una respuesta idéntica a la de una petición GET, pero sin el cuerpo de la respuesta.
- POST: Se utiliza para enviar una entidad a un recurso en específico, causando a menudo un cambio en el estado o efectos secundarios en el servidor.
- PUT: Similar al POST pero es idempotente.
- DELETE: Borra un recurso en específico.
- CONNECT: Establece un túnel hacia el servidor identificado por el recurso.
- PATCH: Es utilizado para aplicar modificaciones parciales a un recurso.
- OPTIONS: Es utilizado para describir las opciones de comunicación para el recurso de destino.
- TRACE: Realiza una prueba de bucle de retorno de mensaje a lo largo de la ruta al recurso de destino.
