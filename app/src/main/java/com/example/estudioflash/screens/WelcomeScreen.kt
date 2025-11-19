package com.example.estudioflash.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryBlue = Color(0xFF5B7FFF)
private val White = Color.White

@Composable
fun WelcomeScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("📚✏️", fontSize = 60.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Text("Cargando...", color = Color.White, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = { onNavigate("login") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = White)
        ) {
            Text("Login", color = PrimaryBlue)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { onNavigate("register") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = White)
        ) {
            Text("Register", color = PrimaryBlue)
        }
    }
}