package com.tecsup.lab13

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun SizeAndPositionAnimation() {
    // Estado para el tamaño y posición del cuadro
    var expanded by remember { mutableStateOf(false) }

    // Animaciones de tamaño y posición
    val size by animateDpAsState(targetValue = if (expanded) 150.dp else 100.dp, animationSpec = tween(500))
    val offsetX by animateDpAsState(targetValue = if (expanded) 50.dp else 0.dp, animationSpec = tween(500))
    val offsetY by animateDpAsState(targetValue = if (expanded) 50.dp else 0.dp, animationSpec = tween(500))

    // Column para el botón y el cuadro animado
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Botón para alternar tamaño y posición
        Button(onClick = { expanded = !expanded }) {
            Text(text = "Mover y Cambiar Tamaño")
        }

        // Cuadro con animaciones de tamaño y posición
        Box(
            modifier = Modifier
                .offset(x = offsetX, y = offsetY) // Cambia el orden para ver el efecto
                .size(size)
                .background(Color.Red)
        )
    }
}
