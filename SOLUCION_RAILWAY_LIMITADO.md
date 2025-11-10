# ⚠️ Solución: Railway Plan Limitado

## ❌ Problema

Railway muestra el mensaje:
```
Your account is on a limited plan and can only deploy databases. Upgrade your plan
```

Esto significa que tu cuenta de Railway está en un plan limitado que **solo permite desplegar bases de datos**, no aplicaciones.

## ✅ Soluciones

### Opción 1: Actualizar Plan de Railway (De Pago)

Si quieres seguir usando Railway:

1. **Ve a Railway Dashboard**
2. **Click en "Upgrade" o "Plans"**
3. **Selecciona un plan que permita desplegar servicios:**
   - **Developer Plan**: $5/mes
   - **Team Plan**: $20/mes
   - **Business Plan**: $100/mes

**Ventajas:**
- ✅ Continúas usando Railway
- ✅ Mismo flujo de trabajo
- ✅ Buena experiencia

**Desventajas:**
- ❌ Requiere pago mensual
- ❌ Puede ser costoso para proyectos pequeños

### Opción 2: Usar Render (Recomendado) ⭐

**Render ofrece un plan gratuito** que permite desplegar aplicaciones Spring Boot.

**Ventajas:**
- ✅ Plan gratuito disponible (750 horas/mes)
- ✅ SSL automático
- ✅ Auto-deploy desde GitHub
- ✅ Bases de datos MySQL/PostgreSQL incluidas
- ✅ Fácil de configurar
- ✅ Sin necesidad de tarjeta de crédito (plan gratuito)

**Desventajas:**
- ⚠️ Sleep mode después de 15 minutos de inactividad (solo en plan gratuito)
- ⚠️ Primera request puede tardar 30-60 segundos si está dormido

**Guía:** Ver `RENDER_DEPLOY.md`

**Link:** https://render.com

### Opción 3: Usar Otras Plataformas Gratuitas

Otras alternativas gratuitas:
- **Fly.io**: Plan gratuito generoso, sin sleep mode
- **AWS**: Plan gratuito por 12 meses (nuevos usuarios)
- **Google Cloud**: Plan gratuito generoso
- **DigitalOcean**: Plan gratuito limitado

**Guía:** Ver `ALTERNATIVAS_DEPLOY.md`

## 🎯 Recomendación

**Usa Render** porque:
1. ✅ Plan gratuito disponible
2. ✅ Fácil de configurar
3. ✅ Auto-deploy desde GitHub
4. ✅ Bases de datos incluidas
5. ✅ SSL automático
6. ✅ Documentación clara

## 🚀 Pasos para Migrar a Render

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
4. Click en "Create Database"
5. Espera a que se cree (2-3 minutos)
6. Anota las credenciales

### 3. Crear Servicio Web (Backend)

1. En Render Dashboard, click en "New +"
2. Selecciona "Web Service"
3. Conecta tu repositorio GitHub: `BytGaming-Back`

### 4. Configurar el Servicio

**Configuración:**
- **Name**: `bytgaming-backend`
- **Environment**: `Docker`
- **Region**: Oregon
- **Branch**: `main`
- **Root Directory**: `BytGaming`
- **Dockerfile Path**: `BytGaming/Dockerfile`

### 5. Configurar Variables de Entorno

```env
DB_URL=jdbc:mysql://[MYSQL_HOST]:3306/[MYSQL_DATABASE]?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=[MYSQL_USER]
DB_PASSWORD=[MYSQL_PASSWORD]
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
PORT=10000
```

### 6. Activar Auto-Deploy

- Activa "Auto-Deploy"
- Selecciona la rama `main`

### 7. Crear el Servicio

1. Click en "Create Web Service"
2. Render comenzará a construir y desplegar
3. Espera 5-10 minutos

## 🔄 Migración de Datos (Opcional)

Si tienes datos en Railway MySQL que quieres migrar:

### Exportar de Railway:
```bash
mysqldump -h [RAILWAY_HOST] -u [USER] -p [DATABASE] > backup.sql
```

### Importar a Render:
```bash
mysql -h [RENDER_HOST] -u [USER] -p [DATABASE] < backup.sql
```

## ✅ Verificar el Deployment

1. Una vez completado, Render te dará una URL:
   - `https://bytgaming-backend.onrender.com`

2. Prueba los endpoints:
   - Swagger UI: `https://bytgaming-backend.onrender.com/swagger-ui.html`
   - API Docs: `https://bytgaming-backend.onrender.com/v3/api-docs`

## 📋 Checklist

- [ ] Cuenta creada en Render
- [ ] Base de datos MySQL creada en Render
- [ ] Servicio web creado en Render
- [ ] Variables de entorno configuradas
- [ ] Auto-Deploy activado
- [ ] Primer deployment exitoso
- [ ] API accesible en la URL de Render
- [ ] Datos migrados (si es necesario)

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
- Render usa el puerto 10000 por defecto
- La aplicación está configurada para usar `${PORT:10000}`
- No necesitas configurar un puerto manualmente

## 📚 Documentación Adicional

- `RENDER_DEPLOY.md` - Guía detallada de deployment en Render
- `ALTERNATIVAS_DEPLOY.md` - Otras alternativas de deployment
- `SOLUCION_ERRORES.md` - Guía de solución de problemas
- `RAILWAY_CONFIG.md` - Configuración de Railway (si decides actualizar el plan)

## 🆘 Soporte

Si tienes problemas:
1. Revisa `RENDER_DEPLOY.md` para la guía detallada
2. Revisa los logs en Render
3. Consulta la documentación de Render: https://render.com/docs
4. Revisa `SOLUCION_ERRORES.md` para errores comunes

## 🎉 Conclusión

**Recomendación:** Usa **Render** para desplegar el backend.

Es la opción más simple, tiene plan gratuito, y es perfecta para proyectos como este.

**Siguiente paso:** Sigue la guía en `RENDER_DEPLOY.md`

