# 🔐 Variables Exactas para Render

## ✅ Variables a Añadir en Render

Ve a tu servicio web → "Environment" → "Environment Variables" y añade estas 6 variables:

### Variable 1: DATABASE_URL

**Name:** `DATABASE_URL`

**Value:**
```
postgresql://bytgaming_db_user:cKhpgerQWHlu3ac7EAMqR7NAC1CjXIrr@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db
```

### Variable 2: DATABASE_USERNAME

**Name:** `DATABASE_USERNAME`

**Value:**
```
bytgaming_db_user
```

### Variable 3: DATABASE_PASSWORD

**Name:** `DATABASE_PASSWORD`

**Value:**
```
cKhpgerQWHlu3ac7EAMqR7NAC1CjXIrr
```

### Variable 4: SECURITY_JWT_KEY_PRIVATE

**Name:** `SECURITY_JWT_KEY_PRIVATE`

**Value:**
```
cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
```

### Variable 5: SECURITY_JWT_USER_GENERATOR

**Name:** `SECURITY_JWT_USER_GENERATOR`

**Value:**
```
AUTH0JWT-BACKEND
```

### Variable 6: PORT

**Name:** `PORT`

**Value:**
```
10000
```

## 📋 Resumen en Tabla

| Name | Value |
|------|-------|
| `DATABASE_URL` | `postgresql://bytgaming_db_user:cKhpgerQWHlu3ac7EAMqR7NAC1CjXIrr@dpg-d497n8qli9vc739n373g-a:5432/bytgaming_db` |
| `DATABASE_USERNAME` | `bytgaming_db_user` |
| `DATABASE_PASSWORD` | `cKhpgerQWHlu3ac7EAMqR7NAC1CjXIrr` |
| `SECURITY_JWT_KEY_PRIVATE` | `cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43` |
| `SECURITY_JWT_USER_GENERATOR` | `AUTH0JWT-BACKEND` |
| `PORT` | `10000` |

## 🚀 Pasos para Añadir las Variables

1. Ve a tu servicio web en Render
2. Click en la pestaña **"Environment"** (arriba)
3. Busca la sección **"Environment Variables"**
4. Click en **"Add Environment Variable"**
5. Añade cada variable una por una:
   - Copia el **Name** exacto
   - Copia el **Value** exacto (sin espacios al inicio o final)
6. Click en **"Save"** después de añadir cada variable
7. Repite para las 6 variables

## ✅ Verificación

Después de añadir todas las variables, verifica:

1. Ve a "Environment" → "Environment Variables"
2. Debe haber 6 variables listadas:
   - ✅ `DATABASE_URL`
   - ✅ `DATABASE_USERNAME`
   - ✅ `DATABASE_PASSWORD`
   - ✅ `SECURITY_JWT_KEY_PRIVATE`
   - ✅ `SECURITY_JWT_USER_GENERATOR`
   - ✅ `PORT`

## 🎯 Siguiente Paso

Una vez que tengas todas las variables añadidas:

1. Guarda los cambios (si no se guardaron automáticamente)
2. Ve a la pestaña **"Settings"**
3. Activa **"Auto-Deploy"** si no está activado
4. Selecciona la rama **`main`**
5. Guarda los cambios
6. Render comenzará a construir y desplegar automáticamente
7. Espera 5-10 minutos para el primer deployment

## 🎉 ¡Listo!

Una vez que Render termine de desplegar, tu API estará disponible en:

- **URL Principal:** `https://bytgaming-backend.onrender.com`
- **Swagger UI:** `https://bytgaming-backend.onrender.com/swagger-ui.html`
- **API Docs:** `https://bytgaming-backend.onrender.com/v3/api-docs`

## 🆘 Si hay Problemas

### Error: "Cannot connect to database"
**Solución:**
- Verifica que copiaste la URL correctamente (sin espacios)
- Verifica que la contraseña sea correcta
- Verifica que la base de datos esté en la misma región (Oregon)

### Error: "Build failed"
**Solución:**
- Revisa los logs en Render
- Verifica que el Dockerfile esté correcto
- Verifica que todas las variables estén añadidas

### Error: "Port already in use"
**Solución:**
- Render usa el puerto 10000 por defecto
- La variable `PORT=10000` es correcta
- No necesitas cambiar nada

## 📝 Notas Importantes

1. **Seguridad:** No compartas estas variables públicamente
2. **Contraseña:** La contraseña está incluida en la URL de `DATABASE_URL`, pero también debes añadirla en `DATABASE_PASSWORD` por si acaso
3. **Espacios:** Asegúrate de no añadir espacios al inicio o final de los valores
4. **Mayúsculas/Minúsculas:** Los nombres de las variables son sensibles a mayúsculas/minúsculas, úsalos exactamente como se muestran

