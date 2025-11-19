package com.example.estudioflash.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores
private val PrimaryBlue = Color(0xFF5B7FFF)
private val BackgroundBlue = Color(0xFF6B8FFF)
private val CardWhite = Color(0xFFFFFFFF)
private val CorrectGreen = Color(0xFF4CAF50)
private val IncorrectRed = Color(0xFFFF5252)

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val correcta: Int
)

@Composable
fun AutoevaluacionScreen(
    materia: String,
    onBack: () -> Unit
) {
    val preguntas = remember { obtenerPreguntas(materia) }
    var seleccion by remember { mutableStateOf(MutableList(preguntas.size) { -1 }) }
    var mostrarResultado by remember { mutableStateOf(false) }
    var puntaje by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue)
    ) {
        // Header fijo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundBlue)
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Autoevaluación",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        // Contenido scrolleable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            // Preguntas
            preguntas.forEachIndexed { index, pregunta ->
                PreguntaCard(
                    numero = index + 1,
                    pregunta = pregunta,
                    seleccionada = seleccion[index],
                    mostrarResultado = mostrarResultado,
                    onSeleccion = { opcionIndex ->
                        seleccion = seleccion.toMutableList().also {
                            it[index] = opcionIndex
                        }
                    }
                )
                Spacer(Modifier.height(16.dp))
            }

            // Resultado
            if (mostrarResultado) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (puntaje == preguntas.size) CorrectGreen else PrimaryBlue
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Resultado",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Resultado",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "$puntaje / ${preguntas.size}",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "${(puntaje * 100 / preguntas.size)}% Correcto",
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            Spacer(Modifier.height(100.dp)) // Espacio para botones fijos
        }

        // Botones fijos al fondo
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = BackgroundBlue,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                if (!mostrarResultado) {
                    Button(
                        onClick = {
                            puntaje = calcularPuntaje(preguntas, seleccion)
                            mostrarResultado = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF51CF66)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        enabled = seleccion.all { it != -1 }
                    ) {
                        Text(
                            text = "Enviar respuestas",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            seleccion = MutableList(preguntas.size) { -1 }
                            mostrarResultado = false
                            puntaje = 0
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Reintentar",
                            fontSize = 16.sp,
                            color = PrimaryBlue,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                TextButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Volver",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun PreguntaCard(
    numero: Int,
    pregunta: Pregunta,
    seleccionada: Int,
    mostrarResultado: Boolean,
    onSeleccion: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Pregunta
            Text(
                text = pregunta.texto,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                lineHeight = 24.sp
            )

            Spacer(Modifier.height(16.dp))

            // Opciones
            pregunta.opciones.forEachIndexed { opcionIndex, opcion ->
                OpcionItem(
                    letra = ('a' + opcionIndex).toString(),
                    texto = opcion,
                    isSelected = seleccionada == opcionIndex,
                    isCorrect = opcionIndex == pregunta.correcta,
                    showResult = mostrarResultado,
                    onClick = { if (!mostrarResultado) onSeleccion(opcionIndex) }
                )
                if (opcionIndex < pregunta.opciones.size - 1) {
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun OpcionItem(
    letra: String,
    texto: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    showResult: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        showResult && isCorrect -> CorrectGreen.copy(alpha = 0.2f)
        showResult && isSelected && !isCorrect -> IncorrectRed.copy(alpha = 0.2f)
        isSelected -> PrimaryBlue.copy(alpha = 0.1f)
        else -> Color.Transparent
    }

    val borderColor = when {
        showResult && isCorrect -> CorrectGreen
        showResult && isSelected && !isCorrect -> IncorrectRed
        isSelected -> PrimaryBlue
        else -> Color.LightGray
    }

    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected || (showResult && isCorrect)) 2.dp else 1.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo con letra
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = if (isSelected) PrimaryBlue else Color.LightGray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = letra,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else Color.Gray
                )
            }

            Spacer(Modifier.width(16.dp))

            // Texto de la opción
            Text(
                text = texto,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Icono de correcto/incorrecto
            if (showResult && (isCorrect || (isSelected && !isCorrect))) {
                Icon(
                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.CheckCircle,
                    contentDescription = if (isCorrect) "Correcto" else "Incorrecto",
                    tint = if (isCorrect) CorrectGreen else IncorrectRed,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// Funciones auxiliares
fun obtenerPreguntas(materia: String): List<Pregunta> {
    return when (materia.lowercase()) {
        "ingles" -> listOf(
            Pregunta(
                "What is the past tense of the verb \"go\"?",
                listOf("goed", "went", "go"),
                correcta = 1
            ),
            Pregunta(
                "Which word is a synonym of \"happy\"?",
                listOf("sad", "angry", "joyful"),
                correcta = 2
            ),
            Pregunta(
                "Choose the correct sentence:",
                listOf(
                    "She don't like pizza",
                    "She doesn't likes pizza",
                    "She doesn't like pizza"
                ),
                correcta = 2
            )
        )
        "matematicas" -> listOf(
            Pregunta("2 + 2 = ?", listOf("3", "4", "5"), 1),
            Pregunta("5 × 3 = ?", listOf("15", "8", "10"), 0),
            Pregunta("Raíz cuadrada de 16", listOf("3", "4", "5"), 1)
        )
        "historia" -> listOf(
            Pregunta("¿En qué año descubrió Colón América?", listOf("1492", "1501", "1480"), 0),
            Pregunta("¿Quién fue el primer emperador romano?", listOf("Julio César", "Augusto", "Nerón"), 1),
            Pregunta("¿Dónde se originó la democracia?", listOf("Roma", "Atenas", "Egipto"), 1)
        )
        else -> listOf(
            Pregunta("Pregunta de ejemplo 1", listOf("A", "B", "C"), 1),
            Pregunta("Pregunta de ejemplo 2", listOf("A", "B", "C"), 0)
        )
    }
}

fun calcularPuntaje(preguntas: List<Pregunta>, seleccion: List<Int>): Int {
    var puntaje = 0
    preguntas.forEachIndexed { index, pregunta ->
        if (seleccion[index] == pregunta.correcta) puntaje++
    }
    return puntaje
}