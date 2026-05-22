# EloCheck API - Verificador de Rendimiento de Equipos

## 📋 Descripción

**EloCheck** es una aplicación web desarrollada con **Spring Boot** que permite a los usuarios verificar si sus equipos (computadoras) cumplen con los requisitos mínimos y recomendados para ejecutar videojuegos específicos. 

Además, integra información del clima en tiempo real a través de la **API de Open-Meteo**.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.0+**
- **Spring Data JPA + Hibernate**
- **MySQL 8.0+**
- **Maven**
- **Lombok** (para DTOs y modelos)
- **WebClient** (para consumir APIs externas)

---

## 📦 Requisitos Previos

1. **Java JDK 17+** instalado
2. **MySQL Server** instalado y ejecutándose
3. **Maven** instalado
4. **IDE** (Eclipse, IntelliJ IDEA, VS Code con Java extensions)
5. **Git** para clonar el repositorio

---

## ⚙️ Configuración Inicial

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Migueldinho/EloCheck.git
cd EloCheck
```
### 2. Configurar application.properties

Abre `src/main/resources/application.properties` y configura:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/elocheck
spring.datasource.username=root
spring.datasource.password=tu_contrasena_mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8080

# Weather API Configuration (Open-Meteo)
openmeteo.api.url=https://api.open-meteo.com/v1
openmeteo.default-latitude=-33.4489
openmeteo.default-longitude=-70.6693

# Error Handling
server.error.include-stacktrace=always
server.error.include-message=always
```

### 3. Compilar y Ejecutar

```bash
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

O desde tu IDE:
- Clic derecho en el proyecto → "Run As" → "Spring Boot App"

La aplicación estará disponible en: **http://localhost:8080**

---

## 📊 Estructura del Proyecto

```
EloCheck/
├── src/main/java/com/duoc/EloCheck/
│   ├── controller/
│   │   ├── JuegoController.java
│   │   ├── UsuarioController.java
│   │   ├── EquipoController.java
│   │   ├── RequisitoController.java
│   │   └── WeatherController.java
│   ├── service/
│   │   ├── JuegoService.java
│   │   ├── UsuarioService.java
│   │   ├── EquipoService.java
│   │   ├── RequisitoService.java
│   │   └── WeatherService.java
│   ├── repository/
│   │   ├── JuegoRepository.java
│   │   ├── UsuarioRepository.java
│   │   ├── EquipoRepository.java
│   │   └── RequisitoRepository.java
│   ├── model/
│   │   ├── Juego.java
│   │   ├── Usuario.java
│   │   ├── Equipo.java
│   │   └── Requisito.java
│   ├── dto/
│   │   ├── UsuarioHardwareDto.java
│   │   ├── NombreEloDto.java
│   │   └── ResponseWeatherDto.java
│   ├── exception/
│   │   └── GlobalExceptionHandler.java
│   └── EloCheckApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## 🔌 Endpoints de la API

### 📁 **JUEGOS** - `/api/juegos`

#### 1. Obtener todos los juegos
```http
GET http://localhost:8080/api/juegos
```

**Respuesta (200):**
```json
[
  {
    "id": 1,
    "juegoNombre": "Cyberpunk 2077",
    "requisitoMinimo": {
      "id": 1,
      "ram": 8,
      "procesador": "Intel i5",
      "grafica": "RTX 2070",
      "espacio": 150,
      "elo": "ALTO"
    },
    "requisitoRecomendado": {
      "id": 2,
      "ram": 16,
      "procesador": "Intel i7",
      "grafica": "RTX 3080",
      "espacio": 150,
      "elo": "MUY_ALTO"
    }
  }
]
```

---

#### 2. Obtener juego por ID
```http
GET http://localhost:8080/api/juegos/1
```

**Respuesta (200):**
```json
{
  "id": 1,
  "juegoNombre": "Cyberpunk 2077",
  "requisitoMinimo": { ... },
  "requisitoRecomendado": { ... }
}
```

---

#### 3. Obtener nombres de todos los juegos
```http
GET http://localhost:8080/api/juegos/nombres
```

**Respuesta (200):**
```json
[
  "Cyberpunk 2077",
  "Elden Ring",
  "StarField"
]
```

---

