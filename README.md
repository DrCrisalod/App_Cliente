# API de Gestión de Clientes

```markdown
Esta API permite gestionar la información de clientes, incluyendo operaciones CRUD básicas.

## Diagrama del Proyecto

```plaintext
+-------------------------------------+
|            Cliente API              |
+-------------------------------------+
|                                     |
|   +---------------+                 |
|   |   Controller  |<--- Maneja las  |
|   |   (REST API)  |    solicitudes  |
|   +-------+-------+                 |
|           |                         |
|   +-------+-------+                 |
|   |   Service     |<--- Contiene    |
|   |   (Logic)     |    la lógica de |
|   +-------+-------+    negocio      |
|           |                         |
|   +-------+-------+                 |
|   |   Repository  |<--- Interactúa  |
|   |   (JPA)       |    con la DB    |
|   +-------+-------+                 |
|           |                         |
|   +-------+-------+                 |
|   |   Database    |<--- Almacena    |
|   |   (MySQL)     |    los datos    |
|   +---------------+                 |
|                                     |
+-------------------------------------+
```

## Estructura del Proyecto

```plaintext
cliente-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── clienteapi
│   │   │               ├── ClienteApiApplication.java
│   │   │               ├── controller
│   │   │               │   └── ClienteController.java
│   │   │               ├── service
│   │   │               │   └── ClienteService.java
│   │   │               ├── repository
│   │   │               │   └── ClienteRepository.java
│   │   │               ├── model
│   │   │               │   └── Cliente.java
│   │   │               └── config
│   │   │                   └── SwaggerConfig.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── clienteapi
│                       └── ClienteApiApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

```

## Configuración del Proyecto

### Dependencias

- Java 11+
- Spring Boot
- MySQL

### Configuración de la Base de Datos

Asegúrate de tener una instancia de MySQL en ejecución y configura las credenciales en `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clientesdb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Scripts SQL

Ejecuta los siguientes scripts para crear las tablas necesarias y agregar datos iniciales:

#### Creación de Tablas

```sql
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
```

#### Datos Iniciales

```sql
INSERT INTO cliente (nombre, apellido, email) VALUES ('Juan', 'Pérez', 'juan.perez@example.com');
INSERT INTO cliente (nombre, apellido, email) VALUES ('Ana', 'García', 'ana.garcia@example.com');
```

### Ejecución del Proyecto

Para ejecutar el proyecto, usa el siguiente comando:

```bash
./mvnw spring-boot:run
```

### Documentación de la API

La documentación de la API está disponible en `http://localhost:8080/swagger-ui/`.

### Pruebas

Para ejecutar las pruebas unitarias, usa el siguiente comando:

```bash
./mvnw test
```

## Endpoints de la API

## Endpoints de la API
```
| Método | Endpoint              | Descripción                                |
|--------|-----------------------|--------------------------------------------|
| GET    | `/api/clientes`       | Obtener todos los clientes                 |
| GET    | `/api/clientes/{id}`  | Obtener un cliente por ID                  |
| POST   | `/api/clientes`       | Crear un nuevo cliente                     |
| PUT    | `/api/clientes/{id}`  | Actualizar un cliente existente            |
| DELETE | `/api/clientes/{id}`  | Eliminar un cliente                        |

```
### Ejemplos de Uso

#### Obtener Todos los Clientes

```
GET /api/clientes
```

Respuesta:
```json
[
  {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan.perez@example.com"
  },
  {
    "id": 2,
    "nombre": "Ana",
    "apellido": "García",
    "email": "ana.garcia@example.com"
  }
]
```

#### Obtener un Cliente por ID

```
GET /api/clientes/1
```

Respuesta:
```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@example.com"
}
```

#### Crear un Nuevo Cliente

```
POST /api/clientes
```

Request Body:
```json
{
  "nombre": "Carlos",
  "apellido": "López",
  "email": "carlos.lopez@example.com"
}
```

Respuesta:
```json
{
  "id": 3,
  "nombre": "Carlos",
  "apellido": "López",
  "email": "carlos.lopez@example.com"
}
```

#### Actualizar un Cliente Existente

```
PUT /api/clientes/1
```

Request Body:
```json
{
  "nombre": "Juan Carlos",
  "apellido": "Pérez",
  "email": "juan.carlos.perez@example.com"
}
```

Respuesta:
```json
{
  "id": 1,
  "nombre": "Juan Carlos",
  "apellido": "Pérez",
  "email": "juan.carlos.perez@example.com"
}
```

#### Eliminar un Cliente

```
DELETE /api/clientes/1
```

Respuesta:
```plaintext
No Content
```

---

## Código del Proyecto

### ClienteApiApplication.java

```java
package com.example.clienteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClienteApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteApiApplication.class, args);
    }
}
```

### Cliente.java

```java
package com.example.clienteapi.model;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

    // Getters y Setters
}
```

### ClienteRepository.java

```java
package com.example.clienteapi.repository;

import com.example.clienteapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
```

### ClienteService.java

```java
package com.example.clienteapi.service;

import com.example.clienteapi.model.Cliente;
import com.example.clienteapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setApellido(clienteDetails.getApellido());
        cliente.setEmail(clienteDetails.getEmail());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        clienteRepository.delete(cliente);
    }
}
```

### ClienteController.java

```java
package com.example.clienteapi.controller;

import com.example.clienteapi.model.Cliente;
import com.example.clienteapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id).orElseThrow();
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
```

### ClienteApiApplicationTests.java

```java
package com.example.clienteapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteApiApplicationTests {

    @Test
    void contextLoads() {
    }
}
```

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clientesdb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### schema.sql

```sql
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO cliente (nombre, apellido, email) VALUES ('Juan', 'Pérez', 'juan.perez@example.com');
INSERT INTO cliente (nombre, apellido, email) VALUES ('Ana', 'García', 'ana.garcia@example.com');
```
```

