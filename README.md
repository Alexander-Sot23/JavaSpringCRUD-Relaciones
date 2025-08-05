# CRUD API con RELACIONES en Spring Boot 3

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-green)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-orange)](https://www.mysql.com/)
[![JPA](https://img.shields.io/badge/JPA-Hibernate-purple)](https://hibernate-org.translate.goog/orm/?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=tc).

Una API REST construida con Spring Boot 3 que implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar gimnasios, entrenadores y usuarios con relaciones entre ellos.

## Características principales

- **Operaciones CRUD completas**: Crear, Leer, Actualizar y Eliminar recursos para tres entidades relacionadas: Gimnasios, Entrenadores y Usuarios
- **Relaciones JPA**: 
  - Un Gimnasio tiene múltiples Entrenadores (One-to-Many)
  - Un Entrenador tiene múltiples Usuarios (One-to-Many)
- **Validación de datos**: Uso de anotaciones para validar entradas
- **Paginación y ordenamiento**: En todos los endpoints de listado
- **DTOs (Data Transfer Objects)**: Para controlar la exposición de datos en las respuestas
- **MySQL**: Integración con base de datos relacional MySQL
- **Manejo de excepciones**: Control centralizado de errores
- **Arquitectura limpia**: Separación clara de capas (controlador, servicio, repositorio)


## Tecnologías utilizadas

- **Spring Boot 3**: Framework para aplicaciones Java
- **Spring Data JPA**: Persistencia de datos
- **Spring Validation**: Validación de datos de entrada
- **Maven**: Gestión de dependencias
- **MySQL Database**: Base de datos MySQL
- **Lombok**: Reducción de código

## Endpoints de la API
Gimnasios (Gyms):

### GET `/api/gym`
Devuelve una lista paginada en formato JSON de todos los gimnasios.

Para configurar la paginación, agregue los siguientes parámetros a la URL:

`?page=0&size=10&sort=id`

Explicacion detallada de los parámetros:
`?page=0` Indica cual pagina mostrar (Comienza desde 0).
`?size=10`  Especifica la cantidad de elementos por página.
`?sort=id` Define el campo por el cual se ordenarán los resultados.
- Responses:
	- `200 OK`  Devuelve la lista paginada de los gimnasios.

### GET `/api/gym/{id}`
Devuelve un JSON con la información del gimnasio con el ID especificado.
- Responses:
	- `400 Bad Request` Si no existe un gimnasio con el id ingresado.
	- `200 OK` Devuelve el gimnasio perteneciente al id ingresado.

### POST `/api/gym`
Permite crear un nuevo objeto gym en la base de datos. Formato del cuerpo de la solicitud:
```
{
    "name": "Power Fitness Center",
    "address": "Lopez Zuñiga 13"
}
```
El ID se generará automáticamente, por lo que no es necesario ingresar un ID desde el JSON.
- Responses:
	- `400 Bad Request` Si ingresamos mal o nos falta completar un campo, devolverá el campo del JSON con error.
	- `201 Created` Gimnasio creado en la base de datos.

### POST `/api/gym/bulk`
Crea múltiples gimnasios en una sola solicitud. Formato del cuerpo:
```
{
    "name": "Elite Gym",
    "address": "AV. Patria 45"
},
{
    "name": "Iron Temple",
    "address": "Vuelta de Rueda 12"
},
{
    "name": "Flex Zone",
    "address": "Paseo Del Rio 03"
}
```
Los IDs se generará automáticamente, por lo que no es necesario ingresarlos desde el JSON.
- Responses:
	- `400 Bad Request` Si hay errores de validación en los campos.
	- `201 Created` Gimnasios creados en la base de datos.

### PUT `/api/gym/{id}`
Actualiza un gimnasio existente.
 Ejemplo: `/api/gym/2`
```
{
    "name": "Super Iron Temple",
    "address": "Vuelta de Rueda 12"
}
```
- Responses:
	- `400 Bad Request` Si no existe el gimnasio con el ID especificado
	- `200 OK` Gimnasio actualizado.

### DELETE `/api/gym/{id}`
Elimina un gimnasio.

 Ejemplo: `/api/gym/2`
- Responses:
	- `400 Bad Request` Si no existe el gimnasio con el ID especificado
	- `200 OK` Gymnasio eliminado.

Entrenadores (Coaches):

### GET `/api/coach`
Devuelve una lista paginada en formato JSON de todos los entrenadores.

Para configurar la paginación, agregue los siguientes parámetros a la URL:

`?page=0&size=10&sort=id`

Explicacion detallada de los parámetros:
`?page=0` Indica cual pagina mostrar (Comienza desde 0).
`?size=10`  Especifica la cantidad de elementos por página.
`?sort=id` Define el campo por el cual se ordenarán los resultados.
- Responses:
	- `200 OK`  Devuelve la lista paginada de los entrenadores.

### GET `/api/coach/{id}`
Devuelve un JSON con la información del entrenador con el ID especificado.
- Responses:
	- `400 Bad Request` Si no existe un entrenador con el id ingresado.
	- `200 OK` Devuelve el entrenador perteneciente al id.

### POST `/api/coach`
Permite crear un nuevo objeto coach en la base de datos. Formato del cuerpo de la solicitud:
```
{
    "name": "Carlos",
    "lastName": "Martinez",
    "mail": "carlos@powerfitness.com",
    "specialty": "Crossfit",
  }
```
El ID se generará automáticamente, por lo que no es necesario ingresar un ID desde el JSON.
- Responses:
	- `400 Bad Request` Si ingresamos mal o nos falta completar un campo, devolverá el campo del JSON con error.
	- `201 Created` Entrenador creado en la base de datos.

### POST `/api/coach/{gymId}`
Crea un entrenador y lo asigna a un gimnasio.
Ejemplo: `/api/coach/1`
```
{
    "name": "Carlos",
    "lastName": "Martinez",
    "mail": "carlos@powerfitness.com",
    "specialty": "Crossfit"
  }
```
El ID se generará automáticamente, por lo que no es necesario ingresar un ID desde el JSON.
- Responses:
	- `400 Bad Request` Si no existe el gimnasio con el ID especificado.
	- `201 Created` Entrenador creado en la base de datos.

### POST `/api/coach/bulk`
Crea múltiples entrenadores en una sola solicitud. Formato del cuerpo:
```
{
    "name": "Laura",
    "lastName": "Garcia",
    "mail": "laura@powerfitness.com",
    "specialty": "Yoga",
    "gymId": 1
  },
  {
    "name": "Robert",
    "lastName": "Johnson",
    "mail": "robert@elitegym.com",
    "specialty": "Boxeo",
    "gymId": 2
  },
  {
    "name": "Sophia",
    "lastName": "Williams",
    "mail": "sophia@elitegym.com",
    "specialty": "Zumba",
    "gymId": 3
  }
```
Los IDs se generará automáticamente, por lo que no es necesario ingresarlos desde el JSON, podemos ingresar en este JSON los gymId, para poder relacionar nuestros entrenadores a un gimnasio, o podemos hacerlo despues manualmente desde el metodo PUT.
- Responses:
	- `400 Bad Request` Si hay errores de validación en los campos.
	- `201 Created` Entrenadores creados en la base de datos.

### PUT `/api/coach/{id}`
Actualiza un entrenador existente.

 Ejemplo: `/api/coach/2`
```
{
    "name": "Robert",
    "lastName": "Johnson",
    "mail": "robert@elitegym.com",
    "specialty": "Entrenador Personal"
}
```
- Responses:
	- `400 Bad Request` Si no existe el entrenador con el ID especificado.
	- `200 OK` Entrenador actualizado.

### PUT `/api/coach/{coachId}/gym/{gymId}`
Asigna un entrenador a un gimnasio.

 Ejemplo: `/api/coach/3/gym/2`
- Responses:
	- `400 Bad Request` Si no existe el entrenador o gimnasio con el ID especificado.
	- `200 OK` Entrenador actualizado.

### DELETE `/api/coach/{id}`
Elimina un entrenador.

 Ejemplo: `/api/coach/2`
- Responses:
	- `400 Bad Request` Si no existe el entrenador con el ID especificado.
	- `200 OK` Entrenador eliminado.

Usuarios (Users):

### GET `/api/user`:
Devuelve una lista paginada en formato JSON de todos los usuarios.

Para configurar la paginación, agregue los siguientes parámetros a la URL:

`?page=0&size=10&sort=id`

Explicacion detallada de los parámetros:
`?page=0` Indica cual pagina mostrar (Comienza desde 0).
`?size=10`  Especifica la cantidad de elementos por página.
`?sort=id` Define el campo por el cual se ordenarán los resultados.
- Responses:
	- `200 OK`  Devuelve la lista paginada de los usuarios.

### GET `/api/user/{id}`
Devuelve un JSON con la información del usuario con el ID especificado.
- Responses:
	- `400 Bad Request` Si no existe usuario con el id ingresado.
	- `200 OK` Devuelve el usuario perteneciente al id.

### POST `/api/user`
Permite crear un nuevo objeto user en la base de datos. Formato del cuerpo de la solicitud:
```
{
    "name": "Ana",
    "lastName": "Lopez",
    "mail": "ana.lopez@example.com"
}
```
El ID se generará automáticamente, por lo que no es necesario ingresar un ID desde el JSON.
- Responses:
	- `400 Bad Request` Si ingresamos mal o nos falta completar un campo, devolverá el campo del JSON con error.
	- `201 Created` Usuario creado en la base de datos.

### POST `/api/user/{coachId}`
Crea un usuario y lo asigna a un entrenador.
```
{
    "name": "Oscar",
    "lastName": "Jimenez",
    "mail": "oscar.jimenez@example.com"
}
```
El ID se generará automáticamente, por lo que no es necesario ingresar un ID desde el JSON.
- Responses:
	- `400 Bad Request` Si no existe el entrenador con el ID especificado.
	- `201 Created` Usuario creado en la base de datos.

### POST `/api/user/bulk`
Crea múltiples usuarios en una sola solicitud. Formato del cuerpo:
```
{
    "name": "David",
    "lastName": "Gonzalez",
    "mail": "davidgonzalez@example.com",
    "coachId": 1
  },
  {
    "name": "Isabel",
    "lastName": "Rodriguez",
    "mail": "isabel.rodriguez@example.com",
    "coachId": 2
  },
  {
    "name": "Juan",
    "lastName": "Perez",
    "mail": "juanperez@example.com",
    "coachId": 3
  },
  {
    "name": "Maria",
    "lastName": "Sanchez",
    "mail": "mariasanchez@example.com",
    "coachId": 4
  }
```
Los IDs se generará automáticamente, por lo que no es necesario ingresarlos desde el JSON, podemos ingresar en este JSON los coachId, para poder relacionar nuestros usuarios a un entrenador, o podemos hacerlo despues manualmente desde el metodo PUT.
- Responses:
	- `400 Bad Request` Si hay errores de validación en los campos.
	- `201 Created` Usuarios creados en la base de datos.

### PUT `/api/user/{id}`
Actualiza un usuario existente.

 Ejemplo: `/api/coach/2`
```
{
    "name": "Robert",
    "lastName": "Johnson",
    "mail": "robert@elitegym.com",
    "specialty": "Entrenador Personal"
}
```
- Responses:
	- `400 Bad Request` Si no existe el usuario con el ID especificado.
	- `200 OK` Usuario actualizado.

### PUT `/api/user/{userId}/coach/{coachId}`
Asigna un usuario a un entrenador

 Ejemplo: `/api/user/1/coach/3`
- Responses:
	- `400 Bad Request` Si no existe el usuario o el entrenador con el ID especificado.
	- `200 OK` Usuario actualizado.

### DELETE `/api/user/{id}`
Elimina un usuario.

 Ejemplo: `/api/user/4`
- Responses:
	- `400 Bad Request` Si no existe el usuario con el ID especificado.
	- `200 OK` Usuario eliminado.

## Requisitos
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Postman o cualquier cliente para probar la API.

## Instalación
1. Clonar el repositorio:
```
git clone https://github.com/Alexander-Sot23/JavaSpringCRUD-Relaciones.git
cd JavaSpringCRUD-Relaciones
```
2.	Configurar las propiedades de la aplicación en `application.properties`.
```
spring.datasource.url=jdbc/:mysql://localhost:3306/tu_basedatos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
3.	Construir y ejecutar la aplicación:
```
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`
