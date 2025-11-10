# Guía de Despliegue - BytGaming Backend

## 🚀 Despliegue en Railway

### Paso 1: Preparar Railway

1. **Crear cuenta en Railway**
   - Ve a https://railway.app
   - Regístrate con tu cuenta de GitHub

2. **Crear nuevo proyecto**
   - Click en "New Project"
   - Selecciona "Deploy from GitHub repo"
   - Conecta el repositorio `BytGaming-Back`

### Paso 2: Configurar Base de Datos MySQL

1. **Añadir servicio MySQL**
   - En tu proyecto de Railway, click en "+ New"
   - Selecciona "Database" → "MySQL"
   - Railway creará automáticamente las variables de entorno:
     - `MYSQL_HOST`
     - `MYSQL_PORT`
     - `MYSQL_USER`
     - `MYSQL_PASSWORD`
     - `MYSQL_DATABASE`

2. **Configurar variables de entorno del backend**
   - Ve a la configuración de tu servicio backend
   - Añade las siguientes variables:

```env
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}
PORT=8080
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

### Paso 3: Configurar GitHub Secrets

1. **Obtener Railway Token**
   - Ve a Railway Dashboard
   - Settings → Tokens
   - Click en "New Token"
   - Copia el token generado

2. **Añadir secrets en GitHub**
   - Ve a tu repositorio en GitHub
   - Settings → Secrets and variables → Actions
   - Añade los siguientes secrets:
     - `RAILWAY_TOKEN`: El token que copiaste anteriormente

### Paso 4: Configurar el Proyecto

1. **Configurar Railway Service**
   - En Railway, ve a tu servicio backend
   - Settings → General
   - Configura:
     - **Root Directory**: `BytGaming`
     - **Build Command**: (dejar vacío, Railway detecta automáticamente)
     - **Start Command**: `java -jar target/*.jar` o dejar vacío

2. **Variables de entorno adicionales** (si es necesario):
```env
JAVA_OPTS=-Xmx512m -Xms256m
```

### Paso 5: Desplegar

1. **Push a GitHub**
   ```bash
   git add .
   git commit -m "Initial deployment setup"
   git push origin main
   ```

2. **Verificar deployment**
   - Railway comenzará a construir y desplegar automáticamente
   - Ve a la pestaña "Deployments" para ver el progreso
   - Una vez completado, Railway proporcionará una URL pública

### Paso 6: Verificar el Despliegue

1. **Verificar logs**
   - En Railway, ve a la pestaña "Logs"
   - Verifica que la aplicación inició correctamente

2. **Probar endpoints**
   - Accede a la URL proporcionada por Railway
   - Prueba el endpoint de salud: `https://tu-app.railway.app/v3/api-docs`
   - Swagger UI: `https://tu-app.railway.app/swagger-ui.html`

## 🔧 Configuración Manual (Alternativa)

Si prefieres desplegar manualmente sin GitHub Actions:

1. **Instalar Railway CLI**
   ```bash
   npm install -g @railway/cli
   ```

2. **Iniciar sesión**
   ```bash
   railway login
   ```

3. **Inicializar proyecto**
   ```bash
   cd Back
   railway init
   ```

4. **Desplegar**
   ```bash
   railway up
   ```

## 🐛 Solución de Problemas

### Error: No se puede conectar a la base de datos

**Solución:**
- Verifica que las variables de entorno están correctamente configuradas
- Asegúrate de que el servicio MySQL está corriendo
- Verifica que `DB_URL` usa las variables de entorno de Railway correctamente

### Error: Puerto no disponible

**Solución:**
- Railway asigna el puerto automáticamente mediante la variable `PORT`
- Verifica que `application.properties` usa `${PORT}` o configura `server.port=${PORT}`

### Error: Build falla

**Solución:**
- Verifica que el Dockerfile está correctamente configurado
- Revisa los logs de build en Railway
- Asegúrate de que todas las dependencias están en `pom.xml`

### Error: JWT no funciona

**Solución:**
- Verifica que `SECURITY_JWT_KEY_PRIVATE` está configurada
- Asegúrate de que el token tiene la longitud correcta (mínimo 256 bits)

## 📊 Monitoreo

Railway proporciona:

- **Logs en tiempo real**: Ve a la pestaña "Logs"
- **Métricas**: CPU, Memoria, Red
- **Variables de entorno**: Fácil gestión desde el dashboard

## 🔄 Actualización Continua

Una vez configurado, cada push a la rama `main` o `master` desplegará automáticamente:

1. GitHub Actions ejecuta el workflow
2. Railway recibe el código
3. Construye y despliega automáticamente

## 📝 Notas Importantes

- Railway proporciona una URL pública gratuita
- El plan gratuito incluye $5 de crédito mensual
- Las variables de entorno de MySQL se configuran automáticamente cuando añades el servicio MySQL
- La base de datos se crea automáticamente si no existe (gracias a `createDatabaseIfNotExist=true`)

## 🆘 Soporte

Si tienes problemas:
1. Revisa los logs en Railway
2. Verifica las variables de entorno
3. Consulta la documentación de Railway: https://docs.railway.app
