package com.tecsup.lab13

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContent() {
    // Definición de los estados como Strings
    var currentState by remember { mutableStateOf("LOADING") }

    // Layout principal con el botón y el contenido animado
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Botón para cambiar de estado
        Button(onClick = {
            currentState = when (currentState) {
                "LOADING" -> "CONTENT"
                "CONTENT" -> "ERROR"
                "ERROR" -> "LOADING"
                else -> "LOADING"
            }
        }) {
            Text(text = "Cambiar Estado")
        }

        // Contenido animado que cambia de acuerdo al estado
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(600)) with fadeOut(animationSpec = tween(600))
            }
        ) { state ->
            when (state) {
                "LOADING" -> Text(text = "Cargando...", Modifier.align(Alignment.CenterHorizontally))
                "CONTENT" -> Text(text = "Contenido Cargado", Modifier.align(Alignment.CenterHorizontally))
                "ERROR" -> Text(text = "Error al Cargar", Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

