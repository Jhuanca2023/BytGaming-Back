# 🚀 Pasos Simples para Render con PostgreSQL

## ✅ Render tiene PostgreSQL (no MySQL)

Render **NO tiene MySQL**. Solo tiene **PostgreSQL**. Tu aplicación ya está configurada para usar PostgreSQL.

## 📋 Pasos Rápidos

### 1. Crear Base de Datos PostgreSQL

1. En Render Dashboard: **"New +"** → **"Postgres"**
2. Configura:
   - **Name**: `bytgaming-db`
   - **Plan**: **Free**
   - **Region**: Oregon
3. Click en **"Create Database"**
4. Espera 2-3 minutos

### 2. Crear Servicio Web

1. En Render Dashboard: **"New +"** → **"Web Service"**
2. Conecta el repositorio `BytGaming-Back`
3. Configura:
   - **Environment**: **Docker**
   - **Root Directory**: `BytGaming`
   - **Dockerfile Path**: `BytGaming/Dockerfile`

### 3. Conectar la Base de Datos

**IMPORTANTE:** En la configuración del servicio web:

1. Busca **"Link Database"** o **"Add Database"**
2. Selecciona la base de datos `bytgaming-db`
3. Render añadirá automáticamente:
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`

### 4. Añadir Variables de Entorno

Solo añade estas variables (las de base de datos ya están):

```env
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000
```

### 5. Activar Auto-Deploy

- Activa **"Auto-Deploy"**
- Selecciona la rama **`main`**

### 6. Crear el Servicio

1. Click en **"Create Web Service"**
2. Espera 5-10 minutos
3. ¡Listo! 🎉

## ✅ Verificar

Una vez completado:
- URL: `https://bytgaming-backend.onrender.com`
- Swagger: `https://bytgaming-backend.onrender.com/swagger-ui.html`

## 🎯 Puntos Clave

1. ✅ Render tiene **PostgreSQL**, no MySQL
2. ✅ La aplicación ya soporta PostgreSQL
3. ✅ Conecta la base de datos al servicio (Link Database)
4. ✅ Render proporciona las variables automáticamente
5. ✅ Solo necesitas añadir las variables JWT

## 🆘 Si hay problemas

1. Verifica que la base de datos está conectada al servicio
2. Revisa los logs en Render
3. Verifica las variables de entorno
4. Consulta `GUIA_RENDER_POSTGRESQL.md` para más detalles

