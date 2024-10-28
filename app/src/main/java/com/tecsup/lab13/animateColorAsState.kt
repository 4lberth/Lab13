package com.tecsup.lab13

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorChangeAnimation() {
    // Variable para controlar el estado de color
    var isBlue by remember { mutableStateOf(true) }

    // Animación de color utilizando animateColorAsState
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 1000) // Puedes cambiar a `spring()` para experimentar
    )

    // Column para mostrar el botón y el cuadro
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Botón para alternar el color
        Button(onClick = { isBlue = !isBlue }) {
            Text(text = "Cambiar Color")
        }

        // Cuadro de color animado
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(backgroundColor)
        )
    }
}
