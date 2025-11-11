# 🔧 Configuración Paso a Paso en Render

## ✅ Configuración del Servicio (NO son variables de entorno)

En la sección **"Settings"** del servicio web, configura:

### 1. Configuración Básica
- **Name**: `bytgaming-backend`
- **Environment**: `Docker` ✅ (Correcto)
- **Region**: `Oregon` (o el más cercano)
- **Branch**: `main`

### 2. Configuración de Build
- **Root Directory**: `BytGaming` ✅ (Correcto)
- **Dockerfile Path**: `BytGaming/Dockerfile` ✅ (Correcto)
- **Docker Context**: `BytGaming` (o déjalo vacío)

### 3. Build & Deploy
- **Build Command**: (Dejar vacío)
- **Start Command**: (Dejar vacío)
- **Auto-Deploy**: `Yes` ✅ (Activar)

---

## 🔗 Conectar la Base de Datos (MUY IMPORTANTE)

### Paso 1: Crear Base de Datos PostgreSQL

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Postgres"**
3. Configura:
   - **Name**: `bytgaming-db`
   - **Database**: `bytgaming`
   - **User**: `bytgaming`
   - **Plan**: **Free**
   - **Region**: Oregon (mismo que el servicio web)
4. Click en **"Create Database"**
5. Espera 2-3 minutos

### Paso 2: Conectar la Base de Datos al Servicio Web

**OPCIÓN A: Link Database (Recomendado - Automático)**

1. Ve a tu servicio web en Render
2. Ve a la pestaña **"Environment"**
3. Busca la sección **"Link Database"** o **"Add Database"**
4. Click en **"Link Database"**
5. Selecciona la base de datos `bytgaming-db`
6. Click en **"Link"**
7. **Render añadirá automáticamente:**
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`

**OPCIÓN B: Añadir Manualmente (Si no puedes usar Link Database)**

1. Ve a tu servicio web en Render
2. Ve a la pestaña **"Environment"**
3. En la sección **"Environment Variables"**, añade:

```env
DATABASE_URL=postgresql://user:password@host:port/database
DATABASE_USERNAME=user
DATABASE_PASSWORD=password
```

**Para obtener estos valores:**
1. Ve a tu base de datos PostgreSQL en Render
2. Ve a la pestaña **"Connections"**
3. Copia la **"Internal Database URL"** o **"External Database URL"**
4. Usa esos valores para las variables de entorno

---

## 🔐 Variables de Entorno (Environment Variables)

Después de conectar la base de datos, añade estas variables en la sección **"Environment Variables"**:

### Variables de Base de Datos (Automáticas si usas Link Database)

Si usaste **"Link Database"**, estas variables ya están añadidas automáticamente:
- ✅ `DATABASE_URL` (Render lo añade automáticamente)
- ✅ `DATABASE_USERNAME` (Render lo añade automáticamente)
- ✅ `DATABASE_PASSWORD` (Render lo añade automáticamente)

### Variables JWT (Debes añadirlas manualmente)

Añade estas variables en la sección **"Environment Variables"**:

| Name | Value |
|------|-------|
| `SECURITY_JWT_KEY_PRIVATE` | `cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43` |
| `SECURITY_JWT_USER_GENERATOR` | `AUTH0JWT-BACKEND` |
| `PORT` | `10000` |

---

## 📋 Checklist Completo

### Configuración del Servicio
- [ ] Name: `bytgaming-backend`
- [ ] Environment: `Docker`
- [ ] Root Directory: `BytGaming`
- [ ] Dockerfile Path: `BytGaming/Dockerfile`
- [ ] Branch: `main`
- [ ] Auto-Deploy: `Yes`

### Base de Datos
- [ ] Base de datos PostgreSQL creada
- [ ] Base de datos conectada al servicio (Link Database)
- [ ] Variables `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD` presentes

### Variables de Entorno
- [ ] `SECURITY_JWT_KEY_PRIVATE` añadida
- [ ] `SECURITY_JWT_USER_GENERATOR` añadida
- [ ] `PORT` añadida (opcional, Render usa 10000 por defecto)

---

## 🎯 Resumen de Dónde Configurar

### 1. Configuración del Servicio (Settings)
- **Environment**: `Docker`
- **Root Directory**: `BytGaming`
- **Dockerfile Path**: `BytGaming/Dockerfile`

### 2. Conectar Base de Datos (Environment → Link Database)
- Click en **"Link Database"**
- Selecciona la base de datos `bytgaming-db`
- Render añade automáticamente las variables de base de datos

### 3. Variables de Entorno (Environment → Environment Variables)
- Añade `SECURITY_JWT_KEY_PRIVATE`
- Añade `SECURITY_JWT_USER_GENERATOR`
- Añade `PORT` (opcional)

---

## 🆘 Problemas Comunes

### Problema: "No veo la opción Link Database"
**Solución:**
1. Asegúrate de que la base de datos PostgreSQL está creada
2. Ve a la pestaña **"Environment"** del servicio web
3. Busca **"Add Database"** o **"Link Database"**
4. Si no aparece, añade las variables manualmente (Opción B arriba)

### Problema: "No sé cómo obtener DATABASE_URL"
**Solución:**
1. Ve a tu base de datos PostgreSQL en Render
2. Ve a la pestaña **"Connections"**
3. Copia la **"Internal Database URL"** (para servicios en la misma región)
4. O copia la **"External Database URL"** (para servicios externos)
5. Usa esa URL como valor de `DATABASE_URL`

### Problema: "Las variables de entorno no se aplican"
**Solución:**
1. Asegúrate de guardar los cambios
2. Reinicia el servicio después de añadir variables
3. Verifica que las variables estén en la pestaña **"Environment"** → **"Environment Variables"**

---

## ✅ Verificación Final

Después de configurar todo:

1. **Verifica las variables de entorno:**
   - Ve a **"Environment"** → **"Environment Variables"**
   - Debe haber al menos:
     - `DATABASE_URL`
     - `DATABASE_USERNAME`
     - `DATABASE_PASSWORD`
     - `SECURITY_JWT_KEY_PRIVATE`
     - `SECURITY_JWT_USER_GENERATOR`
     - `PORT` (opcional)

2. **Verifica la conexión de la base de datos:**
   - En la pestaña **"Environment"**, debe aparecer la base de datos conectada
   - O las variables `DATABASE_*` deben estar presentes

3. **Despliega el servicio:**
   - Click en **"Create Web Service"** o **"Save Changes"**
   - Espera 5-10 minutos
   - Verifica los logs para asegurar que se conectó a la base de datos

---

## 🎉 ¡Listo!

Una vez configurado todo, Render desplegará automáticamente tu aplicación con PostgreSQL.

**URL de tu API:**
- `https://bytgaming-backend.onrender.com`
- Swagger: `https://bytgaming-backend.onrender.com/swagger-ui.html`

