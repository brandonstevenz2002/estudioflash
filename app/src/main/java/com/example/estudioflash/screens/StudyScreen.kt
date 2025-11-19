package com.example.estudioflash.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



// Colores personalizados
private val PrimaryBlue = Color(0xFF5B7FFF)
private val LightBlue = Color(0xFF7B9FFF)
private val BackgroundGray = Color(0xFFF5F5F5)
private val CardWhite = Color(0xFFFFFFFF)

@Composable
fun StudyScreen(
    onSelectMateria: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    var materiaSeleccionada by remember { mutableStateOf<String?>(null) }
    var metodoSeleccionado by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(24.dp)
    ) {
        // Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = PrimaryBlue),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hola\nEstudiante",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 36.sp
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Pregunta
        Text(
            text = "¿Qué deseas estudiar hoy?",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(16.dp))

        // Materias
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MateriaCard(
                titulo = "Matematicas",
                icon = Icons.Default.Calculate,
                        isSelected = materiaSeleccionada == "Matematicas",
                onClick = { materiaSeleccionada = "Matematicas" },
                modifier = Modifier.weight(1f)
            )
            MateriaCard(
                titulo = "Historia",
                icon = Icons.Default.Book,
                isSelected = materiaSeleccionada == "Historia",
                onClick = { materiaSeleccionada = "Historia" },
                modifier = Modifier.weight(1f)
            )
            MateriaCard(
                titulo = "Ingles",
                icon = Icons.Default.Language,
                isSelected = materiaSeleccionada == "Ingles",
                onClick = { materiaSeleccionada = "Ingles" },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(24.dp))

        // Botón Seleccionar
        Button(
            onClick = {
                materiaSeleccionada?.let { onSelectMateria(it) }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = RoundedCornerShape(12.dp),
            enabled = materiaSeleccionada != null
        ) {
            Text(
                text = "Seleccionar",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(Modifier.height(32.dp))

        // Métodos de estudio
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardWhite),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Metodos De Estudio",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetodoChip(
                        texto = "Pomodoro",
                        isSelected = metodoSeleccionado == "Pomodoro",
                        onClick = { metodoSeleccionado = "Pomodoro" }
                    )
                    MetodoChip(
                        texto = "Mapas mentales",
                        isSelected = metodoSeleccionado == "Mapas",
                        onClick = { metodoSeleccionado = "Mapas" }
                    )
                    MetodoChip(
                        texto = "Autoevaluación",
                        isSelected = metodoSeleccionado == "Autoevaluacion",
                        onClick = { metodoSeleccionado = "Autoevaluacion" }
                    )
                }

                Spacer(Modifier.height(20.dp))

                // Indicadores de selección
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IndicadorSeleccion(isSelected = metodoSeleccionado == "Pomodoro")
                    Spacer(Modifier.width(12.dp))
                    IndicadorSeleccion(isSelected = metodoSeleccionado == "Mapas")
                    Spacer(Modifier.width(12.dp))
                    IndicadorSeleccion(isSelected = metodoSeleccionado == "Autoevaluacion")
                }

                Spacer(Modifier.height(20.dp))

                // Botón Seleccionar Método
                Button(
                    onClick = {
                        when (metodoSeleccionado) {
                            "Pomodoro" -> {
                                materiaSeleccionada?.let { onSelectMateria(it) }
                                onNavigate("pomodoro")
                            }
                            "Autoevaluacion" -> {
                                materiaSeleccionada?.let { onSelectMateria(it) }
                                onNavigate("autoevaluacion")
                            }
                            "Mapas" -> onNavigate("mapa")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    shape = RoundedCornerShape(12.dp),
                    enabled = metodoSeleccionado != null
                ) {
                    Text(
                        text = "Seleccionar",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MateriaCard(
    titulo: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(0.85f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) PrimaryBlue else CardWhite
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (isSelected) BorderStroke(2.dp, LightBlue) else null
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = titulo,
                modifier = Modifier.size(48.dp),
                tint = if (isSelected) Color.White else PrimaryBlue
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun MetodoChip(
    texto: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) PrimaryBlue else Color.White,
        border = BorderStroke(1.5.dp, if (isSelected) PrimaryBlue else Color.LightGray),
        modifier = Modifier.height(40.dp)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = texto,
                fontSize = 13.sp,
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun IndicadorSeleccion(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(
                color = if (isSelected) PrimaryBlue else Color.LightGray,
                shape = RoundedCornerShape(6.dp)
            )
    )
}