#### 4. Crear nuevo juego
```http
POST http://localhost:8080/api/juegos
Content-Type: application/json

{
  "juegoNombre": "Black Myth Wukong",
  "requisitoMinimo": {
    "ram": 16,
    "procesador": "Intel i7",
    "grafica": "RTX 4070",
    "espacio": 140,
    "elo": "MUY_ALTO"
  },
  "requisitoRecomendado": {
    "ram": 32,
    "procesador": "Intel i9",
    "grafica": "RTX 4090",
    "espacio": 140,
    "elo": "EXTREMO"
  }
}
```

**Respuesta (201):**
```json
{
  "id": 3,
  "juegoNombre": "Black Myth Wukong",
  "requisitoMinimo": { ... },
  "requisitoRecomendado": { ... }
}
```

---

#### 5. Eliminar juego
```http
DELETE http://localhost:8080/api/juegos/1
```

**Respuesta (204 - No Content)**

---

### 📁 **EQUIPOS** - `/api/equipos`

#### 1. Obtener todos los equipos
```http
GET http://localhost:8080/api/equipos
```

**Respuesta (200):**
```json
[
  {
    "id": 1,
    "nombreEquipo": "Mi PC Gaming",
    "ram": 16,
    "grafica": "RTX 3070",
    "procesador": "Intel i7-11700K",
    "elo": "ALTO"
  }
]
```

---

#### 2. Obtener equipo por ID
```http
GET http://localhost:8080/api/equipos/1
```

**Respuesta (200):**
```json
{
  "id": 1,
  "nombreEquipo": "Mi PC Gaming",
  "ram": 16,
  "grafica": "RTX 3070",
  "procesador": "Intel i7-11700K",
  "elo": "ALTO"
}
```

---

#### 3. Crear nuevo equipo
```http
POST http://localhost:8080/api/equipos
Content-Type: application/json

{
  "nombreEquipo": "PC Portátil ASUS",
  "ram": 32,
  "grafica": "RTX 4080",
  "procesador": "Intel i9-13900K",
  "elo": "EXTREMO"
}
```

**Respuesta (201):**
```json
{
  "id": 2,
  "nombreEquipo": "PC Portátil ASUS",
  "ram": 32,
  "grafica": "RTX 4080",
  "procesador": "Intel i9-13900K",
  "elo": "EXTREMO"
}
```

---

#### 4. Actualizar equipo
```http
PUT http://localhost:8080/api/equipos/1
Content-Type: application/json

{
  "nombreEquipo": "Mi PC Gaming Actualizada",
  "ram": 32,
  "grafica": "RTX 4070 Super",
  "procesador": "Intel i9-13900K",
  "elo": "MUY_ALTO"
}
```

**Respuesta (200):**
```json
{
  "id": 1,
  "nombreEquipo": "Mi PC Gaming Actualizada",
  "ram": 32,
  "grafica": "RTX 4070 Super",
  "procesador": "Intel i9-13900K",
  "elo": "MUY_ALTO"
}
```

---

#### 5. Eliminar equipo
```http
DELETE http://localhost:8080/api/equipos/1
```

**Respuesta (204 - No Content)**

---

### 📁 **REQUISITOS** - `/api/requisitos`

#### 1. Obtener todos los requisitos
```http
GET http://localhost:8080/api/requisitos
```

**Respuesta (200):**
```json
[
  {
    "id": 1,
    "ram": 8,
    "procesador": "Intel i5",
    "grafica": "RTX 2070",
    "espacio": 100,
    "elo": "BAJO"
  }
]
```

---

#### 2. Obtener requisito por ID
```http
GET http://localhost:8080/api/requisitos/1
```

**Respuesta (200):**
```json
{
  "id": 1,
  "ram": 8,
  "procesador": "Intel i5",
  "grafica": "RTX 2070",
  "espacio": 100,
  "elo": "BAJO"
}
```

---

#### 3. Crear nuevo requisito
```http
POST http://localhost:8080/api/requisitos
Content-Type: application/json

{
  "ram": 24,
  "procesador": "Intel i9",
  "grafica": "RTX 4090",
  "espacio": 200,
  "elo": "EXTREMO"
}
```

**Respuesta (201):**
```json
{
  "id": 5,
  "ram": 24,
  "procesador": "Intel i9",
  "grafica": "RTX 4090",
  "espacio": 200,
  "elo": "EXTREMO"
}
```

---

