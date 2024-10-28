package com.tecsup.lab13

import androidx.compose.animation.*
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CombinedAnimationsScreen() {
    // Estado para el cuadro (cambio de tamaño y color)
    var isExpanded by remember { mutableStateOf(false) }
    val boxSize by animateDpAsState(
        targetValue = if (isExpanded) 150.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val boxColor by animateColorAsState(
        targetValue = if (isExpanded) Color.Green else Color.Blue,
        animationSpec = tween(durationMillis = 500)
    )

    // Estado para la visibilidad y posición del botón
    var isButtonVisible by remember { mutableStateOf(true) }

    // Estado para el modo de tema (claro/oscuro)
    var isDarkMode by remember { mutableStateOf(false) }

    // Pantalla con elementos y animaciones combinadas
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.DarkGray else Color.White)
    ) {
        // Cuadro que cambia de tamaño y color
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(boxColor)
                .padding(16.dp)
        ) {
            Text(
                text = if (isDarkMode) "Modo Oscuro" else "Modo Claro",
                color = if (isDarkMode) Color.White else Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón para cambiar tamaño y color del cuadro
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(text = "Cambiar Tamaño y Color")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón de desplazamiento y desaparición
        AnimatedVisibility(
            visible = isButtonVisible,
            enter = slideInVertically(initialOffsetY = { -40 }) + fadeIn(animationSpec = tween(600)),
            exit = slideOutVertically(targetOffsetY = { 40 }) + fadeOut(animationSpec = tween(600))
        ) {
            Button(onClick = { isButtonVisible = !isButtonVisible }) {
                Text(text = "Desaparecer")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón para alternar entre modo claro y oscuro
        AnimatedContent(
            targetState = isDarkMode,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
            }
        ) { darkMode ->
            Button(
                onClick = { isDarkMode = !isDarkMode },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = if (darkMode) "Cambiar a Modo Claro" else "Cambiar a Modo Oscuro")
            }
        }
    }
}
