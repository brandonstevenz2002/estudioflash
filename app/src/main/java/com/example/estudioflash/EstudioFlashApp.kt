package com.example.estudioflash

import androidx.compose.runtime.*
import androidx.compose.material3.*
import com.example.estudioflash.screens.*

@Composable
fun EstudioFlashApp() {

    var currentScreen by remember { mutableStateOf("welcome") }

    // Materia seleccionada para Pomodoro / Autoevaluación
    var materiaSeleccionada by remember { mutableStateOf("Inglés") }

    when (currentScreen) {

        // Pantallas iniciales
        "welcome" ->
            WelcomeScreen { destino -> currentScreen = destino }

        "login" ->
            LoginScreen { destino -> currentScreen = destino }

        "register" ->
            RegisterScreen { destino -> currentScreen = destino }

        // Pantalla principal después de login
        "study" ->
            StudyScreen(
                onSelectMateria = { materia ->
                    materiaSeleccionada = materia
                },
                onNavigate = { destino ->
                    currentScreen = destino
                }
            )

        // Pomodoro
        "pomodoro" ->
            PomodoroScreen(
                materia = materiaSeleccionada,
                onBack = { currentScreen = "study" }
            )

        // Autoevaluación
        "autoevaluacion" ->
            AutoevaluacionScreen(
                materia = materiaSeleccionada,
                onBack = { currentScreen = "study" }
            )

        // Mapa conceptual - En desarrollo
        "mapa" ->
            EnDesarrolloScreen {
                currentScreen = "study"
            }
    }
}