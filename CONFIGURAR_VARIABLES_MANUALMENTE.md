# 🔧 Configurar Variables de Base de Datos Manualmente en Render

## ✅ Base de Datos Creada

Tu base de datos PostgreSQL está creada correctamente:
- **Nombre**: `bytgaming-db`
- **Host**: `dpg-d497n8qli9vc739n373g-a`
- **Puerto**: `5432`
- **Base de datos**: `bytgaming_db`
- **Usuario**: `bytgaming_db_user`

## 🔐 Añadir Variables de Entorno Manualmente

### Paso 1: Ve a tu Servicio Web

1. En Render Dashboard, ve a tu servicio web (si no lo has creado, créalo primero)
2. Click en el servicio web `bytgaming-backend`

### Paso 2: Ve a la Pestaña "Environment"

1. En la parte superior del servicio, click en la pestaña **"Environment"**
2. Busca la sección **"Environment Variables"**

### Paso 3: Añade las Variables de Base de Datos

Click en **"Add Environment Variable"** y añade estas variables una por una:

#### Variable 1: DATABASE_URL

**Name:** `DATABASE_URL`

**Value:** Construye la URL usando esta fórmula:
```
postgresql://[USUARIO]:[CONTRASEÑA]@[HOST]:[PUERTO]/[BASE_DE_DATOS]
```

Con tus datos:
```
postgresql://bytgaming_db_user:[TU_CONTRASEÑA]@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db
```

**⚠️ IMPORTANTE:** Reemplaza `[TU_CONTRASEÑA]` con la contraseña real que Render te mostró cuando creaste la base de datos.

**O mejor aún:** Si Render te muestra la "URL interna de la base de datos" o "URL de base de datos externa", cópiala directamente y úsala como valor.

#### Variable 2: DATABASE_USERNAME

**Name:** `DATABASE_USERNAME`

**Value:** `bytgaming_db_user`

#### Variable 3: DATABASE_PASSWORD

**Name:** `DATABASE_PASSWORD`

**Value:** La contraseña que Render te mostró cuando creaste la base de datos (la que está oculta con ••••)

### Paso 4: Añade las Variables JWT

Después de añadir las variables de base de datos, añade estas variables JWT:

#### Variable 4: SECURITY_JWT_KEY_PRIVATE

**Name:** `SECURITY_JWT_KEY_PRIVATE`

**Value:** `cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43`

#### Variable 5: SECURITY_JWT_USER_GENERATOR

**Name:** `SECURITY_JWT_USER_GENERATOR`

**Value:** `AUTH0JWT-BACKEND`

#### Variable 6: PORT (Opcional)

**Name:** `PORT`

**Value:** `10000`

## 📋 Lista Completa de Variables

Después de añadir todas las variables, deberías tener:

| Name | Value |
|------|-------|
| `DATABASE_URL` | `postgresql://bytgaming_db_user:[CONTRASEÑA]@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db` |
| `DATABASE_USERNAME` | `bytgaming_db_user` |
| `DATABASE_PASSWORD` | `[TU_CONTRASEÑA]` |
| `SECURITY_JWT_KEY_PRIVATE` | `cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43` |
| `SECURITY_JWT_USER_GENERATOR` | `AUTH0JWT-BACKEND` |
| `PORT` | `10000` |

## 🔍 Cómo Obtener la Contraseña

### Opción 1: Desde la Página de la Base de Datos

1. Ve a tu base de datos PostgreSQL en Render
2. En la sección **"Conexiones"**, busca **"Contraseña"**
3. Click en el ícono de "ojo" o "mostrar" para revelar la contraseña
4. Copia la contraseña

### Opción 2: Desde la URL de la Base de Datos

1. Ve a tu base de datos PostgreSQL en Render
2. En la sección **"Conexiones"**, busca **"URL interna de la base de datos"** o **"URL de base de datos externa"**
3. Click en el ícono de "ojo" o "mostrar" para revelar la URL
4. Copia la URL completa
5. Usa esa URL directamente como valor de `DATABASE_URL`

La URL tiene este formato:
```
postgresql://bytgaming_db_user:CONTRASEÑA@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db
```

## ✅ Verificación

Después de añadir todas las variables:

1. Ve a tu servicio web → "Environment" → "Environment Variables"
2. Verifica que tengas estas 6 variables:
   - ✅ `DATABASE_URL`
   - ✅ `DATABASE_USERNAME`
   - ✅ `DATABASE_PASSWORD`
   - ✅ `SECURITY_JWT_KEY_PRIVATE`
   - ✅ `SECURITY_JWT_USER_GENERATOR`
   - ✅ `PORT`

## 🚀 Desplegar

Una vez que tengas todas las variables configuradas:

1. Ve a la pestaña **"Settings"** del servicio
2. Activa **"Auto-Deploy"** si no está activado
3. Selecciona la rama **`main`**
4. Guarda los cambios
5. Render comenzará a construir y desplegar automáticamente

## 🆘 Si No Puedes Ver la Contraseña

Si no puedes ver la contraseña en Render:

1. Ve a la base de datos PostgreSQL
2. Busca la opción **"Reset Password"** o **"Cambiar contraseña"**
3. Genera una nueva contraseña
4. Anota la nueva contraseña
5. Úsala en las variables de entorno

## 📝 Notas Importantes

1. **URL Interna vs Externa:**
   - Si tu servicio web y base de datos están en la misma región (Oregon), usa la **"URL interna de la base de datos"**
   - Si están en diferentes regiones, usa la **"URL de base de datos externa"**

2. **Formato de la URL:**
   - La URL debe estar en formato: `postgresql://user:password@host:port/database`
   - NO debe tener espacios ni saltos de línea
   - La contraseña puede contener caracteres especiales, asegúrate de copiarla correctamente

3. **Seguridad:**
   - Las variables de entorno están encriptadas en Render
   - No las compartas públicamente
   - Si necesitas cambiar la contraseña, actualiza la variable `DATABASE_PASSWORD`

## 🎉 ¡Listo!

Una vez que tengas todas las variables configuradas, Render desplegará automáticamente tu aplicación con PostgreSQL.

**URL de tu API:**
- `https://bytgaming-backend.onrender.com`
- Swagger: `https://bytgaming-backend.onrender.com/swagger-ui.html`

