@echo off
REM Script para verificar que el build funciona correctamente (Windows)

echo Verificando configuracion del proyecto...

REM Verificar que estamos en el directorio correcto
if not exist "BytGaming\pom.xml" (
    echo Error: No se encontro BytGaming\pom.xml
    echo Asegurate de ejecutar este script desde el directorio Back\
    exit /b 1
)

echo Estructura de directorios correcta

REM Verificar versión en pom.xml
findstr /C:"<version>1.0.0</version>" BytGaming\pom.xml >nul
if errorlevel 1 (
    echo Error: La version en pom.xml no es 1.0.0
    exit /b 1
)

echo Version correcta en pom.xml: 1.0.0

REM Verificar finalName en pom.xml
findstr /C:"<finalName>app</finalName>" BytGaming\pom.xml >nul
if errorlevel 1 (
    echo Error: finalName no esta configurado como 'app' en pom.xml
    exit /b 1
)

echo finalName configurado correctamente

REM Verificar Dockerfile
if not exist "BytGaming\Dockerfile" (
    echo Error: No se encontro BytGaming\Dockerfile
    exit /b 1
)

echo Dockerfile encontrado

REM Verificar que el Dockerfile copia app.jar
findstr /C:"app.jar" BytGaming\Dockerfile >nul
if errorlevel 1 (
    echo Error: El Dockerfile no copia app.jar
    exit /b 1
)

echo Dockerfile configura app.jar correctamente

REM Intentar build (opcional)
if "%1"=="--build" (
    echo Intentando build...
    cd BytGaming
    call mvnw.cmd clean package -DskipTests -B
    if errorlevel 1 (
        echo Error: Build fallo
        exit /b 1
    )
    if exist "target\app.jar" (
        echo Build exitoso, app.jar generado
    ) else (
        echo Error: Build exitoso pero app.jar no encontrado
        exit /b 1
    )
)

echo Todas las verificaciones pasaron

