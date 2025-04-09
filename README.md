# Sistema de Gestión F1 2024  
**¡Bienvenidos al proyecto de gestión de datos de la Fórmula 1 2024!**  
*Aplicación Java para consultar pilotos, equipos y resultados usando POO.*  

---

## 🚀 Características Principales  
- **Modelado de entidades**:  
  - Clases: `Equipo`, `Piloto`, `Circuito`, `Carrera` con herencia y encapsulación.  
- **Datos oficiales 2024**:  
  - Datos inicialmente cargados desde APIs oficiales y almacenados en JSON
  - Sistema de datos precargados para consulta rápida y offline
- **Consultas dinámicas**:  
  - Tabla de posiciones del Mundial de Pilotos y Constructores
  - Resultados por carrera (posición de salida y llegada)
  - Detalles completos de pilotos/equipos
- **Documentación completa**:  
  - Diagramas UML + explicación de métodos/atributos en español

---

## 🛠️ Requisitos Técnicos  
- **Java Development Kit (JDK)**: Versión 21  
- **Maven**: Para gestión de dependencias  
- **Principios POO**:
  - Herencia (ej: `Piloto extends Persona`)
  - Encapsulación (atributos privados con getters/setters)
  - Polimorfismo (sobrescritura de métodos)

---

## 📂 Estructura del Proyecto
```
src/
├── main/java/
│   ├── model/     → Clases POJO (Equipo.java, Piloto.java)
│   ├── api/       → Cliente API histórico (usado inicialmente)
│   ├── data/      → Persistencia y datos precargados
│   └── ui/        → Interfaz consola (MainUI.java)
├── test/java/     → Tests unitarios
data/              → Archivos JSON con datos oficiales
docs/              → Documentación
├── uml/           → Diagramas UML
└── screenshots/   → Capturas de consultas
```

## 📥 Instalación y Uso
1. **Clonar el repositorio**:  
   ```bash  
   git clone https://github.com/FreddyB200/proyecto-F1-POO.git
   cd proyecto-F1-POO
   ```
2. **Compilar con Maven**:
   ```bash
   mvn clean install
   ```
3. **Ejecutar**:
   ```bash
   java -jar target/proyecto-F1-POO.jar
   ```

## 🔍 Menú Principal
1. Mostrar lista de pilotos
2. Ver clasificación de pilotos
3. Ver clasificación de constructores
4. Ver calendario de carreras
5. Ver estadísticas detalladas de piloto
6. Ver resultados por carrera
7. Ver próxima carrera
8. Ver información de equipo
9. Salir

Cada opción proporciona información detallada y actualizada de la temporada 2024.

## 🌐 Proceso de Desarrollo
El proyecto se desarrolló en tres fases principales:

1. **Fase Inicial - Carga de Datos**:
   - Integración con APIs de F1 (Ergast y OpenF1)
   - Recopilación y validación de datos oficiales
   - Almacenamiento en formato JSON

2. **Fase Actual - Sistema Offline**:
   - Datos precargados para acceso rápido
   - Sin dependencia de APIs externas
   - Optimizado para consultas locales

3. **Mantenimiento**:
   - Documentación detallada del proceso
   - Sistema preparado para actualizaciones manuales
   - Preservación de la arquitectura original

## 📝 Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

### Autores
- **Freddy Bautista** - *Desarrollo de APIs y conexiones externas*
- **Javier Esquivel** - *Modelado de datos y persistencia*
- **Sebastian Viloria** - *Interfaz de usuario y documentación*

Este proyecto es parte del curso de Programación Orientada a Objetos.

## 🔗 Referencias
- Datos oficiales: [Formula1.com](https://www.formula1.com)
- Documentación completa en `/docs`