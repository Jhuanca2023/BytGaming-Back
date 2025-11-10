# 🚀 Pasos Rápidos para Desplegar en Render

## ⚠️ Problema con Railway

Railway está en un plan limitado que **solo permite desplegar bases de datos**, no aplicaciones.

## ✅ Solución: Render (Gratis)

Render ofrece un **plan gratuito** que permite desplegar aplicaciones Spring Boot.

## 📋 Pasos (15 minutos)

### 1. Crear Cuenta en Render

1. Ve a https://render.com
2. Click en "Get Started for Free"
3. Regístrate con tu cuenta de GitHub
4. Confirma tu email

### 2. Crear Base de Datos MySQL

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"MySQL"**
3. Configura:
   - **Name**: `bytgaming-db`
   - **Database**: `bytgaming`
   - **User**: `bytgaming`
   - **Plan**: **Free**
   - **Region**: Oregon (o el más cercano)
4. Click en **"Create Database"**
5. Espera 2-3 minutos a que se cree
6. **Anota las credenciales** (las verás en la pantalla)

### 3. Crear Servicio Web (Backend)

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Web Service"**
3. Conecta tu repositorio:
   - Click en **"Connect account"** o **"Connect GitHub"**
   - Autoriza a Render a acceder a tus repositorios
   - Selecciona el repositorio **`BytGaming-Back`**
   - Click en **"Connect"**

### 4. Configurar el Servicio

**Configuración básica:**
- **Name**: `bytgaming-backend`
- **Environment**: **Docker**
- **Region**: Oregon (mismo que la base de datos)
- **Branch**: `main`
- **Root Directory**: `BytGaming`
- **Dockerfile Path**: `BytGaming/Dockerfile`
- **Docker Context**: `BytGaming`

**Build Command:**
```
(Dejar vacío)
```

**Start Command:**
```
(Dejar vacío)
```

### 5. Configurar Variables de Entorno

En la sección **"Environment Variables"**, añade estas variables:

```env
DB_URL=jdbc:mysql://[MYSQL_HOST]:3306/bytgaming?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=[MYSQL_USER]
DB_PASSWORD=[MYSQL_PASSWORD]
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000
```

**Para obtener los valores de MySQL:**
1. Ve a tu servicio MySQL en Render
2. Ve a la pestaña **"Connections"**
3. Copia:
   - **Internal Database URL**: Usa la parte del host para `DB_URL`
   - **Username**: Para `DB_USER`
   - **Password**: Para `DB_PASSWORD`

**Ejemplo de DB_URL:**
```
jdbc:mysql://dpg-xxxxx-a.oregon-postgres.render.com:3306/bytgaming?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```

### 6. Activar Auto-Deploy

- Activa **"Auto-Deploy"**
- Selecciona la rama **`main`**
- Render desplegará automáticamente en cada push

### 7. Crear el Servicio

1. Click en **"Create Web Service"**
2. Render comenzará a construir y desplegar
3. Espera **5-10 minutos** para el primer deployment
4. Puedes ver el progreso en la pestaña **"Logs"**

## ✅ Verificar el Deployment

1. Una vez completado, Render te dará una URL como:
   - `https://bytgaming-backend.onrender.com`

2. Prueba los endpoints:
   - **Swagger UI**: `https://bytgaming-backend.onrender.com/swagger-ui.html`
   - **API Docs**: `https://bytgaming-backend.onrender.com/v3/api-docs`

## 🔄 Actualización Continua

Una vez configurado, cada push a `main` desplegará automáticamente:

1. Render detecta el cambio
2. Inicia el build usando el Dockerfile
3. Construye la aplicación
4. Despliega automáticamente
5. El servicio está disponible en la URL proporcionada

## 📝 Notas Importantes

- **Plan Gratuito**: 750 horas/mes (suficiente para desarrollo)
- **Sleep Mode**: El servicio se "duerme" después de 15 minutos de inactividad (solo en plan gratuito)
- **Primera Request**: Puede tardar 30-60 segundos si el servicio está dormido (solo en plan gratuito)
- **Auto-Deploy**: Render despliega automáticamente en cada push a `main`
- **SSL**: Render proporciona SSL automáticamente

## 🐛 Solución de Problemas

### Error: "Cannot connect to database"
**Solución:**
- Verifica que `DB_URL` usa el "Internal Database URL" de Render
- Asegúrate de que el servicio MySQL está corriendo
- Verifica que las credenciales son correctas
- Asegúrate de usar el puerto correcto (3306)

### Error: "Build failed"
**Solución:**
- Revisa los logs en Render
- Verifica que el Dockerfile está correcto
- Verifica que el Root Directory es `BytGaming`
- Verifica que todas las dependencias están en `pom.xml`

### Error: "Port already in use"
**Solución:**
- Render usa el puerto 10000 por defecto
- La aplicación está configurada para usar `${PORT:10000}`
- No necesitas configurar un puerto manualmente

## ✅ Checklist

- [ ] Cuenta creada en Render
- [ ] Base de datos MySQL creada en Render
- [ ] Credenciales de MySQL anotadas
- [ ] Servicio web creado en Render
- [ ] Repositorio GitHub conectado
- [ ] Variables de entorno configuradas
- [ ] Auto-Deploy activado
- [ ] Primer deployment exitoso
- [ ] API accesible en la URL de Render
- [ ] Swagger UI funcionando

## 🆘 Soporte

Si tienes problemas:
1. Revisa los logs en Render
2. Verifica las variables de entorno
3. Consulta `RENDER_DEPLOY.md` para más detalles
4. Consulta la documentación de Render: https://render.com/docs

## 🎉 ¡Listo!

Una vez configurado, Render desplegará automáticamente cada vez que hagas push a `main`.

**Ventajas de Render:**
- ✅ Plan gratuito disponible
- ✅ SSL automático
- ✅ Auto-deploy desde GitHub
- ✅ Bases de datos incluidas
- ✅ Fácil de configurar
- ✅ Logs en tiempo real

