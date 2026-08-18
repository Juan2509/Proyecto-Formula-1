# 🏎️ Simulación de Fórmula 1

Sistema de simulación interactiva de Fórmula 1 desarrollado en Java que permite gestionar equipos, pilotos, vehículos y circuitos, además de configurar vehículos y realizar simulaciones de clasificación realistas.

---

## 📋 Tabla de Contenidos

- [Descripción General](#-descripción-general)
- [Características Principales](#-características-principales)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Guía de Uso](#-guía-de-uso)
- [Datos Iniciales](#-datos-iniciales)
- [Cómo Funciona la Simulación](#-cómo-funciona-la-simulación)
- [Ejemplos de Uso](#-ejemplos-de-uso)
- [Arquitectura del Sistema](#-arquitectura-del-sistema)
- [Funcionalidades por Rol](#-funcionalidades-por-rol)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Limitaciones Conocidas](#-limitaciones-conocidas)
- [Posibles Mejoras Futuras](#-posibles-mejoras-futuras)
- [Autores](#-autores)

---

## 🎯 Descripción General

**Simulación de Fórmula 1** es un proyecto académico que combina gestión de datos con simulación realista de clasificaciones de Fórmula 1. El sistema permite:

- Gestionar información de equipos, pilotos, vehículos y circuitos
- Configurar parámetros técnicos de los vehículos
- Simular sesiones de clasificación con tiempos calculados mediante una fórmula que considera múltiples factores
- Consultar y comparar resultados históricos

El proyecto está diseñado con una arquitectura modular que separa responsabilidades y permite fácil mantenimiento y extensión.

---

## ✨ Características Principales

### 📊 **Módulos de Gestión (CRUD Completo)**
- ✅ **Gestión de Equipos** - Registrar, listar, buscar, editar y eliminar equipos
- ✅ **Gestión de Pilotos** - CRUD completo con asignación a equipos y estadísticas
- ✅ **Gestión de Vehículos** - CRUD con asignación inteligente de pilotos y comparación
- ✅ **Gestión de Circuitos** - CRUD con ganadores históricos y características técnicas

### ⚙️ **Configuración Avanzada**
- Modo de conducción (Normal, Agresiva, Ahorro de combustible)
- Carga aerodinámica (Baja, Media, Alta)
- Presión de neumáticos (Baja, Estándar, Alta)
- Estrategia de combustible (Agresiva, Balanceada, Ahorro)
- Visualización de efectos esperados de cada configuración

### 🌦️ **Sistema Climático**
- Generación aleatoria de condiciones (Seco 60%, Lluvioso 30%, Extremo 10%)
- Modificadores realistas que afectan velocidad, consumo y desgaste

### 🏁 **Simulación de Clasificación**
- Cálculo de tiempos basado en fórmula completa
- Consideración de: vehículo, piloto, circuito, configuración, clima
- Variación aleatoria para simular impredecibilidad
- Clasificación ordenada con formato de tiempo profesional (mm:ss.sss)

### 📈 **Historial y Estadísticas**
- Guardado automático de sesiones
- Consulta por ID o circuito
- Comparación entre sesiones
- Estadísticas de poles por piloto y sesiones por circuito

---

## 📁 Estructura del Proyecto

```
formula1/
├── src/main/java/com/formula1/
│   ├── Main.java                          # Punto de entrada y menús
│   ├── ModuloPilotosEquipos.java         # Módulo separado de pilotos/equipos
│   │
│   ├── [Modelos]
│   ├── Piloto.java                       # Clase modelo de piloto
│   ├── Equipo.java                       # Clase modelo de equipo
│   ├── Vehiculo.java                     # Clase modelo de vehículo
│   ├── Circuito.java                     # Clase modelo de circuito
│   ├── ConfiguracionVehiculo.java        # Configuración de vehículo
│   ├── CondicionClimatica.java           # Enum de condiciones climáticas
│   ├── ResultadoClasificacion.java       # Resultado individual
│   ├── SesionClasificacion.java          # Sesión completa
│   │
│   ├── [Gestores]
│   ├── GestorPilotos.java               # Lógica de negocio de pilotos
│   ├── GestorEquipos.java               # Lógica de negocio de equipos
│   ├── GestorVehiculos.java             # Lógica de negocio de vehículos
│   ├── GestorCircuitos.java             # Lógica de negocio de circuitos
│   ├── GestorConfiguraciones.java       # Gestión de configuraciones
│   ├── GestorHistorial.java             # Gestión del historial
│   │
│   ├── [Simulación]
│   ├── MotorSimulacion.java             # Motor principal de simulación
│   └── GeneradorClima.java              # Generador de clima aleatorio
│
├── pom.xml                               # Configuración de Maven
└── README.md                             # Este archivo
```

### **Descripción de Componentes Principales**

**Modelos**: Clases que representan las entidades del dominio (Piloto, Equipo, etc.)

**Gestores**: Contienen la lógica de negocio y operaciones CRUD para cada entidad

**Simulación**: Motor que calcula tiempos y genera resultados de clasificación

---

## 🔧 Requisitos Previos

- **Java JDK 17** o superior
- **Maven 3.6+** (opcional, para compilación)
- **IDE recomendado**: IntelliJ IDEA, Eclipse o VS Code con extensión Java

---

## 🚀 Instalación y Ejecución

### **Opción 1: Con Maven**

```bash
# 1. Clonar el repositorio
git clone <url-del-repositorio>
cd Proyecto-Formula-1/formula1

# 2. Compilar el proyecto
mvn compile

# 3. Ejecutar el programa
mvn exec:java -Dexec.mainClass="com.formula1.Main"
```

### **Opción 2: Con IDE**

1. Abrir el proyecto en tu IDE favorito
2. Asegurarse de que el JDK 17 esté configurado
3. Ejecutar la clase `Main.java`

### **Opción 3: Línea de comandos (sin Maven)**

```bash
# 1. Compilar
javac -d bin -sourcepath src/main/java src/main/java/com/formula1/*.java

# 2. Ejecutar
java -cp bin com.formula1.Main
```

---

## 📖 Guía de Uso

### **Menú Principal**

Al iniciar el programa, se presenta el menú principal con 7 opciones:

```
--- [ Simulador de Formula 1 ] ---

1. Gestión de circuitos
2. Gestión de pilotos y equipos
3. Gestión de vehículos
4. Configuración de simulación
5. Iniciar simulación
6. Historial de resultados
7. Salir
```

### **1. Gestión de Circuitos**

Permite administrar los circuitos disponibles:
- **Agregar**: Registrar nuevos circuitos con características técnicas
- **Listar**: Ver todos los circuitos registrados
- **Buscar**: Buscar por nombre o país
- **Editar**: Modificar datos de un circuito existente
- **Eliminar**: Eliminar circuitos del sistema
- **Ganadores históricos**: Registrar y consultar ganadores por año

### **2. Gestión de Pilotos y Equipos**

Módulo dividido en **Administrador** y **Usuario**:

**Administrador:**
- Gestionar equipos (CRUD completo)
- Gestionar pilotos (CRUD completo con asignación a equipos)

**Usuario:**
- Listar y buscar equipos
- Listar y buscar pilotos
- Consultar especificaciones de vehículos

### **3. Gestión de Vehículos**

Administración completa de vehículos:
- Agregar vehículos (predefinidos o personalizados)
- Listar vehículos (paginado: 3 por página)
- Buscar vehículo por modelo
- Editar especificaciones
- Eliminar vehículos
- Configurar rendimiento por modo de conducción
- Asignar pilotos (validación de equipo)
- Comparar dos vehículos

### **4. Configuración de Simulación**

Configurar parámetros del vehículo antes de la simulación:
- **Modo de conducción**: Normal, Agresiva o Ahorro
- **Carga aerodinámica**: Baja (más velocidad), Media (balanceada), Alta (más agarre)
- **Presión de neumáticos**: Baja (más agarre), Estándar, Alta (menos desgaste)
- **Estrategia de combustible**: Agresiva, Balanceada o Ahorro

Cada opción muestra sus efectos esperados en rendimiento, consumo y desgaste.

### **5. Iniciar Simulación**

Proceso de simulación:
1. Seleccionar circuito de la lista disponible
2. Sistema genera clima aleatorio automáticamente
3. Calcula tiempos para los 11 pilotos usando la fórmula completa
4. Muestra tabla de clasificación ordenada
5. Opción de guardar la sesión en el historial

### **6. Historial de Resultados**

Consultar sesiones anteriores:
- Listar todas las sesiones guardadas
- Consultar sesión específica (ver clasificación completa)
- Consultar sesiones por circuito
- Comparar dos sesiones (diferencias de tiempo)
- Ver estadísticas generales

---

## 📦 Datos Iniciales

El sistema viene pre-cargado con datos reales de la temporada 2026:

### **11 Pilotos Predefinidos**

| ID | Piloto | Equipo | Experiencia | Habilidad |
|----|--------|--------|-------------|-----------|
| 1 | Lando Norris | McLaren | 85 | 88 |
| 2 | George Russell | Mercedes | 82 | 86 |
| 3 | Max Verstappen | Red Bull Racing | 95 | 98 |
| 4 | Charles Leclerc | Ferrari | 90 | 92 |
| 5 | Carlos Sainz | Williams | 85 | 90 |
| 6 | Liam Lawson | Racing Bulls | 75 | 70 |
| 7 | Fernando Alonso | Aston Martin | 88 | 95 |
| 8 | Esteban Ocon | Haas | 78 | 82 |
| 9 | Nico Hülkenberg | Audi | 80 | 88 |
| 10 | Pierre Gasly | Alpine | 82 | 85 |
| 11 | Sergio Pérez | Cadillac | 83 | 87 |

### **11 Equipos**

McLaren, Mercedes, Red Bull Racing, Ferrari, Williams, Racing Bulls, Aston Martin, Haas, Audi, Alpine, Cadillac

### **11 Vehículos**

| Equipo | Modelo | Motor | Vel. Máx | 0-100 |
|--------|--------|-------|----------|-------|
| Red Bull Racing | RB22 | Red Bull Ford | 358 km/h | 2.3s |
| Ferrari | SF-26 | Ferrari | 360 km/h | 2.3s |
| McLaren | MCL40 | Mercedes | 355 km/h | 2.4s |
| Mercedes | W17 | Mercedes | 352 km/h | 2.4s |
| Aston Martin | AMR26 | Honda | 348 km/h | 2.5s |
| Williams | FW48 | Mercedes | 350 km/h | 2.5s |
| Alpine | A526 | Mercedes | 345 km/h | 2.6s |
| Haas | VF-26 | Ferrari | 347 km/h | 2.6s |
| Audi | R26 | Audi | 346 km/h | 2.6s |
| Racing Bulls | VCARB03 | Ford | 349 km/h | 2.5s |
| Cadillac | MAC-26 | Ferrari | 344 km/h | 2.7s |

### **7 Circuitos Icónicos**

| Circuito | País | Longitud | Vueltas | Récord |
|----------|------|----------|---------|--------|
| Circuit de Monaco | Mónaco | 3.337 km | 78 | 1:12.909 (L. Hamilton, 2021) |
| Silverstone Circuit | Reino Unido | 5.891 km | 52 | 1:27.097 (M. Verstappen, 2020) |
| Spa-Francorchamps | Bélgica | 7.004 km | 44 | 1:44.701 (S. Pérez, 2024) |
| Autodromo Nazionale Monza | Italia | 5.793 km | 53 | 1:20.901 (L. Norris, 2025) |
| Interlagos | Brasil | 4.309 km | 71 | 1:10.540 (V. Bottas, 2018) |
| Yas Marina Circuit | EAU | 5.281 km | 58 | 1:25.637 (K. Magnussen, 2024) |
| Suzuka | Japón | 5.807 km | 53 | 1:30.965 (A. Antonelli, 2025) |

---

## 🧮 Cómo Funciona la Simulación

### **Fórmula de Cálculo de Tiempo**

El tiempo de vuelta se calcula usando esta fórmula:

```
TIEMPO FINAL = Tiempo_Base × Mod_Vehículo × Mod_Piloto × 
               Factor_Circuito × Mod_Configuración × 
               Mod_Clima × Variación_Aleatoria
```

### **Componentes de la Fórmula**

#### **1. Tiempo Base**
```
Tiempo_Base = Longitud_Circuito × 20 segundos/km
```
Factor aproximado de tiempo por kilómetro a velocidad promedio de F1.

#### **2. Modificador del Vehículo**
Combina velocidad máxima y aceleración:
```
Mod_Velocidad = 350 / Velocidad_Máxima_Vehículo
Mod_Aceleración = Aceleración_0-100 / 2.5
Mod_Vehículo = (Mod_Velocidad + Mod_Aceleración) / 2
```

#### **3. Modificador del Piloto**
Basado en habilidad y experiencia:
```
Mod_Habilidad = 75 / Habilidad_Piloto
Mod_Experiencia = 75 / Experiencia_Piloto
Mod_Piloto = (Mod_Habilidad × 0.6) + (Mod_Experiencia × 0.4)
```

#### **4. Factor del Circuito**
Considera exigencia del trazado:
```
Factor_Circuito = 1.0 + (Desgaste/100) + (Consumo/100)
```

#### **5. Modificador de Configuración**
Acumula efectos de los 4 parámetros configurables:
- Modo agresiva: -8% tiempo (más rápido)
- Carga baja: -3% tiempo (mejor en rectas)
- Presión baja: -2% tiempo (mejor agarre)
- Estrategia agresiva: -5% tiempo (más potencia)

#### **6. Modificador de Clima**
- **Seco**: x1.0 (sin cambios)
- **Lluvioso**: ÷0.85 (+17.6% tiempo)
- **Extremo**: ÷0.70 (+42.8% tiempo)

#### **7. Variación Aleatoria**
±5% para simular impredecibilidad (neumáticos, tráfico, errores, etc.)

### **Ejemplo de Cálculo**

**Escenario**: Max Verstappen en Monaco con clima seco

```
1. Tiempo Base = 3.337 km × 20 = 66.74 segundos
2. Mod_Vehículo (RB22, 358 km/h) = 0.98
3. Mod_Piloto (Habil: 98, Exp: 95) = 0.77
4. Factor_Circuito (Desgaste: 2.0, Consumo: 1.5) = 1.035
5. Mod_Configuración (Agresiva balanceada) = 0.92
6. Mod_Clima (Seco) = 1.0
7. Variación Aleatoria = 1.03 (ejemplo)

TIEMPO FINAL = 66.74 × 0.98 × 0.77 × 1.035 × 0.92 × 1.0 × 1.03
             = 44.95 segundos (0:44.950)
```

---

## 💡 Ejemplos de Uso

### **Ejemplo 1: Crear una Configuración**

```
1. Seleccionar opción "4. Configuración de simulación"
2. Elegir "1. Crear configuración"
3. Ingresar modelo: "RB22"
4. Seleccionar modo: "2. Agresiva"
5. Seleccionar aerodinámica: "1. Baja"
6. Seleccionar neumáticos: "2. Estándar"
7. Seleccionar combustible: "1. Agresiva"
8. Sistema muestra efectos esperados y guarda
```

**Resultado**: Configuración optimizada para velocidad máxima

### **Ejemplo 2: Ejecutar una Simulación**

```
1. Seleccionar opción "5. Iniciar simulación"
2. Ver lista de circuitos disponibles
3. Ingresar: "Circuit de Monaco"
4. Sistema genera clima: "Lluvioso"
5. Se calculan tiempos de 11 pilotos
6. Se muestra clasificación:
   P1 | Charles Leclerc | Ferrari | 1:18.456
   P2 | Max Verstappen | Red Bull | 1:18.892
   ...
7. Confirmar guardado en historial
```

### **Ejemplo 3: Consultar Historial**

```
1. Seleccionar opción "6. Historial de resultados"
2. Elegir "3. Consultar por circuito"
3. Ingresar: "Monaco"
4. Sistema muestra todas las sesiones en Monaco:
   Sesión #1 | Circuit de Monaco | Seco | Pole: M. Verstappen
   Sesión #3 | Circuit de Monaco | Lluvioso | Pole: C. Leclerc
   ...
```

---

## 🏗️ Arquitectura del Sistema

### **Patrón de Diseño**

El proyecto utiliza una arquitectura **MVC adaptada** con separación clara de responsabilidades:

```
┌─────────────────────────────────────────────────┐
│                    VISTA                        │
│  (Main.java - Menús con JOptionPane)           │
└─────────────────────┬───────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────┐
│                 CONTROLADOR                     │
│  (Gestores - Lógica de negocio)                │
│  • GestorPilotos                               │
│  • GestorEquipos                               │
│  • GestorVehiculos                             │
│  • GestorCircuitos                             │
│  • GestorConfiguraciones                       │
│  • GestorHistorial                             │
│  • MotorSimulacion                             │
└─────────────────────┬───────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────┐
│                   MODELO                        │
│  (Entidades de dominio)                        │
│  • Piloto, Equipo, Vehiculo, Circuito         │
│  • ConfiguracionVehiculo                       │
│  • ResultadoClasificacion                      │
│  • SesionClasificacion                         │
└─────────────────────────────────────────────────┘
```

### **Relaciones Entre Clases**

- **Piloto** pertenece a un **Equipo**
- **Vehiculo** pertenece a un **Equipo** y puede tener **Pilotos** asignados
- **ConfiguracionVehiculo** se asocia a un **Vehiculo** (por modelo)
- **ResultadoClasificacion** vincula **Piloto**, **Vehiculo** y **Circuito**
- **SesionClasificacion** contiene múltiples **ResultadoClasificacion**
- **MotorSimulacion** orquesta todos los gestores para generar resultados

---

## 👥 Funcionalidades por Rol

### **Administrador**

Acceso completo a operaciones CRUD:

✅ **Equipos**: Registrar, editar, eliminar equipos
✅ **Pilotos**: Registrar, editar, eliminar pilotos y asignarlos a equipos
✅ **Vehículos**: Agregar, editar, eliminar vehículos y asignar pilotos
✅ **Circuitos**: Registrar, editar, eliminar circuitos y gestionar ganadores históricos

### **Usuario**

Acceso a consultas y simulación:

✅ **Consultas**: Ver información de equipos, pilotos, vehículos, circuitos
✅ **Comparaciones**: Comparar especificaciones de vehículos
✅ **Configuración**: Crear y gestionar configuraciones de vehículos
✅ **Simulación**: Ejecutar simulaciones de clasificación
✅ **Historial**: Consultar, comparar y analizar sesiones anteriores

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Build Tool**: Maven 3.6+
- **Interfaz Gráfica**: javax.swing.JOptionPane
- **Persistencia**: HashMap (temporal, en memoria)
- **Estructura de Datos**: 
  - `HashMap` para almacenamiento O(1)
  - `ArrayList` para listas ordenadas
  - `TreeMap` para ordenamiento automático
- **Generación Aleatoria**: java.util.Random
- **Fechas**: java.time (LocalDateTime)

---

## ⚠️ Limitaciones Conocidas

1. **Persistencia Temporal**: Los datos se pierden al cerrar el programa. No hay base de datos persistente.

2. **Interfaz Básica**: Uso de JOptionPane limita la experiencia visual (no es GUI moderna).

3. **Sin Autenticación Real**: La separación Usuario/Administrador es conceptual, sin login real.

4. **Simulación Simplificada**: Solo calcula clasificación, no carrera completa con estrategias dinámicas.

5. **Capacidad Limitada**: Optimizado para ~20 pilotos máximo (rendimiento de HashMap).

---

## 🚀 Posibles Mejoras Futuras

### **Corto Plazo**
- ✨ Agregar persistencia con archivos JSON o serialización
- ✨ Implementar sistema de login con roles reales
- ✨ Mejorar interfaz con JavaFX o Swing completo
- ✨ Agregar más circuitos históricos

### **Mediano Plazo**
- 🔥 Simulación de carrera completa (no solo clasificación)
- 🔥 Sistema de estrategias de pit-stops
- 🔥 Simulación de campeonato completo con puntos
- 🔥 Gráficos de rendimiento y estadísticas avanzadas

### **Largo Plazo**
- 🌟 Base de datos relacional (MySQL/PostgreSQL)
- 🌟 API REST para acceso remoto
- 🌟 Interfaz web con Spring Boot
- 🌟 Machine Learning para predicciones más realistas
- 🌟 Multiplayer - carreras con otros usuarios

---

## 👨‍💻 Autores

**Proyecto Académico** - Simulación de Fórmula 1  
Desarrollado como proyecto de programación orientada a objetos  
**Año**: 2026

---

**¡Disfruta simulando carreras de Fórmula 1! 🏎️💨**
