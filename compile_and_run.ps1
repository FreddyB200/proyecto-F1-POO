# Crear directorios necesarios
New-Item -ItemType Directory -Force -Path "target/classes"

# Descargar dependencias si no existen
$gsonJar = "lib/gson-2.10.1.jar"
if (-not (Test-Path $gsonJar)) {
    New-Item -ItemType Directory -Force -Path "lib"
    Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar" -OutFile $gsonJar
}

# Compilar todas las clases
Write-Host "Compilando clases..."
javac -cp "$gsonJar" -d target/classes (Get-ChildItem -Path "src/main/java/org/f1" -Recurse -Filter "*.java").FullName

# Ejecutar la clase de prueba
Write-Host "`nEjecutando pruebas..."
java -cp "target/classes;$gsonJar" org.f1.TestF1System 