# 🚀 Configuración Rápida de Railway

## Paso a Paso para Desplegar el Backend

### 1. Crear Proyecto en Railway

1. Ve a https://railway.app
2. Inicia sesión con GitHub
3. Click en "New Project"
4. Selecciona "Deploy from GitHub repo"
5. Busca y selecciona `BytGaming-Back`

### 2. Añadir Base de Datos MySQL

1. En tu proyecto, click en "+ New"
2. Selecciona "Database" → "MySQL"
3. Railway creará automáticamente el servicio MySQL
4. Anota las variables de entorno que Railway genera (las verás en la pestaña Variables)

### 3. Configurar el Servicio Backend

1. Railway detectará automáticamente que es un proyecto Java/Maven
2. Ve a la configuración del servicio:
   - **Root Directory**: `BytGaming` (si Railway no lo detecta automáticamente)
   - **Build Command**: (dejar vacío, Railway lo detecta)
   - **Start Command**: (dejar vacío, Railway lo detecta)

### 4. Configurar Variables de Entorno

En la pestaña "Variables" del servicio backend, añade:

```env
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

**Nota:** Las variables `${MYSQL_HOST}`, `${MYSQL_PORT}`, etc. son referencias al servicio MySQL que Railway crea automáticamente.

### 5. Obtener Railway Token para GitHub Actions

1. Ve a Railway Dashboard
2. Click en tu perfil (arriba a la derecha)
3. Selecciona "Account Settings"
4. Ve a la pestaña "Tokens"
5. Click en "New Token"
6. Dale un nombre (ej: "GitHub Actions")
7. Copia el token generado

### 6. Configurar GitHub Secrets

1. Ve a tu repositorio en GitHub: https://github.com/Jhuanca2023/BytGaming-Back
2. Ve a Settings → Secrets and variables → Actions
3. Click en "New repository secret"
4. Añade el secret:
   - **Name**: `RAILWAY_TOKEN`
   - **Value**: El token que copiaste en el paso anterior
5. Click en "Add secret"

### 7. Configurar Railway para GitHub

1. En Railway, ve a tu proyecto
2. Ve a Settings → Integrations
3. Asegúrate de que GitHub está conectado
4. Verifica que el repositorio está vinculado

### 8. Primer Despliegue

1. En GitHub, ve a tu repositorio
2. Haz commit y push de los cambios:
   ```bash
   git add .
   git commit -m "Setup Railway deployment"
   git push origin main
   ```

3. Railway detectará el push y comenzará a construir y desplegar automáticamente
4. Ve a Railway para ver el progreso del deployment
5. Una vez completado, Railway te dará una URL pública

### 9. Verificar el Despliegue

1. **Ver logs:**
   - En Railway, ve a la pestaña "Logs"
   - Verifica que la aplicación inició correctamente
   - Busca mensajes como "Started BytGaminGApplication"

2. **Probar endpoints:**
   - Swagger UI: `https://tu-app.railway.app/swagger-ui.html`
   - API Docs: `https://tu-app.railway.app/v3/api-docs`
   - Health check: `https://tu-app.railway.app/actuator/health` (si está habilitado)

### 10. Configurar Dominio Personalizado (Opcional)

1. En Railway, ve a la configuración de tu servicio
2. Ve a la pestaña "Settings"
3. En "Domains", click en "Generate Domain"
4. Railway te dará una URL como: `tu-app.up.railway.app`
5. También puedes añadir un dominio personalizado si lo tienes

## 🔄 Deployment Automático

Una vez configurado, cada push a `main` o `master` desplegará automáticamente:

1. GitHub Actions ejecuta el workflow
2. Railway recibe el código
3. Construye la aplicación
4. Despliega automáticamente

## 📊 Monitoreo

Railway proporciona:

- **Logs en tiempo real**: Ve a la pestaña "Logs"
- **Métricas**: CPU, Memoria, Red en tiempo real
- **Deployments**: Historial de todos los deployments
- **Variables**: Gestión fácil de variables de entorno

## 🐛 Solución de Problemas

### El build falla

**Solución:**
- Verifica los logs en Railway
- Asegúrate de que el Dockerfile está en la ruta correcta
- Verifica que todas las dependencias están en `pom.xml`

### No se puede conectar a la base de datos

**Solución:**
- Verifica que las variables de entorno están correctas
- Asegúrate de que el servicio MySQL está corriendo
- Verifica que `DB_URL` usa las variables correctas

### El puerto no está disponible

**Solución:**
- Railway asigna el puerto automáticamente
- La aplicación ya está configurada para usar `${PORT}`
- No necesitas configurar un puerto manualmente

## 📝 Notas Importantes

- Railway proporciona $5 de crédito gratuito mensual
- El plan gratuito es suficiente para desarrollo y pruebas
- Las variables de entorno de MySQL se configuran automáticamente
- La base de datos se crea automáticamente si no existe
- Railway asigna el puerto automáticamente mediante la variable `PORT`

## 🆘 Ayuda

Si tienes problemas:
1. Revisa los logs en Railway
2. Verifica las variables de entorno
3. Consulta la documentación: https://docs.railway.app
4. Revisa el README.md y DEPLOYMENT.md
