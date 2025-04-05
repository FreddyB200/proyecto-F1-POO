# Sistema de Gestión F1 2024  
**¡Bienvenidos al proyecto de gestión de datos de la Fórmula 1 2024!**  
*Aplicación Java para consultar pilotos, equipos y resultados usando POO.*  

---

## 🚀 Características Principales  
- **Modelado de entidades**:  
  - Clases: `Equipo`, `Piloto`, `Circuito`, `Carrera` con herencia y encapsulación.  
- **Datos oficiales 2024**:  
  - Carga automática desde archivos JSON (sin entrada por consola).  
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
│   ├── api/       → Cliente API (APIClient.java)
│   ├── data/      → Persistencia (DataManager.java)
│   └── ui/        → Interfaz consola (MainUI.java)
├── test/java/     → Tests unitarios
data/              → Archivos JSON
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
1. Mostrar ranking de pilotos
2. Ver resultados por carrera
3. Consultar equipo
4. Salir

## 🌐 APIs Utilizadas
- **Ergast F1 API** (https://ergast.com/mrd/):
  - Datos históricos F1 desde 1950
  - Endpoints para pilotos, constructores y resultados
  - *Nota: API deprecada después de 2024*

- **OpenF1 API** (https://openf1.org/):
  - Datos en tiempo real
  - Telemetría, tiempos por vuelta, radio del equipo
  - Formato JSON/CSV

## 📝 Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

### Autores
- Freddy Bautista
- Javier Esquivel
- Sebastian Viloria

Este proyecto es parte del curso de Programación Orientada a Objetos.

## 🔗 Referencias
- Datos oficiales: [Formula1.com](https://www.formula1.com)
- Documentación completa en `/docs`