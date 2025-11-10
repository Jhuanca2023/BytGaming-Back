# 🚀 Guía de Deployment - BytGaming Backend

## ⚠️ IMPORTANTE: Error "Project Token not found"

Si ves el error "Project Token not found" en GitHub Actions, tienes **dos opciones**:

### ✅ Opción 1: Conectar Railway directamente con GitHub (RECOMENDADO)

**Esta es la forma más simple y recomendada.**

1. **En Railway Dashboard:**
   - Ve a tu proyecto
   - Click en "Settings"
   - Ve a "Connect GitHub Repo"
   - Selecciona `BytGaming-Back`
   - Railway se conectará automáticamente

2. **Activar Auto-Deploy:**
   - En Railway: "Settings" → "Source"
   - Activa "Auto Deploy"
   - Selecciona la rama `main`
   - Railway desplegará automáticamente en cada push

3. **Listo:**
   - Ya no necesitas GitHub Actions
   - Railway detecta cambios automáticamente
   - Más simple y confiable

**Ventajas:**
- ✅ No necesitas configurar tokens
- ✅ Railway maneja todo automáticamente
- ✅ Menos puntos de fallo
- ✅ Más simple de mantener

### 🔧 Opción 2: Configurar Railway Token (Alternativa)

Si prefieres usar GitHub Actions:

1. **Obtener Token:**
   - Railway Dashboard → Perfil → Account Settings → Tokens
   - "New Token" → Copia el token

2. **Añadir Secret en GitHub:**
   - GitHub Repo → Settings → Secrets and variables → Actions
   - "New repository secret"
   - Name: `RAILWAY_TOKEN`
   - Value: [Pega el token]

3. **Verificar:**
   - El workflow debería funcionar ahora
   - Consulta `SOLUCION_TOKEN.md` para más detalles

## 📋 Configuración en Railway

### Variables de Entorno Requeridas:

```env
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

### Root Directory:
```
BytGaming
```

### Build Command:
```
(Dejar vacío - Railway detecta automáticamente)
```

### Start Command:
```
(Dejar vacío - Railway usa el Dockerfile)
```

## 🚀 Proceso de Deployment

### Con Opción 1 (Recomendado):

1. **Conecta Railway con GitHub** (una vez)
2. **Activa Auto-Deploy** (una vez)
3. **Haz push a GitHub:**
   ```bash
   git add .
   git commit -m "Cambios"
   git push origin main
   ```
4. **Railway desplegará automáticamente** ✅

### Con Opción 2 (GitHub Actions):

1. **Configura RAILWAY_TOKEN** en GitHub Secrets (una vez)
2. **Haz push a GitHub:**
   ```bash
   git add .
   git commit -m "Cambios"
   git push origin main
   ```
3. **GitHub Actions ejecutará el workflow**
4. **Railway recibirá el deployment** ✅

## ✅ Checklist de Deployment

- [ ] Railway conectado con GitHub (Opción 1) O RAILWAY_TOKEN configurado (Opción 2)
- [ ] Auto-Deploy activado (si usas Opción 1)
- [ ] Variables de entorno configuradas en Railway
- [ ] Root Directory configurado como `BytGaming`
- [ ] Servicio MySQL corriendo
- [ ] Código subido a GitHub
- [ ] Deployment exitoso
- [ ] API accesible en la URL de Railway

## 🐛 Solución de Problemas

### Error: "Project Token not found"
**Solución:** Consulta `SOLUCION_TOKEN.md`

### Error: "Cannot connect to database"
**Solución:** Verifica las variables de entorno en Railway

### Error: "Build failed"
**Solución:** Revisa los logs en Railway

### Error: "Port already in use"
**Solución:** Railway asigna el puerto automáticamente, no necesitas configurarlo

## 📚 Documentación Adicional

- `SOLUCION_TOKEN.md` - Solución detallada del error de token
- `SOLUCION_ERRORES.md` - Guía de solución de problemas
- `RAILWAY_CONFIG.md` - Configuración detallada de Railway
- `CAMBIOS_REALIZADOS.md` - Resumen de cambios realizados

## 🆘 Soporte

Si tienes problemas:
1. Consulta `SOLUCION_TOKEN.md` para el error de token
2. Consulta `SOLUCION_ERRORES.md` para otros errores
3. Revisa los logs en Railway
4. Revisa la documentación de Railway: https://docs.railway.app

## 🎉 ¡Listo!

Una vez configurado, el deployment será automático con cada push a `main`.

