# 🔍 Cómo Ver los Logs de Build en Render

## 📋 Pasos para Ver los Logs

### Paso 1: Ve a tu Servicio Web

1. En Render Dashboard, click en tu servicio web `backend de bytgaming`
2. Deberías ver la página del servicio

### Paso 2: Ve a la Pestaña "Logs"

1. En la parte superior del servicio, busca la pestaña **"Logs"**
2. Click en **"Logs"**
3. Verás los logs del servicio

### Paso 3: Ver los Logs de Build

1. En la pestaña **"Logs"**, busca la sección de **"Build Logs"** o **"Deploy Logs"**
2. O busca el último deployment fallido
3. Click en el deployment para ver los logs completos

### Paso 4: Buscar el Error

1. Busca mensajes que contengan:
   - `ERROR`
   - `FAILED`
   - `Build failed`
   - `Compilation failure`
   - `JAR file not found`
   - `Cannot find`

2. Copia el mensaje de error completo

## 🔍 Qué Buscar en los Logs

### Errores Comunes:

1. **Error de Compilación:**
   ```
   [ERROR] Compilation failure
   [ERROR] /workspace/src/.../SomeClass.java:[X:Y] error: ...
   ```

2. **Dependencia Faltante:**
   ```
   [ERROR] Failed to execute goal on project ...
   [ERROR] Could not resolve dependencies
   ```

3. **JAR No Encontrado:**
   ```
   ERROR: JAR file not found!
   Files in target directory:
   ```

4. **Error de Dockerfile:**
   ```
   ERROR: failed to solve: ...
   COPY failed: file not found
   ```

## 📝 Ejemplo de Logs

Cuando veas los logs, deberías ver algo como:

```
Step 1/10 : FROM maven:3.8.6-eclipse-temurin-17 AS build
...
Step 5/10 : RUN mvn dependency:go-offline -B
...
Step 7/10 : RUN mvn clean package -DskipTests -B
[INFO] Building jar: /workspace/target/app.jar
...
Step 8/10 : RUN ls -la target/
target/app.jar
SUCCESS: JAR file found
```

Si hay un error, verás:

```
[ERROR] Compilation failure
[ERROR] ...
ERROR: JAR file not found!
```

## 🆘 Si No Puedes Ver los Logs

1. **Verifica que el servicio esté creado:**
   - Asegúrate de que el servicio web esté creado en Render
   - Verifica que esté conectado al repositorio de GitHub

2. **Verifica los permisos:**
   - Asegúrate de tener acceso al servicio
   - Verifica que estés logueado en Render

3. **Contacta a soporte:**
   - Si no puedes ver los logs, contacta a soporte de Render
   - Proporciona el ID del servicio: `srv-d497thvgi27c73c7a5pg`

## 🎯 Próximos Pasos

1. **Ve a los logs** y copia el error completo
2. **Busca el error** en `SOLUCION_ERROR_BUILD_RENDER.md`
3. **Aplica la solución** correspondiente
4. **Haz commit y push** de los cambios
5. **Monitorea el nuevo build** en Render

## 📋 Checklist

- [ ] Ve a Render Dashboard
- [ ] Click en tu servicio web
- [ ] Ve a la pestaña "Logs"
- [ ] Busca el último deployment fallido
- [ ] Copia el mensaje de error completo
- [ ] Busca la solución en `SOLUCION_ERROR_BUILD_RENDER.md`

