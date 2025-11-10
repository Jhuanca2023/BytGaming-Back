# 🚀 Pasos para Desplegar en Render con PostgreSQL

## ✅ Render tiene PostgreSQL (no MySQL)

Render ofrece **PostgreSQL** en su plan gratuito. La aplicación ya está configurada para soportar PostgreSQL.

## 📋 Pasos (15 minutos)

### 1. Crear Cuenta en Render

1. Ve a https://render.com
2. Click en "Get Started for Free"
3. Regístrate con tu cuenta de GitHub
4. Confirma tu email

### 2. Crear Base de Datos PostgreSQL

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Postgres"**
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

Render proporciona automáticamente estas variables para PostgreSQL:
- `DATABASE_URL` - URL completa de conexión
- `DATABASE_USERNAME` - Usuario
- `DATABASE_PASSWORD` - Contraseña
- `DATABASE_HOST` - Host
- `DATABASE_PORT` - Puerto
- `DATABASE` - Nombre de la base de datos

**En la sección "Environment Variables", añade:**

```env
# Render proporciona estas variables automáticamente para PostgreSQL
# Solo necesitas añadir las variables JWT
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000

# Opcional: Si quieres usar nombres personalizados
# DB_URL=${DATABASE_URL}
# DB_USER=${DATABASE_USERNAME}
# DB_PASSWORD=${DATABASE_PASSWORD}
```

**Nota:** La aplicación detecta automáticamente `DATABASE_URL`, `DATABASE_USERNAME` y `DATABASE_PASSWORD` que Render proporciona.

### 6. Conectar la Base de Datos al Servicio

1. En la configuración del servicio web, ve a **"Environment"**
2. Busca la sección **"Add Environment Variable"**
3. Click en **"Link Database"** o busca tu base de datos PostgreSQL
4. Selecciona la base de datos `bytgaming-db`
5. Render conectará automáticamente la base de datos y añadirá las variables de entorno

### 7. Activar Auto-Deploy

- Activa **"Auto-Deploy"**
- Selecciona la rama **`main`**
- Render desplegará automáticamente en cada push

### 8. Crear el Servicio

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
- **PostgreSQL**: Render usa PostgreSQL, no MySQL (la aplicación ya está configurada para soportarlo)

## 🔧 Configuración de PostgreSQL

La aplicación detecta automáticamente PostgreSQL cuando:
- La URL contiene `jdbc:postgresql://`
- O cuando se usan las variables `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`

Render proporciona estas variables automáticamente cuando conectas una base de datos PostgreSQL.

## 🐛 Solución de Problemas

### Error: "Cannot connect to database"
**Solución:**
- Verifica que la base de datos PostgreSQL está conectada al servicio
- Verifica que las variables de entorno están correctas
- Asegúrate de que Render ha conectado la base de datos al servicio
- Verifica que el servicio PostgreSQL está corriendo

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

### Error: "Driver not found"
**Solución:**
- La aplicación ya tiene la dependencia de PostgreSQL en `pom.xml`
- Verifica que el build incluye todas las dependencias
- Revisa los logs de build en Render

## ✅ Checklist

- [ ] Cuenta creada en Render
- [ ] Base de datos PostgreSQL creada en Render
- [ ] Servicio web creado en Render
- [ ] Repositorio GitHub conectado
- [ ] Base de datos conectada al servicio (Link Database)
- [ ] Variables de entorno JWT configuradas
- [ ] Auto-Deploy activado
- [ ] Primer deployment exitoso
- [ ] API accesible en la URL de Render
- [ ] Swagger UI funcionando

## 🆘 Soporte

Si tienes problemas:
1. Revisa los logs en Render
2. Verifica que la base de datos está conectada al servicio
3. Verifica las variables de entorno
4. Consulta la documentación de Render: https://render.com/docs

## 🎉 ¡Listo!

Una vez configurado, Render desplegará automáticamente cada vez que hagas push a `main`.

**Ventajas de Render con PostgreSQL:**
- ✅ Plan gratuito disponible
- ✅ SSL automático
- ✅ Auto-deploy desde GitHub
- ✅ PostgreSQL incluido (más potente que MySQL)
- ✅ Fácil de configurar
- ✅ Logs en tiempo real
- ✅ Variables de entorno automáticas

## 📚 Diferencias entre MySQL y PostgreSQL

**PostgreSQL es compatible con tu aplicación:**
- ✅ Todas las entidades JPA funcionan igual
- ✅ Las consultas son compatibles
- ✅ Spring Boot detecta automáticamente PostgreSQL
- ✅ No necesitas cambiar el código

**La única diferencia:**
- PostgreSQL usa tipos de datos ligeramente diferentes, pero Spring Boot/JPA maneja esto automáticamente
- Algunas funciones específicas de MySQL pueden no funcionar, pero tu aplicación usa solo funciones estándar

