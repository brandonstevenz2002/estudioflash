package com.example.estudioflash.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.foundation.BorderStroke

// Colores
private val PrimaryBlue = Color(0xFF4169E1)
private val LightBlue = Color(0xFF87CEEB)
private val BackgroundBlue = Color(0xFF5B8FFF)
private val CardWhite = Color(0xFFFFFFFF)
private val StudyColor = Color(0xFFFFD700)
private val BreakColor = Color(0xFF40E0D0)

@Composable
fun PomodoroScreen(
    materia: String,
    onBack: () -> Unit
) {
    val studyTime = 25 * 60
    val breakTime = 5 * 60

    var isRunning by remember { mutableStateOf(false) }
    var isStudyPhase by remember { mutableStateOf(true) }
    var timeLeft by remember { mutableStateOf(studyTime) }

    // Lógica del temporizador
    LaunchedEffect(isRunning, isStudyPhase) {
        while (isRunning) {
            delay(1000L)
            timeLeft--

            if (timeLeft <= 0) {
                isStudyPhase = !isStudyPhase
                timeLeft = if (isStudyPhase) studyTime else breakTime
            }
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    val totalTime = if (isStudyPhase) studyTime else breakTime
    val progress = 1f - (timeLeft.toFloat() / totalTime.toFloat())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
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
                    text = "Pomodoro",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        // Reloj circular
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(240.dp)
        ) {
            // Reloj animado
            AnimatedClock(isRunning = isRunning)

            // Círculo de progreso
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(240.dp),
                color = if (isStudyPhase) StudyColor else BreakColor,
                strokeWidth = 12.dp,
                trackColor = Color.White.copy(alpha = 0.3f)
            )
        }

        Spacer(Modifier.height(40.dp))

        // Fase de estudio
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardWhite),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isStudyPhase) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Estudiando",
                        tint = StudyColor,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                }
                Text(
                    text = if (isStudyPhase) "25min de estudio" else "5min de descanso",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Barra de progreso
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardWhite),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Barra de progreso",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                }

                Spacer(Modifier.height(12.dp))

                LinearProgressIndicator(
                    progress = {progress},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = PrimaryBlue,
                    trackColor = Color.LightGray.copy(alpha = 0.3f)
                )
            }
        }

        Spacer(Modifier.weight(1f))

        // Controles
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón Iniciar/Pausar
            Button(
                onClick = { isRunning = !isRunning },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) Color(0xFFFF6B6B) else Color(0xFF51CF66)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (isRunning) "Pausar" else "Iniciar",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Botón Reiniciar
            OutlinedButton(
                onClick = {
                    timeLeft = studyTime
                    isStudyPhase = true
                    isRunning = false
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Reiniciar",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Botón Volver
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

@Composable
fun AnimatedClock(isRunning: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "clock")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isRunning) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = Modifier.size(180.dp),
        contentAlignment = Alignment.Center
    ) {
        // Cuerpo del reloj
        Canvas(modifier = Modifier.size(180.dp)) {
            // Círculo exterior (azul claro)
            drawCircle(
                color = LightBlue,
                radius = size.minDimension / 2,
                center = center
            )

            // Círculo interior (azul)
            drawCircle(
                color = PrimaryBlue,
                radius = size.minDimension / 2.5f,
                center = center
            )

            // Campanas del reloj
            drawArc(
                color = Color.DarkGray,
                startAngle = -45f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(-size.width * 0.15f, -size.height * 0.25f),
                size = Size(size.width * 0.3f, size.height * 0.3f),
                style = Stroke(width = 12f)
            )

            drawArc(
                color = Color.DarkGray,
                startAngle = 135f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(size.width * 0.85f, -size.height * 0.25f),
                size = Size(size.width * 0.3f, size.height * 0.3f),
                style = Stroke(width = 12f)
            )
        }

        // Manecilla del reloj
        Canvas(
            modifier = Modifier
                .size(120.dp)
                .rotate(rotation)
        ) {
            drawLine(
                color = Color.White,
                start = center,
                end = Offset(center.x + size.width / 3, center.y - size.height / 6),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
    }
}