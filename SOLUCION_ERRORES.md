# 🔧 Solución de Errores de Deployment en Railway

## ❌ Problemas Identificados y Solucionados

### 1. Versión inválida en pom.xml
**Problema:** La versión era `<version>ByteGaming</version>` que no es válida para Maven.

**Solución:** ✅ Cambiada a `<version>1.0.0</version>`

### 2. Nombre del JAR impredecible
**Problema:** El JAR se generaba con un nombre basado en la versión incorrecta, dificultando su localización.

**Solución:** ✅ Configurado `<finalName>app</finalName>` en el plugin de Spring Boot para generar `app.jar` siempre.

### 3. Dockerfile no robusto
**Problema:** El Dockerfile fallaba si mvnw no tenía permisos o si el JAR tenía un nombre diferente.

**Solución:** ✅ Mejorado el Dockerfile con:
- Manejo de errores mejorado
- Verificación de que el JAR se crea correctamente
- Fallback a `mvn` si `mvnw` falla
- Healthcheck añadido
- Nombre de JAR fijo: `app.jar`

### 4. Configuración de Railway
**Problema:** Railway podría no estar detectando correctamente el proyecto.

**Solución:** ✅ Creados múltiples archivos de configuración:
- `railway.json` - Configuración principal
- `railway.toml` - Configuración alternativa
- `nixpacks.toml` - Para deployment sin Docker (alternativa)

## 🔍 Cómo Diagnosticar Errores

### Ver logs en Railway

1. Ve a Railway Dashboard
2. Selecciona tu proyecto
3. Ve a la pestaña "Logs"
4. Revisa los errores más recientes

### Errores comunes y soluciones

#### Error: "JAR file not found"
```
Solución: Verifica que el pom.xml tiene <finalName>app</finalName>
```

#### Error: "Cannot connect to database"
```
Solución: Verifica las variables de entorno:
- DB_URL
- DB_USER
- DB_PASSWORD
Asegúrate de que MySQL está corriendo y las variables están correctas.
```

#### Error: "Port already in use"
```
Solución: Railway asigna el puerto automáticamente.
La aplicación ya está configurada para usar ${PORT}.
```

#### Error: "Build failed"
```
Solución: 
1. Revisa los logs de build
2. Verifica que todas las dependencias están en pom.xml
3. Asegúrate de que el Dockerfile está correcto
4. Verifica que el Root Directory está configurado como "BytGaming"
```

## 🛠️ Configuración Recomendada en Railway

### Root Directory
```
BytGaming
```

### Build Command
```
(Dejar vacío - Railway detecta automáticamente)
```

### Start Command
```
(Dejar vacío - Railway detecta automáticamente)
```

### Variables de Entorno
```env
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

## 🚀 Pasos para Desplegar Correctamente

### 1. Verificar configuración local
```bash
cd Back/BytGaming
mvn clean package -DskipTests
ls -la target/app.jar  # Debe existir
```

### 2. Subir cambios a GitHub
```bash
cd Back
git add .
git commit -m "Fix: Corregir versión y Dockerfile para Railway"
git push origin main
```

### 3. Verificar en Railway
1. Ve a Railway Dashboard
2. Verifica que el deployment se está ejecutando
3. Revisa los logs en tiempo real
4. Espera a que el build complete

### 4. Verificar el deployment
1. Una vez completado, Railway proporcionará una URL
2. Prueba: `https://tu-app.railway.app/v3/api-docs`
3. Swagger UI: `https://tu-app.railway.app/swagger-ui.html`

## 📋 Checklist de Verificación

- [ ] Versión en pom.xml es válida (1.0.0)
- [ ] finalName configurado como "app" en pom.xml
- [ ] Dockerfile actualizado y probado
- [ ] Variables de entorno configuradas en Railway
- [ ] Root Directory configurado como "BytGaming"
- [ ] MySQL servicio corriendo
- [ ] Código subido a GitHub
- [ ] Deployment iniciado en Railway
- [ ] Logs sin errores críticos
- [ ] API accesible en la URL de Railway

## 🔄 Si el deployment sigue fallando

### Opción 1: Usar Dockerfile simplificado
Renombra `Dockerfile.simple` a `Dockerfile`:
```bash
cd Back/BytGaming
mv Dockerfile Dockerfile.original
mv Dockerfile.simple Dockerfile
```

### Opción 2: Usar Nixpacks (sin Docker)
Railway puede construir automáticamente usando Nixpacks:
1. Elimina o renombra el Dockerfile
2. Railway detectará automáticamente que es un proyecto Maven
3. Usará `nixpacks.toml` si existe

### Opción 3: Build manual y deploy
```bash
# Build local
cd Back/BytGaming
mvn clean package -DskipTests

# Verificar JAR
ls -la target/app.jar

# Subir a GitHub
git add .
git commit -m "Build verification"
git push origin main
```

## 🆘 Contacto y Soporte

Si después de seguir estos pasos el deployment sigue fallando:

1. **Revisa los logs completos en Railway**
2. **Verifica que todas las variables de entorno están correctas**
3. **Asegúrate de que MySQL está corriendo**
4. **Consulta la documentación de Railway**: https://docs.railway.app
5. **Revisa los issues en GitHub** (si los hay)

## 📝 Notas Importantes

- Railway asigna el puerto automáticamente mediante la variable `PORT`
- La aplicación Spring Boot está configurada para usar `${PORT:8080}`
- El JAR se genera siempre como `app.jar` gracias a `<finalName>app</finalName>`
- El Dockerfile tiene fallbacks para mayor robustez
- Los healthchecks ayudan a Railway a detectar cuando la app está lista

