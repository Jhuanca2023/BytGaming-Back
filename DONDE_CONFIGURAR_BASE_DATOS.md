# 📍 Dónde Configurar la Base de Datos en Render

## ✅ Tu Configuración Actual (CORRECTA)

Lo que ya configuraste está **BIEN**, pero son **configuraciones del servicio**, NO variables de entorno:

- ✅ **Environment**: `Docker` (Configuración del servicio)
- ✅ **Root Directory**: `BytGaming` (Configuración del servicio)
- ✅ **Dockerfile Path**: `BytGaming/Dockerfile` (Configuración del servicio)

## 🔗 Dónde Conectar la Base de Datos

### Paso 1: Crear Base de Datos PostgreSQL

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Postgres"** (NO MySQL, Render no tiene MySQL)
3. Configura:
   - **Name**: `bytgaming-db`
   - **Database**: `bytgaming`
   - **User**: `bytgaming`
   - **Plan**: **Free**
   - **Region**: Oregon (o el más cercano)
4. Click en **"Create Database"**
5. Espera 2-3 minutos

### Paso 2: Conectar la Base de Datos al Servicio Web

**OPCIÓN A: Link Database (Si está disponible)**

1. Ve a tu servicio web en Render (el que acabas de crear)
2. Ve a la pestaña **"Environment"** (en la parte superior)
3. Busca la sección **"Link Database"** o **"Add Database"**
4. Click en **"Link Database"** o **"Add Database"**
5. Selecciona la base de datos `bytgaming-db` de la lista
6. Click en **"Link"** o **"Add"**

**Render añadirá automáticamente estas variables:**
- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`

**OPCIÓN B: Añadir Variables Manualmente (Si no hay "Link Database")**

Si no encuentras la opción "Link Database", añade las variables manualmente:

1. Ve a tu servicio web en Render
2. Ve a la pestaña **"Environment"**
3. Busca la sección **"Environment Variables"**
4. Click en **"Add Environment Variable"**
5. Añade estas variables:

**Variable 1: DATABASE_URL**
- **Name:** `DATABASE_URL`
- **Value:** `postgresql://bytgaming_db_user:[CONTRASEÑA]@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db`
  - Reemplaza `[CONTRASEÑA]` con la contraseña real de tu base de datos
  - O usa la "URL interna de la base de datos" que Render te muestra

**Variable 2: DATABASE_USERNAME**
- **Name:** `DATABASE_USERNAME`
- **Value:** `bytgaming_db_user`

**Variable 3: DATABASE_PASSWORD**
- **Name:** `DATABASE_PASSWORD`
- **Value:** La contraseña de tu base de datos (la que está oculta con ••••)

**Ver guía completa en:** `CONFIGURAR_VARIABLES_MANUALMENTE.md`

## 🔐 Variables de Entorno (Environment Variables)

Después de conectar la base de datos, añade estas variables:

### Dónde Añadir Variables de Entorno:

1. En tu servicio web, ve a la pestaña **"Environment"**
2. Busca la sección **"Environment Variables"**
3. Click en **"Add Environment Variable"** o **"Add Variable"**
4. Añade estas 3 variables:

| Name | Value |
|------|-------|
| `SECURITY_JWT_KEY_PRIVATE` | `cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43` |
| `SECURITY_JWT_USER_GENERATOR` | `AUTH0JWT-BACKEND` |
| `PORT` | `10000` |

## 📋 Resumen Visual

```
Render Dashboard
├── Servicio Web (bytgaming-backend)
│   ├── Settings (Configuración del servicio)
│   │   ├── Environment: Docker ✅
│   │   ├── Root Directory: BytGaming ✅
│   │   └── Dockerfile Path: BytGaming/Dockerfile ✅
│   │
│   └── Environment (Pestaña)
│       ├── Link Database (Click aquí para conectar)
│       │   └── Selecciona: bytgaming-db
│       │       └── Render añade automáticamente:
│       │           ├── DATABASE_URL
│       │           ├── DATABASE_USERNAME
│       │           └── DATABASE_PASSWORD
│       │
│       └── Environment Variables (Añade manualmente)
│           ├── SECURITY_JWT_KEY_PRIVATE
│           ├── SECURITY_JWT_USER_GENERATOR
│           └── PORT
│
└── Base de Datos PostgreSQL (bytgaming-db)
    └── Ya creada y lista para conectar
```

## 🎯 Pasos en Orden

1. ✅ **Configuración del servicio** (Ya lo hiciste)
   - Environment: Docker
   - Root Directory: BytGaming
   - Dockerfile Path: BytGaming/Dockerfile

2. ⏳ **Crear base de datos PostgreSQL**
   - "New +" → "Postgres"
   - Name: bytgaming-db
   - Plan: Free

3. ⏳ **Conectar base de datos al servicio**
   - Servicio web → "Environment" → "Link Database"
   - Selecciona bytgaming-db
   - Render añade automáticamente DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD

4. ⏳ **Añadir variables de entorno JWT**
   - Servicio web → "Environment" → "Environment Variables"
   - Añade: SECURITY_JWT_KEY_PRIVATE
   - Añade: SECURITY_JWT_USER_GENERATOR
   - Añade: PORT

5. ⏳ **Activar Auto-Deploy**
   - Settings → Auto-Deploy: Yes
   - Branch: main

6. ⏳ **Crear el servicio**
   - Click en "Create Web Service"
   - Espera 5-10 minutos

## 🆘 Si No Encuentras "Link Database"

### Opción A: Buscar en la Pestaña "Environment"

1. Ve a tu servicio web
2. Click en la pestaña **"Environment"** (arriba)
3. Busca **"Link Database"** o **"Add Database"**
4. Si no aparece, usa la Opción B

### Opción B: Añadir Variables Manualmente

1. Ve a tu base de datos PostgreSQL en Render
2. Ve a la pestaña **"Connections"**
3. Copia la **"Internal Database URL"** (para servicios en la misma región)
4. Ve a tu servicio web → "Environment" → "Environment Variables"
5. Añade estas variables:

| Name | Value (Copia desde la base de datos) |
|------|--------------------------------------|
| `DATABASE_URL` | `postgresql://user:password@host:port/database` |
| `DATABASE_USERNAME` | `user` |
| `DATABASE_PASSWORD` | `password` |

## ✅ Verificación

Después de configurar todo, verifica que tengas estas variables:

1. Ve a tu servicio web → "Environment" → "Environment Variables"
2. Debe haber al menos:
   - ✅ `DATABASE_URL`
   - ✅ `DATABASE_USERNAME`
   - ✅ `DATABASE_PASSWORD`
   - ✅ `SECURITY_JWT_KEY_PRIVATE`
   - ✅ `SECURITY_JWT_USER_GENERATOR`
   - ✅ `PORT` (opcional)

## 🎉 ¡Listo!

Una vez que tengas todas las variables configuradas, Render desplegará automáticamente tu aplicación con PostgreSQL.

**URL de tu API:**
- `https://bytgaming-backend.onrender.com`
- Swagger: `https://bytgaming-backend.onrender.com/swagger-ui.html`

