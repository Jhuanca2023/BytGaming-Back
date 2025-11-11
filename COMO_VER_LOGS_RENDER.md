# 🔍 Cómo Ver los Logs de Build en Render

## 📋 Pasos Detallados

### Paso 1: Ve a tu Servicio Web

1. En Render Dashboard, click en **"bytgaming-backend"**
2. O ve directamente a: https://dashboard.render.com/web/srv-d497thvgi27c73c7a5pg

### Paso 2: Ve a la Pestaña "Logs"

1. En la parte superior del servicio, busca las pestañas:
   - Environment
   - Events
   - **Settings** ← NO esta
   - **Monitor**
   - **Logs** ← **CLICK AQUÍ**
   - Metrics
   - Manage

2. Click en la pestaña **"Logs"**

### Paso 3: Ver los Logs de Build

1. En la pestaña "Logs", verás diferentes secciones:
   - **Build Logs** - Logs del proceso de construcción
   - **Runtime Logs** - Logs cuando el servicio está corriendo

2. Busca el último deployment fallido:
   - Busca el mensaje: `Deploy failed for 6e88323`
   - Click en ese deployment para ver los logs completos

3. O simplemente desplázate hacia abajo en los logs para ver el último build

### Paso 4: Buscar el Error

En los logs, busca mensajes que contengan:

- `ERROR`
- `FAILED`
- `Build failed`
- `Compilation failure`
- `JAR file not found`
- `Cannot find`
- `exit code 1`

## 🔍 Qué Buscar en los Logs

### Ejemplo de Logs Exitosos:

```
Step 1/8 : FROM maven:3.8.6-eclipse-temurin-17 AS build
...
Step 4/8 : RUN mvn dependency:go-offline -B
[INFO] Downloading dependencies...
...
Step 6/8 : RUN mvn clean package -DskipTests -B
[INFO] Building jar: /workspace/target/app.jar
[INFO] BUILD SUCCESS
...
Step 7/8 : RUN ls -la target/
SUCCESS: JAR file found
```

### Ejemplo de Logs con Error:

```
Step 6/8 : RUN mvn clean package -DskipTests -B
[ERROR] Compilation failure
[ERROR] /workspace/src/.../SomeClass.java:[10:5] error: cannot find symbol
...
ERROR: JAR file not found!
Files in target directory:
```

## 📝 Cómo Copiar los Logs

1. En la pestaña "Logs", selecciona el texto del error
2. Copia el mensaje completo (Ctrl+C)
3. Pégalo aquí para que pueda ayudarte

## 🆘 Si No Puedes Ver los Logs

1. **Verifica que estés en el servicio correcto:**
   - Service ID: `srv-d497thvgi27c73c7a5pg`
   - Nombre: `bytgaming-backend`

2. **Intenta refrescar la página:**
   - Presiona F5 o Ctrl+R

3. **Verifica los permisos:**
   - Asegúrate de estar logueado en Render
   - Verifica que tengas acceso al servicio

## 🎯 Próximos Pasos

1. **Ve a los logs** siguiendo los pasos arriba
2. **Busca el error** en los logs
3. **Copia el mensaje de error completo**
4. **Pégalo aquí** para que pueda ayudarte a solucionarlo

## 📸 Captura de Pantalla

Si puedes, toma una captura de pantalla de los logs y compártela, o copia el texto del error.

