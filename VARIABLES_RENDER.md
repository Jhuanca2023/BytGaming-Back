# 🔐 Variables de Entorno para Render

## 📋 Lista de Variables a Añadir

Añade estas variables en tu servicio web → "Environment" → "Environment Variables":

| # | Name | Value | Descripción |
|---|------|-------|-------------|
| 1 | `DATABASE_URL` | `postgresql://bytgaming_db_user:[CONTRASEÑA]@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db` | URL completa de conexión a PostgreSQL |
| 2 | `DATABASE_USERNAME` | `bytgaming_db_user` | Usuario de la base de datos |
| 3 | `DATABASE_PASSWORD` | `[TU_CONTRASEÑA]` | Contraseña de la base de datos |
| 4 | `SECURITY_JWT_KEY_PRIVATE` | `cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43` | Clave privada para JWT |
| 5 | `SECURITY_JWT_USER_GENERATOR` | `AUTH0JWT-BACKEND` | Generador de usuario JWT |
| 6 | `PORT` | `10000` | Puerto del servidor (opcional) |

## 🔍 Cómo Obtener la Contraseña

### Método 1: Desde la URL de la Base de Datos (Recomendado)

1. Ve a tu base de datos PostgreSQL en Render
2. En la sección **"Conexiones"**, busca **"URL interna de la base de datos"**
3. Click en el ícono de "ojo" 👁️ para revelar la URL
4. Copia la URL completa
5. Úsala directamente como valor de `DATABASE_URL`

La URL tiene este formato:
```
postgresql://bytgaming_db_user:CONTRASEÑA@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db
```

### Método 2: Desde la Contraseña Ocultada

1. Ve a tu base de datos PostgreSQL en Render
2. En la sección **"Conexiones"**, busca **"Contraseña"**
3. Click en el ícono de "ojo" 👁️ para revelar la contraseña
4. Copia la contraseña
5. Úsala como valor de `DATABASE_PASSWORD`
6. Construye la URL manualmente para `DATABASE_URL`

## 📝 Ejemplo de Configuración

### Si usas la URL completa (Método 1):

```
DATABASE_URL=postgresql://bytgaming_db_user:abc123xyz@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db
DATABASE_USERNAME=bytgaming_db_user
DATABASE_PASSWORD=abc123xyz
```

### Si construyes la URL manualmente (Método 2):

```
DATABASE_URL=postgresql://bytgaming_db_user:abc123xyz@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db
DATABASE_USERNAME=bytgaming_db_user
DATABASE_PASSWORD=abc123xyz
```

## ✅ Verificación

Después de añadir todas las variables, verifica:

1. Ve a tu servicio web → "Environment" → "Environment Variables"
2. Debe haber 6 variables:
   - ✅ `DATABASE_URL`
   - ✅ `DATABASE_USERNAME`
   - ✅ `DATABASE_PASSWORD`
   - ✅ `SECURITY_JWT_KEY_PRIVATE`
   - ✅ `SECURITY_JWT_USER_GENERATOR`
   - ✅ `PORT`

## 🚀 Siguiente Paso

Una vez que tengas todas las variables configuradas:

1. Guarda los cambios
2. Render comenzará a construir y desplegar automáticamente
3. Espera 5-10 minutos
4. Verifica que el servicio esté funcionando en: `https://bytgaming-backend.onrender.com`

## 🆘 Problemas Comunes

### Error: "Cannot connect to database"
**Solución:**
- Verifica que `DATABASE_URL` esté correcta
- Verifica que `DATABASE_USERNAME` y `DATABASE_PASSWORD` sean correctos
- Asegúrate de que la base de datos esté en la misma región que el servicio web (Oregon)

### Error: "Password contains special characters"
**Solución:**
- Si la contraseña tiene caracteres especiales, asegúrate de copiarla correctamente
- Usa la URL completa en lugar de construirla manualmente
- Verifica que no haya espacios al inicio o al final

### Error: "URL format is incorrect"
**Solución:**
- La URL debe estar en formato: `postgresql://user:password@host:port/database`
- No debe tener espacios ni saltos de línea
- Verifica que todos los valores estén correctos

