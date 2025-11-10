# 🚀 Despliegue en Render (Alternativa Gratuita)

## ✅ Render - Plan Gratuito Disponible

Render ofrece un plan gratuito que permite desplegar aplicaciones Spring Boot.

### Características del Plan Gratuito:
- ✅ 750 horas/mes gratis
- ✅ SSL automático
- ✅ Auto-deploy desde GitHub
- ✅ Variables de entorno
- ✅ Bases de datos PostgreSQL/MySQL (gratis)

## 📋 Pasos para Desplegar en Render

### 1. Crear Cuenta en Render

1. Ve a https://render.com
2. Regístrate con tu cuenta de GitHub
3. Confirma tu email

### 2. Crear Base de Datos MySQL

1. En Render Dashboard, click en "New +"
2. Selecciona "MySQL"
3. Configura:
   - **Name**: `bytgaming-db`
   - **Plan**: Free
   - **Region**: Oregon (o el más cercano)
   - Click en "Create Database"
4. Espera a que se cree (2-3 minutos)
5. Anota las credenciales que Render te proporciona

### 3. Crear Servicio Web (Backend)

1. En Render Dashboard, click en "New +"
2. Selecciona "Web Service"
3. Conecta tu repositorio GitHub:
   - Selecciona `BytGaming-Back`
   - Autoriza a Render

### 4. Configurar el Servicio

**Configuración básica:**
- **Name**: `bytgaming-backend`
- **Environment**: `Docker`
- **Region**: Oregon (o el mismo que la base de datos)
- **Branch**: `main`
- **Root Directory**: `BytGaming`
- **Dockerfile Path**: `BytGaming/Dockerfile`
- **Docker Context**: `BytGaming`

**Build Command:**
```
(Dejar vacío - Render usa el Dockerfile)
```

**Start Command:**
```
(Dejar vacío - Render usa el Dockerfile)
```

### 5. Configurar Variables de Entorno

En la sección "Environment Variables", añade:

```env
DB_URL=jdbc:mysql://[MYSQL_HOST]:3306/[MYSQL_DATABASE]?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=[MYSQL_USER]
DB_PASSWORD=[MYSQL_PASSWORD]
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000
```

**Nota:** Reemplaza `[MYSQL_HOST]`, `[MYSQL_USER]`, `[MYSQL_PASSWORD]`, `[MYSQL_DATABASE]` con los valores de tu base de datos MySQL en Render.

### 6. Configurar Auto-Deploy

- Activa "Auto-Deploy"
- Selecciona la rama `main`
- Render desplegará automáticamente en cada push

### 7. Crear el Servicio

1. Click en "Create Web Service"
2. Render comenzará a construir y desplegar
3. Espera 5-10 minutos para el primer deployment

## 🔧 Configuración Adicional

### Obtener Credenciales de MySQL

1. Ve a tu servicio MySQL en Render
2. Ve a "Connections"
3. Copia:
   - **Internal Database URL**: Usa esta para `DB_URL`
   - **Username**: Para `DB_USER`
   - **Password**: Para `DB_PASSWORD`
   - **Database**: Para `MYSQL_DATABASE`

### Ejemplo de DB_URL:

```
jdbc:mysql://dpg-xxxxx-a.oregon-postgres.render.com:3306/bytgaming_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```

## ✅ Verificar el Deployment

1. Una vez completado, Render te dará una URL como:
   - `https://bytgaming-backend.onrender.com`

2. Prueba los endpoints:
   - Swagger UI: `https://bytgaming-backend.onrender.com/swagger-ui.html`
   - API Docs: `https://bytgaming-backend.onrender.com/v3/api-docs`

## 🐛 Solución de Problemas

### Error: "Cannot connect to database"
**Solución:**
- Verifica que `DB_URL` usa el "Internal Database URL" de Render
- Asegúrate de que el servicio MySQL está corriendo
- Verifica que las credenciales son correctas

### Error: "Build failed"
**Solución:**
- Revisa los logs en Render
- Verifica que el Dockerfile está correcto
- Verifica que el Root Directory es `BytGaming`

### Error: "Port already in use"
**Solución:**
- Render asigna el puerto automáticamente
- Usa la variable de entorno `PORT` que Render proporciona
- La aplicación ya está configurada para usar `${PORT:8080}`

## 📝 Notas Importantes

- **Plan Gratuito**: 750 horas/mes (suficiente para desarrollo)
- **Sleep Mode**: El servicio se "duerme" después de 15 minutos de inactividad (solo en plan gratuito)
- **Primera Request**: Puede tardar 30-60 segundos si el servicio está dormido
- **Auto-Deploy**: Render despliega automáticamente en cada push a `main`

## 🔄 Actualización Continua

Una vez configurado, cada push a `main` desplegará automáticamente:

1. Render detecta el cambio
2. Inicia el build usando el Dockerfile
3. Construye la aplicación
4. Despliega automáticamente
5. El servicio está disponible en la URL proporcionada

## 🆘 Soporte

Si tienes problemas:
1. Revisa los logs en Render
2. Verifica las variables de entorno
3. Consulta la documentación de Render: https://render.com/docs
4. Revisa `SOLUCION_ERRORES.md` para errores comunes

## 🎉 Ventajas de Render

- ✅ Plan gratuito disponible
- ✅ SSL automático
- ✅ Auto-deploy desde GitHub
- ✅ Bases de datos incluidas
- ✅ Fácil de configurar
- ✅ Logs en tiempo real