#### 4. Actualizar requisito
```http
PUT http://localhost:8080/api/requisitos/1
Content-Type: application/json

{
  "ram": 16,
  "procesador": "Intel i7",
  "grafica": "RTX 3080",
  "espacio": 150,
  "elo": "ALTO"
}
```

**Respuesta (200):**
```json
{
  "id": 1,
  "ram": 16,
  "procesador": "Intel i7",
  "grafica": "RTX 3080",
  "espacio": 150,
  "elo": "ALTO"
}
```

---

#### 5. Eliminar requisito
```http
DELETE http://localhost:8080/api/requisitos/1
```

**Respuesta (204 - No Content)**

---

### 📁 **USUARIOS** - `/api/usuarios`

#### 1. Obtener todos los usuarios con hardware
```http
GET http://localhost:8080/api/usuarios
```

**Respuesta (200):**
```json
[
  {
    "nombre": "Sebastian",
    "email": "sebastian@example.com",
    "nombreEquipo": "Mi PC Gaming",
    "ram": 16,
    "grafica": "RTX 3070",
    "procesador": "Intel i7-11700K",
    "elo": "ALTO"
  }
]
```

---

#### 2. Obtener usuario por ID
```http
GET http://localhost:8080/api/usuarios/1
```

**Respuesta (200):**
```json
{
  "nombre": "Sebastian",
  "email": "sebastian@example.com",
  "nombreEquipo": "Mi PC Gaming",
  "ram": 16,
  "grafica": "RTX 3070",
  "procesador": "Intel i7-11700K",
  "elo": "ALTO"
}
```

---

#### 3. Crear nuevo usuario
```http
POST http://localhost:8080/api/usuarios
Content-Type: application/json

{
  "nombre": "Miguel",
  "clave": "password123",
  "email": "miguel@example.com",
  "hardware": {
    "id": 1
  }
}
```

**Respuesta (201):**
```json
{
  "id": 2,
  "nombre": "Miguel",
  "clave": "password123",
  "email": "miguel@example.com",
  "hardware": {
    "id": 1,
    "nombreEquipo": "Mi PC Gaming",
    "ram": 16,
    "grafica": "RTX 3070",
    "procesador": "Intel i7-11700K",
    "elo": "ALTO"
  }
}
```

---

#### 4. Obtener usuarios con su ELO
```http
GET http://localhost:8080/api/usuarios/con-elo
```

**Respuesta (200):**
```json
[
  {
    "nombre": "Sebastian",
    "nombreEquipo": "Mi PC Gaming",
    "elo": "ALTO"
  },
  {
    "nombre": "Miguel",
    "nombreEquipo": "PC Portátil",
    "elo": "MUY_ALTO"
  }
]
```

---

#### 5. Eliminar usuario
```http
DELETE http://localhost:8080/api/usuarios/1
```

**Respuesta (204 - No Content)**

---

### 📁 **CLIMA / WEATHER** - `/api/weather`

#### 1. Obtener clima actual (coordenadas por defecto)
```http
GET http://localhost:8080/api/weather/current
```

**Respuesta (200):**
```json
{
  "latitude": -33.4489,
  "longitude": -70.6693,
  "currentWeather": {
    "temperature": 22.5,
    "windspeed": 12.3,
    "weathercode": 1,
    "time": "2026-05-22T20:30:00Z"
  }
}
```

---

#### 2. Obtener clima en coordenadas personalizadas
```http
GET http://localhost:8080/api/weather/current/custom?latitude=-33.8688&longitude=-51.5350
```

**Respuesta (200):**
```json
{
  "latitude": -33.8688,
  "longitude": -51.5350,
  "currentWeather": {
    "temperature": 18.0,
    "windspeed": 15.2,
    "weathercode": 2,
    "time": "2026-05-22T20:30:00Z"
  }
}
```

---

## 📊 Ejemplo de Flujo Completo de Logs

Cuando haces un request, verás en la consola:

```
[CONTROLLER] GET /api/equipos - Iniciando
[SERVICE] Obteniendo todos los equipos
[SERVICE] Total de equipos: 3
[CONTROLLER] Equipos obtenidos: 3

---

[CONTROLLER] POST /api/equipos - Creando: Mi PC Gaming
[SERVICE] Creando equipo: Mi PC Gaming
[SERVICE] Equipo guardado con id: 5
[CONTROLLER] Equipo creado con id: 5

---

[CONTROLLER] DELETE /api/equipos/1
[SERVICE] Eliminando equipo con id: 1
[SERVICE] Equipo eliminado correctamente
[CONTROLLER] Equipo eliminado
```

