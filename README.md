# Gestor-de-inventarios
Proyecto didáctico de desarrollo en Java. Sistema de gestión de inventarios usando base de datos MySql.

1. Se agregan las clases del modelo en Java.
2. Se agregan las clases de la vista en Java.
3. Se agrega el controlador en Java.

Se valida la conexión desde la aplicación hacia la base de datos, usando el servicio en una máquina virtual desplegada en el mismo equipo (localhost)

Se agrega el archivo de comandos usados en CentOS 7 para crear la base de datos y las tablas requeridas.

Se debe tener en cuenta que se agrega la librería externa jdbc, con la cual se logra gestionar la conexión entre la base de datos y la aplicación Java

Estructura de la base de datos

---------------------------------------------------------
|    dato                  |           tipo             |
|--------------------------|----------------------------|
|    nombre                |          varchar           |
|--------------------------|----------------------------|
|    cantidad              |          int               |
|--------------------------|----------------------------|
