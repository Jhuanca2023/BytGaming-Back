# Variables de Entorno - Railway

## 🔐 Variables Requeridas

Configura estas variables de entorno en Railway para que el backend funcione correctamente:

### Base de Datos (MySQL)

Railway genera automáticamente estas variables cuando añades un servicio MySQL:

```env
MYSQL_HOST=containers-us-west-xxx.railway.app
MYSQL_PORT=3306
MYSQL_USER=root
MYSQL_PASSWORD=tu_password_generado
MYSQL_DATABASE=railway
```

### Variables del Backend

Añade estas variables en la configuración de tu servicio backend:

```env
# Database Connection
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}

# Server Port (Railway lo asigna automáticamente)
PORT=8080

# JWT Security
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

## 📝 Cómo Configurar en Railway

### Opción 1: Desde el Dashboard

1. Ve a tu proyecto en Railway
2. Selecciona el servicio backend
3. Ve a la pestaña "Variables"
4. Añade cada variable una por una
5. Para las variables de MySQL, Railway las referencia automáticamente con `${MYSQL_HOST}`, etc.

### Opción 2: Desde Railway CLI

```bash
railway variables set DB_URL="jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
railway variables set DB_USER="${MYSQL_USER}"
railway variables set DB_PASSWORD="${MYSQL_PASSWORD}"
railway variables set SECURITY_JWT_KEY_PRIVATE="cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43"
railway variables set SECURITY_JWT_USER_GENERATOR="AUTH0JWT-BACKEND"
```

## 🔗 Referencias de Variables

Railway permite referenciar variables de otros servicios usando la sintaxis `${VARIABLE_NAME}`.

**Ejemplo:**
- Si tu servicio MySQL tiene la variable `MYSQL_HOST`
- Puedes usarla en el backend con `${MYSQL_HOST}`

## ✅ Verificar Variables

Para verificar que las variables están correctamente configuradas:

1. Ve a la pestaña "Variables" en Railway
2. Verifica que todas las variables están presentes
3. Revisa los logs del deployment para ver si hay errores de conexión

## 🐛 Solución de Problemas

### Error: Variable not found

**Solución:**
- Asegúrate de que el servicio MySQL está en el mismo proyecto
- Verifica que el nombre de la variable es exactamente el mismo (case-sensitive)

### Error: Cannot connect to database

**Solución:**
- Verifica que `DB_URL` usa las variables correctas de MySQL
- Asegúrate de que el servicio MySQL está corriendo
- Revisa que `MYSQL_HOST` no tiene `http://` o `https://`

### Error: Port already in use

**Solución:**
- Railway asigna el puerto automáticamente
- Usa `${PORT}` en lugar de un puerto fijo
- La aplicación Spring Boot ya está configurada para usar `${PORT}`

## 🔒 Seguridad

**Importante:**
- Nunca commitees las contraseñas en el código
- Usa variables de entorno para toda la información sensible
- El JWT key debe ser único y seguro en producción
- Considera rotar las contraseñas regularmente