---

## ⚠️ Manejo de Excepciones

### Error 404 - Recurso No Encontrado
```
GET http://localhost:8080/api/equipos/999
```

**Respuesta (404):**
```
Recurso no encontrado
```

**Consola:**
```
[ADVERTENCIA] Recurso no encontrado: /api/equipos/999
```

---

### Error 400 - Solicitud Inválida
```
POST http://localhost:8080/api/equipos
Content-Type: application/json

{ "nombreEquipo": "" }
```

**Respuesta (400):**
```json
"Error: validation failed"
```

**Consola:**
```
[ERROR] RuntimeException: Validation error
```

---

### Error 500 - Error Interno del Servidor
```
GET http://localhost:8080/api/equipos/abc
```

**Respuesta (500):**
```json
"Error: For input string: \"abc\""
```

**Consola:**
```
[ERROR] Excepción capturada: For input string: "abc"
[ERROR] Tipo: java.lang.NumberFormatException
java.lang.NumberFormatException: For input string: "abc"
	at java.lang.Integer.parseInt(...)
```

---

## 🧪 Pruebas Manuales

### Prueba 1: Crear un Juego Completo

```bash
# 1. Crear requisitos mínimos
curl -X POST http://localhost:8080/api/requisitos \
  -H "Content-Type: application/json" \
  -d '{
    "ram": 8,
    "procesador": "Intel i5",
    "grafica": "RTX 2070",
    "espacio": 100,
    "elo": "BAJO"
  }'

# 2. Crear requisitos recomendados
curl -X POST http://localhost:8080/api/requisitos \
  -H "Content-Type: application/json" \
  -d '{
    "ram": 16,
    "procesador": "Intel i7",
    "grafica": "RTX 3080",
    "espacio": 150,
    "elo": "ALTO"
  }'

# 3. Crear juego con requisitos
curl -X POST http://localhost:8080/api/juegos \
  -H "Content-Type: application/json" \
  -d '{
    "juegoNombre": "The Witcher 3",
    "requisitoMinimo": { "id": 1 },
    "requisitoRecomendado": { "id": 2 }
  }'

# 4. Verificar el juego creado
curl http://localhost:8080/api/juegos/1
```

---

### Prueba 2: Crear Usuario con Equipo

```bash
# 1. Crear equipo
curl -X POST http://localhost:8080/api/equipos \
  -H "Content-Type: application/json" \
  -d '{
    "nombreEquipo": "Mi PC Gaming",
    "ram": 32,
    "grafica": "RTX 4070",
    "procesador": "Intel i9-13900K",
    "elo": "MUY_ALTO"
  }'

# 2. Crear usuario con el equipo
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Sebastian",
    "clave": "pass123",
    "email": "sebastian@example.com",
    "hardware": { "id": 1 }
  }'

# 3. Obtener usuario completo
curl http://localhost:8080/api/usuarios/1
```

---

## 🐛 Solución de Problemas

### Problema: "Access denied for user 'root'@'localhost'"
**Solución:** Verifica las credenciales en `application.properties`
```properties
spring.datasource.username=root
spring.datasource.password=tu_contrasena_correcta
```

---

### Problema: "Port 8080 already in use"
**Solución:** Cambia el puerto en `application.properties`
```properties
server.port=8081
```

## 📚 Estructura de DTOs

### UsuarioHardwareDto
```java
public class UsuarioHardwareDto {
    private String nombre;
    private String email;
    private String nombreEquipo;
    private Integer ram;
    private String grafica;
    private String procesador;
    private String elo;
}
```

### NombreEloDto
```java
public class NombreEloDto {
    private String nombre;
    private String nombreEquipo;
    private String elo;
}
```

### ResponseWeatherDto
```java
public class ResponseWeatherDto {
    private Double latitude;
    private Double longitude;
    private CurrentWeather currentWeather;
}
```

## 👥 Autores

- **Miguel Gutierrez (Migueldinho):** [GitHub](https://github.com/Migueldinho)
- **Sebastian Ramirez (FlayingWolf):** [GitHub](https://github.com/FlayingWolf)

