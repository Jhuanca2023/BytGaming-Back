# BytGaming Backend

Backend del proyecto BytGaming desarrollado con Spring Boot 3.4.4, MySQL y Spring Security con JWT.

## 🚀 Características

- Spring Boot 3.4.4
- Spring Security con JWT
- MySQL Database
- Spring Data JPA
- Cloudinary para gestión de imágenes
- Swagger/OpenAPI para documentación
- Docker y Docker Compose

## 📋 Prerrequisitos

- Java 17
- Maven 3.8+
- MySQL 8.0+
- Docker (opcional)

## 🔧 Configuración Local

### 1. Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
DB_URL=jdbc:mysql://localhost:3306/bygaming?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=bytgaming
DB_PASSWORD=bytgaming123
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

### 2. Base de Datos

#### Opción A: Docker Compose (Recomendado)

```bash
cd Back
docker-compose up -d
```

Esto iniciará MySQL en el puerto 3306.

#### Opción B: MySQL Local

1. Crea una base de datos:
```sql
CREATE DATABASE bygaming;
```

2. Configura las credenciales en `application.properties` o variables de entorno.

### 3. Ejecutar la Aplicación

```bash
cd Back/BytGaming
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 🐳 Docker

### Construir la imagen

```bash
cd Back/BytGaming
docker build -t bytgaming-backend .
```

### Ejecutar con Docker Compose

```bash
cd Back
docker-compose up
```

## 🚢 Despliegue en Railway

### 1. Configurar Railway

1. Ve a [Railway](https://railway.app)
2. Crea un nuevo proyecto
3. Conecta tu repositorio de GitHub
4. Añade un servicio MySQL

### 2. Variables de Entorno en Railway

Configura las siguientes variables de entorno en Railway:

```
DB_URL=jdbc:mysql://[MYSQL_HOST]:[MYSQL_PORT]/bygaming?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=[MYSQL_USER]
DB_PASSWORD=[MYSQL_PASSWORD]
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=8080
```

### 3. Secrets de GitHub

Configura los siguientes secrets en GitHub para el workflow de deployment:

- `RAILWAY_TOKEN`: Token de autenticación de Railway
- `RAILWAY_PROJECT_ID`: ID del proyecto en Railway (opcional)
- `RAILWAY_SERVICE_ID`: ID del servicio en Railway (opcional)

### 4. Obtener Railway Token

```bash
npm install -g @railway/cli
railway login
railway link
railway variables
```

Para obtener el token:
1. Ve a Railway Dashboard
2. Settings → Tokens
3. Genera un nuevo token
4. Añádelo como secret en GitHub

## 📚 API Documentation

Una vez desplegada la aplicación, la documentación Swagger estará disponible en:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

## 🔐 Endpoints Principales

### Autenticación
- `POST /auth/sign` - Registrar usuario
- `POST /auth/login` - Iniciar sesión
- `PUT /auth/{id}` - Actualizar usuario

### Productos
- `GET /api/products` - Listar productos
- `GET /api/products/{id}` - Obtener producto
- `POST /api/products` - Crear producto
- `PUT /api/products/{id}` - Actualizar producto
- `DELETE /api/products/{id}` - Eliminar producto

### Carrito
- `GET /api/cart` - Obtener carrito
- `POST /api/cart` - Crear carrito
- `POST /api/cart/{productId}` - Añadir producto al carrito
- `DELETE /api/cart/{productId}` - Eliminar producto del carrito

### Órdenes
- `GET /api/orders` - Listar órdenes
- `POST /api/orders/cart` - Crear orden desde carrito
- `DELETE /api/orders/{id}` - Eliminar orden

## 🗄️ Estructura del Proyecto

```
Back/
├── BytGaming/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── gaming/pe/
│   │       │       ├── config/       # Configuraciones
│   │       │       ├── Controller/   # Controladores REST
│   │       │       ├── Entity/       # Entidades JPA
│   │       │       ├── Repository/   # Repositorios
│   │       │       ├── Service/      # Servicios
│   │       │       └── DTO/          # Data Transfer Objects
│   │       └── resources/
│   │           └── application.properties
│   └── Dockerfile
├── gateway/                          # Spring Cloud Gateway
└── docker-compose.yml
```

## 🔄 Workflow de Deployment

El proyecto incluye un workflow de GitHub Actions que automáticamente:

1. Construye la aplicación
2. Despliega a Railway cuando se hace push a `main` o `master`

## 📝 Notas

- La base de datos se crea automáticamente si no existe
- Las migraciones se ejecutan automáticamente con `ddl-auto=update`
- El JWT tiene una expiración de 30 minutos (1800000 ms)

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia Apache 2.0.
