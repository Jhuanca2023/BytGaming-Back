# 🐘 Guía Completa: Desplegar en Render con PostgreSQL

## ✅ Render tiene PostgreSQL (no MySQL)

Render **NO tiene MySQL** como servicio gestionado. Solo ofrece **PostgreSQL**. La buena noticia es que tu aplicación ya está configurada para soportar PostgreSQL.

## 🎯 Cambios Realizados

### 1. ✅ Dependencia de PostgreSQL añadida
- Añadida `postgresql` al `pom.xml`
- La aplicación ahora soporta tanto MySQL como PostgreSQL

### 2. ✅ Configuración automática
- Creada clase `DatabaseConfig` que convierte automáticamente `DATABASE_URL` de Render a formato JDBC
- Soporta variables de Render: `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`
- También soporta variables de MySQL: `DB_URL`, `DB_USER`, `DB_PASSWORD`

### 3. ✅ Detección automática
- Spring Boot detecta automáticamente PostgreSQL desde la URL JDBC
- No necesitas cambiar el código Java
- Las entidades JPA funcionan igual con PostgreSQL

## 📋 Pasos para Desplegar en Render

### Paso 1: Crear Cuenta en Render

1. Ve a https://render.com
2. Click en "Get Started for Free"
3. Regístrate con tu cuenta de GitHub
4. Confirma tu email

### Paso 2: Crear Base de Datos PostgreSQL

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Postgres"** (NO MySQL, Render no tiene MySQL)
3. Configura:
   - **Name**: `bytgaming-db`
   - **Database**: `bytgaming`
   - **User**: `bytgaming`
   - **Plan**: **Free**
   - **Region**: Oregon (o el más cercano)
4. Click en **"Create Database"**
5. Espera 2-3 minutos a que se cree
6. **Anota las credenciales** (las verás en la pantalla)

### Paso 3: Crear Servicio Web (Backend)

1. En Render Dashboard, click en **"New +"**
2. Selecciona **"Web Service"**
3. Conecta tu repositorio:
   - Click en **"Connect account"** o **"Connect GitHub"**
   - Autoriza a Render a acceder a tus repositorios
   - Selecciona el repositorio **`BytGaming-Back`**
   - Click en **"Connect"**

### Paso 4: Configurar el Servicio

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

### Paso 5: Conectar la Base de Datos al Servicio

**IMPORTANTE:** Render puede conectar automáticamente la base de datos al servicio.

1. En la configuración del servicio web, busca la sección **"Environment"**
2. Busca **"Link Database"** o **"Add Database"**
3. Selecciona la base de datos `bytgaming-db`
4. Render conectará automáticamente y añadirá las variables:
   - `DATABASE_URL` - URL completa de conexión
   - `DATABASE_USERNAME` - Usuario
   - `DATABASE_PASSWORD` - Contraseña

**Si no puedes conectar automáticamente**, añade manualmente estas variables:

1. Ve a la sección **"Environment Variables"**
2. Añade estas variables (obtén los valores de la pestaña "Connections" de tu base de datos):
   ```env
   DATABASE_URL=postgresql://[USER]:[PASSWORD]@[HOST]:[PORT]/[DATABASE]
   DATABASE_USERNAME=[USER]
   DATABASE_PASSWORD=[PASSWORD]
   ```

### Paso 6: Configurar Variables de Entorno Adicionales

Añade estas variables en la sección **"Environment Variables"**:

```env
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000
```

### Paso 7: Activar Auto-Deploy

- Activa **"Auto-Deploy"**
- Selecciona la rama **`main`**
- Render desplegará automáticamente en cada push

### Paso 8: Crear el Servicio

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

## 🔧 Formato de DATABASE_URL en Render

Render proporciona `DATABASE_URL` en este formato:
```
postgresql://user:password@host:port/database
```

La clase `DatabaseConfig` lo convierte automáticamente a:
```
jdbc:postgresql://host:port/database
```

**No necesitas hacer nada**, la aplicación lo maneja automáticamente.

## 📝 Notas Importantes

- **PostgreSQL es compatible**: Tu aplicación funciona igual con PostgreSQL
- **Detección automática**: Spring Boot detecta PostgreSQL automáticamente
- **Sin cambios de código**: No necesitas cambiar el código Java
- **Variables automáticas**: Render proporciona las variables de base de datos automáticamente cuando conectas la base de datos
- **Plan Gratuito**: 750 horas/mes (suficiente para desarrollo)
- **Sleep Mode**: El servicio se "duerme" después de 15 minutos de inactividad (solo en plan gratuito)
- **Primera Request**: Puede tardar 30-60 segundos si el servicio está dormido (solo en plan gratuito)

## 🐛 Solución de Problemas

### Error: "Cannot connect to database"
**Solución:**
- Verifica que la base de datos está conectada al servicio (Link Database)
- Verifica que las variables `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD` están presentes
- Verifica que el servicio PostgreSQL está corriendo
- Revisa los logs en Render para ver el error exacto

### Error: "Build failed"
**Solución:**
- Revisa los logs en Render
- Verifica que el Dockerfile está correcto
- Verifica que el Root Directory es `BytGaming`
- Verifica que todas las dependencias están en `pom.xml`

### Error: "Driver not found"
**Solución:**
- La dependencia de PostgreSQL ya está en `pom.xml`
- Verifica que el build incluye todas las dependencias
- Revisa los logs de build en Render

### Error: "Port already in use"
**Solución:**
- Render usa el puerto 10000 por defecto
- La aplicación está configurada para usar `${PORT:10000}`
- No necesitas configurar un puerto manualmente

## ✅ Checklist

- [ ] Cuenta creada en Render
- [ ] Base de datos PostgreSQL creada en Render
- [ ] Credenciales de PostgreSQL anotadas
- [ ] Servicio web creado en Render
- [ ] Repositorio GitHub conectado
- [ ] Base de datos conectada al servicio (Link Database)
- [ ] Variables de entorno JWT configuradas
- [ ] Auto-Deploy activado
- [ ] Primer deployment exitoso
- [ ] API accesible en la URL de Render
- [ ] Swagger UI funcionando

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
2. Verifica que la base de datos está conectada al servicio
3. Verifica las variables de entorno
4. Consulta la documentación de Render: https://render.com/docs
5. Revisa `SOLUCION_ERRORES.md` para errores comunes

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
- Algunas funciones específicas de MySQL pueden no funcionar, pero tu aplicación usa solo funciones estándar que son compatibles

## 🚀 Próximos Pasos

1. Sigue los pasos arriba para desplegar en Render
2. Verifica que el deployment fue exitoso
3. Prueba los endpoints de la API
4. Conecta tu frontend a la URL de Render

