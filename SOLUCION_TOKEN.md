# 🔐 Solución: Error "Project Token not found" en Railway

## ❌ Problema

El workflow de GitHub Actions está fallando con el error:
```
Project Token not found
Error: Process completed with exit code 1.
```

## ✅ Solución: Dos Opciones

### Opción 1: Conectar Railway directamente con GitHub (Recomendado)

Esta es la forma más simple y recomendada. Railway detectará automáticamente los cambios desde GitHub.

#### Pasos:

1. **En Railway Dashboard:**
   - Ve a tu proyecto en Railway
   - Click en "Settings" (Configuración)
   - Ve a "Connect GitHub Repo"
   - Selecciona el repositorio `BytGaming-Back`
   - Railway se conectará automáticamente

2. **Configurar Auto-Deploy:**
   - En Railway, ve a "Settings" → "Source"
   - Activa "Auto Deploy"
   - Selecciona la rama `main` o `master`
   - Railway desplegará automáticamente cuando hagas push

3. **Desactivar GitHub Actions (Opcional):**
   - Si Railway está conectado directamente, no necesitas GitHub Actions
   - Puedes desactivar el workflow o dejarlo como backup

**Ventajas:**
- ✅ Más simple
- ✅ No necesitas configurar tokens
- ✅ Railway maneja todo automáticamente
- ✅ Menos puntos de fallo

### Opción 2: Configurar Railway Token en GitHub (Alternativa)

Si prefieres usar GitHub Actions, necesitas configurar el token.

#### Pasos:

1. **Obtener Railway Token:**
   - Ve a Railway Dashboard
   - Click en tu perfil (arriba a la derecha)
   - Selecciona "Account Settings"
   - Ve a la pestaña "Tokens"
   - Click en "New Token"
   - Dale un nombre (ej: "GitHub Actions")
   - **Copia el token** (solo se muestra una vez)

2. **Añadir Secret en GitHub:**
   - Ve a tu repositorio en GitHub: https://github.com/Jhuanca2023/BytGaming-Back
   - Ve a "Settings" → "Secrets and variables" → "Actions"
   - Click en "New repository secret"
   - **Name:** `RAILWAY_TOKEN`
   - **Value:** Pega el token que copiaste
   - Click en "Add secret"

3. **Verificar el Workflow:**
   - El workflow debería funcionar ahora
   - Puedes hacer un push de prueba o ejecutar manualmente desde "Actions"

**Ventajas:**
- ✅ Más control sobre el proceso de deployment
- ✅ Puedes añadir pasos adicionales antes del deployment
- ✅ Logs en GitHub Actions

## 🚀 Configuración Recomendada

### Para la mayoría de casos: **Opción 1 (Railway + GitHub Direct)**

1. Conecta Railway directamente con GitHub
2. Configura Auto-Deploy en Railway
3. Desactiva o elimina el workflow de GitHub Actions (opcional)

### Workflow simplificado (si usas Opción 1)

Puedes eliminar o comentar el workflow de GitHub Actions, ya que Railway manejará el deployment automáticamente.

## 📋 Checklist

### Opción 1 (Recomendada):
- [ ] Railway conectado con GitHub
- [ ] Auto-Deploy activado en Railway
- [ ] Rama `main` o `master` seleccionada
- [ ] Primer deployment exitoso

### Opción 2 (Alternativa):
- [ ] Railway Token generado
- [ ] Secret `RAILWAY_TOKEN` añadido en GitHub
- [ ] Workflow de GitHub Actions configurado
- [ ] Primer deployment exitoso

## 🔧 Verificar Configuración

### En Railway:
1. Ve a "Settings" → "Source"
2. Verifica que el repositorio está conectado
3. Verifica que Auto-Deploy está activado
4. Verifica que la rama correcta está seleccionada

### En GitHub:
1. Ve a "Settings" → "Secrets and variables" → "Actions"
2. Verifica que `RAILWAY_TOKEN` existe (si usas Opción 2)
3. Verifica que el workflow está en `.github/workflows/deploy.yml`

## 🐛 Solución de Problemas

### Error: "Project Token not found"
**Solución:**
- Si usas Opción 1: Asegúrate de que Railway está conectado con GitHub
- Si usas Opción 2: Verifica que el secret `RAILWAY_TOKEN` está configurado en GitHub

### Error: "Repository not found"
**Solución:**
- Verifica que Railway tiene acceso al repositorio
- Verifica que el repositorio es público o Railway tiene permisos

### Error: "Auto-deploy not working"
**Solución:**
- Verifica que Auto-Deploy está activado en Railway
- Verifica que estás haciendo push a la rama correcta
- Verifica que Railway está conectado con el repositorio correcto

## 📝 Notas Importantes

- Railway puede desplegar automáticamente desde GitHub sin necesidad de GitHub Actions
- Si usas GitHub Actions, necesitas configurar el token como secret
- La Opción 1 es más simple y recomendada para la mayoría de casos
- Railway detecta cambios automáticamente cuando está conectado con GitHub

## 🆘 Si sigues teniendo problemas

1. Verifica que Railway está conectado con GitHub
2. Verifica que Auto-Deploy está activado
3. Revisa los logs en Railway
4. Revisa los logs en GitHub Actions (si los usas)
5. Consulta la documentación de Railway: https://docs.railway.app

