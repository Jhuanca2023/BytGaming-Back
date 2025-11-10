#!/bin/bash

# Script para verificar que el build funciona correctamente

echo "🔍 Verificando configuración del proyecto..."

# Verificar que estamos en el directorio correcto
if [ ! -f "BytGaming/pom.xml" ]; then
    echo "❌ Error: No se encontró BytGaming/pom.xml"
    echo "   Asegúrate de ejecutar este script desde el directorio Back/"
    exit 1
fi

echo "✅ Estructura de directorios correcta"

# Verificar versión en pom.xml
VERSION=$(grep -A 1 "<artifactId>BytGaminG</artifactId>" BytGaming/pom.xml | grep "<version>" | sed 's/.*<version>\(.*\)<\/version>.*/\1/')
if [ "$VERSION" != "1.0.0" ]; then
    echo "❌ Error: La versión en pom.xml no es 1.0.0 (encontrada: $VERSION)"
    exit 1
fi

echo "✅ Versión correcta en pom.xml: $VERSION"

# Verificar finalName en pom.xml
if ! grep -q "<finalName>app</finalName>" BytGaming/pom.xml; then
    echo "❌ Error: finalName no está configurado como 'app' en pom.xml"
    exit 1
fi

echo "✅ finalName configurado correctamente"

# Verificar Dockerfile
if [ ! -f "BytGaming/Dockerfile" ]; then
    echo "❌ Error: No se encontró BytGaming/Dockerfile"
    exit 1
fi

echo "✅ Dockerfile encontrado"

# Verificar que el Dockerfile copia app.jar
if ! grep -q "app.jar" BytGaming/Dockerfile; then
    echo "❌ Error: El Dockerfile no copia app.jar"
    exit 1
fi

echo "✅ Dockerfile configura app.jar correctamente"

# Intentar build (opcional)
if [ "$1" == "--build" ]; then
    echo "🔨 Intentando build..."
    cd BytGaming
    mvn clean package -DskipTests -B
    if [ $? -eq 0 ]; then
        if [ -f "target/app.jar" ]; then
            echo "✅ Build exitoso, app.jar generado"
        else
            echo "❌ Error: Build exitoso pero app.jar no encontrado"
            exit 1
        fi
    else
        echo "❌ Error: Build falló"
        exit 1
    fi
fi

echo "✅ Todas las verificaciones pasaron"

