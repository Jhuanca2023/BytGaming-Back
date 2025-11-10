# ⚙️ Configuración de Railway - Guía Rápida

## 🔧 Configuración del Servicio Backend en Railway

### 1. Root Directory
```
BytGaming
```

### 2. Build Command
```
(Dejar vacío - Railway detecta automáticamente el Dockerfile)
```

### 3. Start Command
```
(Dejar vacío - Railway usa el ENTRYPOINT del Dockerfile)
```

### 4. Variables de Entorno Requeridas

Añade estas variables en la pestaña "Variables" del servicio backend:

```env
# Database Connection (Railway genera estas automáticamente del servicio MySQL)
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}

# JWT Security
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND

# Port (Railway asigna automáticamente)
PORT=8080
```

## 📋 Pasos de Configuración en Railway

### Paso 1: Verificar Root Directory
1. Ve a tu proyecto en Railway
2. Selecciona el servicio backend
3. Ve a Settings → General
4. Verifica que **Root Directory** está configurado como: `BytGaming`
5. Si no está, configúralo y guarda

### Paso 2: Verificar Variables de Entorno
1. Ve a la pestaña "Variables"
2. Verifica que todas las variables están presentes
3. Asegúrate de que las variables de MySQL usan la sintaxis `${MYSQL_HOST}` etc.

### Paso 3: Verificar Servicio MySQL
1. Asegúrate de que el servicio MySQL está corriendo
2. Verifica que las variables de MySQL están disponibles:
   - `MYSQL_HOST`
   - `MYSQL_PORT`
   - `MYSQL_USER`
   - `MYSQL_PASSWORD`
   - `MYSQL_DATABASE`

### Paso 4: Configurar Health Check (Opcional)
1. Ve a Settings → Healthcheck
2. Path: `/v3/api-docs`
3. Timeout: 100

## 🐛 Solución de Problemas

### Error: "Cannot find Dockerfile"
**Solución:**
- Verifica que el Root Directory está configurado como `BytGaming`
- Asegúrate de que el Dockerfile existe en `BytGaming/Dockerfile`

### Error: "Build failed"
**Solución:**
- Revisa los logs de build en Railway
- Verifica que el pom.xml tiene la versión correcta (1.0.0)
- Asegúrate de que todas las dependencias están disponibles

### Error: "Cannot connect to database"
**Solución:**
- Verifica que el servicio MySQL está corriendo
- Asegúrate de que las variables de entorno están correctas
- Verifica que `DB_URL` usa las variables correctas: `${MYSQL_HOST}`, etc.

### Error: "Port already in use"
**Solución:**
- Railway asigna el puerto automáticamente
- La aplicación ya está configurada para usar `${PORT}`
- No necesitas configurar un puerto manualmente

## ✅ Checklist de Verificación

Antes de desplegar, verifica:

- [ ] Root Directory configurado como `BytGaming`
- [ ] Build Command vacío (Railway detecta automáticamente)
- [ ] Start Command vacío (Railway usa el Dockerfile)
- [ ] Variables de entorno configuradas correctamente
- [ ] Servicio MySQL corriendo
- [ ] Variables de MySQL disponibles
- [ ] Código subido a GitHub
- [ ] Railway conectado al repositorio correcto

## 🚀 Después del Deployment

Una vez que el deployment complete:

1. **Verifica los logs:**
   - Ve a la pestaña "Logs"
   - Busca mensajes como "Started BytGaminGApplication"
   - Verifica que no hay errores de conexión a la base de datos

2. **Prueba los endpoints:**
   - Swagger UI: `https://tu-app.railway.app/swagger-ui.html`
   - API Docs: `https://tu-app.railway.app/v3/api-docs`
   - Health: `https://tu-app.railway.app/actuator/health` (si está habilitado)

3. **Verifica la base de datos:**
   - La aplicación creará las tablas automáticamente
   - Los datos iniciales se crearán mediante `CommandInitializerConfig`

## 📝 Notas Importantes

- Railway asigna el puerto automáticamente mediante la variable `PORT`
- La aplicación Spring Boot está configurada para usar `${PORT:8080}`
- El JAR se genera como `app.jar` gracias a `<finalName>app</finalName>` en pom.xml
- El Dockerfile está optimizado para Railway con fallbacks y verificaciones
- Las variables de entorno de MySQL se referencian usando `${VARIABLE_NAME}`

## 🔄 Actualización Continua

Una vez configurado, cada push a `main` o `master` desplegará automáticamente:

1. Railway detecta el cambio
2. Inicia el build usando el Dockerfile
3. Construye la aplicación
4. Despliega automáticamente
5. Ejecuta health checks

## 🆘 Si algo falla

1. Revisa los logs en Railway
2. Verifica las variables de entorno
3. Consulta `SOLUCION_ERRORES.md` para más detalles
4. Revisa la documentación de Railway: https://docs.railway.app

