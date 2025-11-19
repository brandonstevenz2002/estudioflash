# 📘 EstudioFlash

<div align="center">

![Android](https://img.shields.io/badge/Android-Studio-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?logo=firebase&logoColor=black)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?logo=jetpack-compose&logoColor=white)

**Aplicación móvil educativa para optimizar el estudio y mejorar el rendimiento académico**

[Características](#-características-principales) • [Instalación](#-instalación) • [Tecnologías](#-stack-tecnológico) • [Equipo](#-equipo-de-desarrollo)

</div>

---

## 📖 Descripción

**EstudioFlash** es una aplicación Android nativa desarrollada con Jetpack Compose que combina técnicas de estudio probadas con tecnología moderna. Diseñada para estudiantes que buscan organizar su tiempo, practicar con autoevaluaciones personalizadas y gestionar eficientemente su aprendizaje académico.

### 🎯 Objetivo

Proporcionar a los estudiantes herramientas integradas que faciliten el estudio efectivo mediante la técnica Pomodoro, autoevaluaciones dinámicas y organización visual del conocimiento.

---

## ✨ Características principales

### 🔐 Sistema de autenticación
- Registro e inicio de sesión con Firebase Authentication
- Validación de campos en tiempo real
- Gestión segura de sesiones de usuario
- Recuperación de contraseña

### 📚 Panel de estudio (StudyScreen)
Interfaz principal con acceso rápido a:
- **Técnica Pomodoro** para gestión del tiempo
- **Mapas mentales** para organización visual (próximamente)
- **Autoevaluaciones** adaptadas por materia
- Selector de materias con contenido personalizado

### ⏱️ Temporizador Pomodoro
Implementación completa de la técnica Pomodoro:
- Sesiones de estudio de 25 minutos
- Descansos cortos de 5 minutos
- Indicador visual de progreso circular
- Estados diferenciados (estudio/descanso)
- Controles de inicio, pausa y reinicio

### 📝 Sistema de autoevaluación
Evaluaciones interactivas con:
- Preguntas categorizadas por materia
- Interfaz intuitiva con tarjetas deslizables
- Selección múltiple con validación instantánea
- Sistema de puntuación y retroalimentación
- Historial de resultados

### 🧠 Mapas mentales
*Funcionalidad en desarrollo activo*

---

## 🛠️ Stack tecnológico

| Tecnología | Propósito |
|------------|-----------|
| **Kotlin** | Lenguaje principal de desarrollo |
| **Jetpack Compose** | Framework UI declarativo moderno |
| **Material Design 3** | Sistema de diseño y componentes |
| **Firebase Authentication** | Gestión de usuarios y autenticación |
| **Coroutines** | Programación asíncrona |
| **ViewModel** | Arquitectura MVVM |
| **Android Studio** | IDE de desarrollo |

---

## 👥 Equipo de desarrollo

| Integrante | ID | Responsabilidades |
|-----------|------|-------------------|
| **Cristhyan Vera** | 407981 | Desarrollo Android • Diseño UI/UX • Integración Firebase |
| **Brandon Vargas** | 406089 | Desarrollo Android • Diseño UI/UX • Integración Firebase |

---

## 🚀 Instalación

### Prerrequisitos

- Android Studio Hedgehog (2023.1.1) o superior
- JDK 17 o superior
- Dispositivo Android con API 24+ o emulador configurado
- Cuenta de Firebase (para autenticación)

### Pasos de instalación

#### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-repositorio/EstudioFlash.git
cd EstudioFlash
```

#### 2️⃣ Abrir en Android Studio

1. Abrir Android Studio
2. Seleccionar **File → Open**
3. Navegar y seleccionar la carpeta del proyecto
4. Esperar a que Gradle sincronice las dependencias

#### 3️⃣ Configurar Firebase

**⚠️ Paso obligatorio para el funcionamiento de la app**

1. Acceder a [Firebase Console](https://console.firebase.google.com/)
2. Crear un nuevo proyecto o seleccionar uno existente
3. Agregar una aplicación Android:
   - Hacer clic en el ícono de Android
   - Registrar el nombre del paquete: `com.tuapp.estudioflash`
   - Descargar el archivo `google-services.json`
4. Colocar el archivo en la ruta:
   ```
   app/google-services.json
   ```
5. En Firebase Console, habilitar autenticación:
   - Ir a **Authentication → Sign-in method**
   - Activar **Email/Password**

#### 4️⃣ Ejecutar la aplicación

**Configuración recomendada del emulador:**
- Dispositivo: Pixel 5
- API Level: 34 (Android 14)
- ABI: x86_64

**Pasos para ejecutar:**

1. Abrir **Device Manager** en Android Studio
2. Crear o seleccionar un dispositivo virtual
3. Presionar el botón **Run ▶** o usar `Shift + F10`
4. Esperar a que la app se compile e instale

---

## 📂 Estructura del proyecto

```
EstudioFlash/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/estudioflash/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/      # Pantallas de la app
│   │   │   │   │   ├── components/   # Componentes reutilizables
│   │   │   │   │   └── theme/        # Tema y estilos
│   │   │   │   ├── data/             # Modelos y repositorios
│   │   │   │   └── viewmodel/        # ViewModels
│   │   │   ├── res/                   # Recursos (strings, colors, etc.)
│   │   │   └── AndroidManifest.xml
│   │   └── google-services.json       # Configuración Firebase
│   └── build.gradle.kts
└── build.gradle.kts
```

---

## 🎨 Capturas de pantalla

*Próximamente*

---

## 🔄 Estado del proyecto

| Funcionalidad | Estado |
|--------------|--------|
| Autenticación Firebase | ✅ Completado |
| Temporizador Pomodoro | ✅ Completado |
| Autoevaluaciones | ✅ Completado |
| Selector de materias | ✅ Completado |
| Mapas mentales | 🚧 En desarrollo |
| Estadísticas de estudio | 📋 Planificado |
| Modo oscuro | 📋 Planificado |

---

## 🤝 Contribuir

Las contribuciones son bienvenidas. Para cambios importantes:

1. Haz fork del proyecto
2. Crea una rama para tu funcionalidad (`git checkout -b feature/NuevaFuncionalidad`)
3. Realiza commit de tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/NuevaFuncionalidad`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la [Licencia MIT](LICENSE).

---

## 📧 Contacto

Para preguntas, sugerencias o reportes de bugs, contactar a:

- Cristhyan Vera - [cristhian.vera01@gmail.com]
- Brandon Vargas - [brandonstevenvm@gmail.com]

---

<div align="center">

**Desarrollado con ❤️ por el equipo EstudioFlash**

⭐ Si este proyecto te resulta útil, considera darle una estrella en GitHub

</div>
