# 🐘 Despliegue en Render con PostgreSQL

## ✅ Render usa PostgreSQL (no MySQL)

Render ofrece **PostgreSQL** en su plan gratuito. La buena noticia es que tu aplicación Spring Boot ya está configurada para soportar PostgreSQL.

## 🎯 Cambios Realizados

### 1. ✅ Dependencia de PostgreSQL añadida
- Añadida `postgresql` al `pom.xml`
- La aplicación ahora soporta tanto MySQL como PostgreSQL

### 2. ✅ Configuración actualizada
- `application.properties` actualizado para detectar automáticamente PostgreSQL
- Soporta variables de entorno de Render (`DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`)
- También soporta variables de MySQL (`DB_URL`, `DB_USER`, `DB_PASSWORD`)

### 3. ✅ Detección automática
- Spring Boot detecta automáticamente el tipo de base de datos desde la URL
- Si la URL contiene `postgresql://` → usa PostgreSQL
- Si la URL contiene `mysql://` → usa MySQL

## 📋 Pasos para Desplegar

### Paso 1: Crear Base de Datos PostgreSQL en Render

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Postgres"** (no MySQL, Render no tiene MySQL)
3. Configura:
   - **Name**: `bytgaming-db`
   - **Database**: `bytgaming`
   - **User**: `bytgaming`
   - **Plan**: **Free**
   - **Region**: Oregon
4. Click en **"Create Database"**
5. Espera 2-3 minutos

### Paso 2: Crear Servicio Web

1. Click en **"New +"** → **"Web Service"**
2. Conecta el repositorio `BytGaming-Back`
3. Configura:
   - **Environment**: Docker
   - **Root Directory**: `BytGaming`
   - **Dockerfile Path**: `BytGaming/Dockerfile`

### Paso 3: Conectar la Base de Datos

1. En la configuración del servicio, ve a **"Environment"**
2. Busca **"Link Database"** o **"Add Database"**
3. Selecciona la base de datos `bytgaming-db`
4. Render conectará automáticamente y añadirá las variables:
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`

### Paso 4: Configurar Variables de Entorno

Añade solo estas variables (las de base de datos ya están):

```env
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000
```

### Paso 5: Activar Auto-Deploy

- Activa **"Auto-Deploy"**
- Selecciona la rama **`main`**

### Paso 6: Crear el Servicio

1. Click en **"Create Web Service"**
2. Render comenzará a construir y desplegar
3. Espera 5-10 minutos

## ✅ Verificar

Una vez completado:
- URL: `https://bytgaming-backend.onrender.com`
- Swagger: `https://bytgaming-backend.onrender.com/swagger-ui.html`

## 🔧 Formato de DATABASE_URL en Render

Render proporciona `DATABASE_URL` en este formato:
```
postgresql://user:password@host:port/database
```

La aplicación Spring Boot lo detecta automáticamente y lo convierte al formato JDBC:
```
jdbc:postgresql://host:port/database
```

## 📝 Notas

- **PostgreSQL es compatible**: Tu aplicación funciona igual con PostgreSQL
- **Detección automática**: Spring Boot detecta PostgreSQL automáticamente
- **Sin cambios de código**: No necesitas cambiar el código Java
- **Variables automáticas**: Render proporciona las variables de base de datos automáticamente

## 🐛 Solución de Problemas

### Error: "Cannot connect to database"
**Solución:**
- Verifica que la base de datos está conectada al servicio (Link Database)
- Verifica que las variables `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD` están presentes
- Revisa los logs en Render

### Error: "Driver not found"
**Solución:**
- La dependencia de PostgreSQL ya está en `pom.xml`
- Verifica que el build incluye todas las dependencias
- Revisa los logs de build

## 🎉 ¡Listo!

La aplicación está lista para usar PostgreSQL en Render. Solo sigue los pasos arriba.

