package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.ui.componentes.BotonMenu

@Composable
fun PantallaPrincipal(
    onCatalogo: () -> Unit,
    onPrestamo: () -> Unit,
    onPrestados: () -> Unit,
    onEstudiantes: () -> Unit,
    mensaje:String?,
    onMensajeMostrado: () -> Unit
) {
    val snackbarHostState =
        remember {
            SnackbarHostState()
        }
    // ---------------------------------------------------------
    // MOSTRAR MENSAJE
    // ---------------------------------------------------------


    LaunchedEffect(mensaje) {


        if (mensaje != null) {


            snackbarHostState.showSnackbar(
                message = mensaje
            )


            // Indicamos que el mensaje ya fue mostrado.
            onMensajeMostrado()
        }
    }

    Scaffold(


        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }


    ) { paddingValues ->


        Column(modifier = Modifier.padding(16.dp).padding(paddingValues)) {
            Text(
                text = "BiblioTech",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Sistema de biblioteca escolar",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Consulta libros y administra los préstamos de la biblioteca escolar."
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            BotonMenu(

                texto = "Catalogo de libros",
                onClick = onCatalogo

            )
            Spacer(modifier = Modifier.height(16.dp))
            BotonMenu(
                texto = "Registrar prestamo",
                onClick = onPrestamo
            )

            Spacer(modifier = Modifier.height(16.dp))
            BotonMenu(

                texto = "Libros prestados",
                onClick = onPrestados
            )


            Spacer(modifier = Modifier.height(16.dp))


            BotonMenu(
                texto = "Estudiantes",
                onClick = onEstudiantes
            )

        }

    }

}
