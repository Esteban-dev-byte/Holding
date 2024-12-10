Contenido

1. Requisitos Previos

   Antes de iniciar, asegúrate de cumplir con los siguientes requisitos:

        Java Development Kit (JDK): Versión 17 o superior.
        Gradle: Herramienta para gestionar dependencias.
        Git: Para clonar el repositorio del proyecto.
        railway: Tener una cuenta en railway.app para gestion de base de datos
        en la nube.

2. Configuración del Entorno

       Clonar el Repositorio
         git clone <URL-DEL-REPOSITORIO>
       Configurar coneccion con la base de datos
         en el archivo application.properties usar el host y el puerto de
         la url publica que es un proxy que genera railway

       spring.datasource.url=jdbc:mysql://junction.proxy.rlwy.net:23629/railway
       spring.datasource.username=root
       spring.datasource.password=enrJzDxoLymkSUCfynBxHvlcGWYVixPT

3. Ejecucion.

       Por ultimo ejecutar el proyecto desde la clase de arranque HoldingApplication