# 🔧 Comandos Git para Desplegar

## Comandos para ejecutar en orden:

### 1. Verificar estado actual
```bash
cd D:\BytGaming\Back
git status
```

### 2. Añadir todos los archivos
```bash
git add .
```

### 3. Hacer commit inicial
```bash
git commit -m "Initial commit: Backend setup for Railway deployment"
```

### 4. Cambiar a rama main (si es necesario)
```bash
git branch -M main
```

### 5. Subir al repositorio remoto
```bash
git push -u origin main
```

## ⚠️ Si el repositorio remoto ya tiene contenido:

Si el repositorio en GitHub ya tiene commits, primero necesitas hacer pull:

```bash
git pull origin main --allow-unrelated-histories
git add .
git commit -m "Merge with remote repository"
git push -u origin main
```

## 🔄 Para futuros cambios:

```bash
git add .
git commit -m "Descripción de los cambios"
git push origin main
```

## 📝 Notas:

- Railway detectará automáticamente los cambios cuando hagas push a `main`
- El workflow de GitHub Actions se ejecutará automáticamente
- Revisa los logs en Railway para ver el progreso del deployment
