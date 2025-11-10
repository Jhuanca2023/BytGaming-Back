# 📋 Instrucciones de Despliegue - BytGaming Backend

## 🚀 Inicio Rápido

### 1. Preparar el Repositorio

```bash
cd Back
git add .
git commit -m "Initial commit: Backend setup for Railway deployment"
git branch -M main
git push -u origin main
```

### 2. Configurar Railway

#### a) Crear Proyecto en Railway
1. Ve a https://railway.app
2. Inicia sesión con GitHub
3. Click en "New Project"
4. Selecciona "Deploy from GitHub repo"
5. Busca `BytGaming-Back` y conéctalo

#### b) Añadir Base de Datos MySQL
1. En Railway, click en "+ New"
2. Selecciona "Database" → "MySQL"
3. Railway creará automáticamente las variables de entorno de MySQL

#### c) Configurar Variables de Entorno del Backend
En la pestaña "Variables" del servicio backend, añade:

```env
DB_URL=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USER=${MYSQL_USER}
DB_PASSWORD=${MYSQL_PASSWORD}
SECURITY_JWT_KEY_PRIVATE=cb0bf0cb4921bb9602e27ce65b34c339ef926f2d4a982e299924d23baca18a43
SECURITY_JWT_USER_GENERATOR=AUTH0JWT-BACKEND
```

#### d) Configurar Root Directory (si es necesario)
1. Ve a Settings → General
2. Si Railway no detecta automáticamente, configura:
   - **Root Directory**: `BytGaming`

### 3. Configurar GitHub Secrets

1. Ve a https://github.com/Jhuanca2023/BytGaming-Back/settings/secrets/actions
2. Click en "New repository secret"
3. Añade:
   - **Name**: `RAILWAY_TOKEN`
   - **Value**: [Obtén el token de Railway - ver abajo]

#### Obtener Railway Token:
1. Ve a Railway Dashboard
2. Click en tu perfil → Account Settings
3. Ve a "Tokens"
4. Click en "New Token"
5. Copia el token y añádelo como secret en GitHub

### 4. Desplegar

Railway desplegará automáticamente cuando hagas push a `main`:

```bash
git push origin main
```

O puedes desplegar manualmente desde Railway dashboard.

## 📁 Estructura del Proyecto

```
Back/
├── BytGaming/              # Aplicación Spring Boot
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── gateway/                # Spring Cloud Gateway (opcional)
├── .github/workflows/      # GitHub Actions
├── docker-compose.yml      # Para desarrollo local
├── railway.json           # Configuración de Railway
└── README.md              # Documentación principal
```

## 🔧 Configuración Local (Opcional)

Para desarrollo local:

```bash
cd Back
docker-compose up -d
```

Esto iniciará MySQL en el puerto 3306.

Luego ejecuta la aplicación:

```bash
cd BytGaming
./mvnw spring-boot:run
```

## 📚 Documentación Adicional

- **README.md**: Documentación general del proyecto
- **DEPLOYMENT.md**: Guía detallada de despliegue
- **SETUP_RAILWAY.md**: Configuración paso a paso de Railway
- **VARIABLES_ENTORNO.md**: Explicación de variables de entorno

## ✅ Checklist de Despliegue

- [ ] Repositorio Git inicializado y conectado
- [ ] Código subido a GitHub
- [ ] Proyecto creado en Railway
- [ ] Servicio MySQL añadido en Railway
- [ ] Variables de entorno configuradas en Railway
- [ ] Railway Token añadido como secret en GitHub
- [ ] Root Directory configurado (si es necesario)
- [ ] Primer deployment exitoso
- [ ] URLs de la API verificadas

## 🐛 Problemas Comunes

### Error: Cannot connect to database
- Verifica que las variables de entorno están correctas
- Asegúrate de que el servicio MySQL está corriendo
- Revisa los logs en Railway

### Error: Build fails
- Verifica que el Dockerfile está correcto
- Revisa los logs de build en Railway
- Asegúrate de que todas las dependencias están en pom.xml

### Error: Port already in use
- Railway asigna el puerto automáticamente
- La aplicación ya está configurada para usar `${PORT}`
- No necesitas configurar un puerto manualmente

## 🆘 Soporte

Si tienes problemas:
1. Revisa los logs en Railway
2. Verifica las variables de entorno
3. Consulta la documentación en los archivos MD
4. Revisa la documentación de Railway: https://docs.railway.app

## 🎉 ¡Listo!

Una vez configurado, cada push a `main` desplegará automáticamente tu backend en Railway.
