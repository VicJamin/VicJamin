package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onEstudiantes: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "BiblioTech",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "sistema de biblioteca escolar",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
         BotonMenu(
             texto="catalogo de libros",
             onClick = onCatalogo)
        Spacer(modifier = Modifier.height(16.dp))
        BotonMenu(
            texto="registrar prestamo",
            onClick = onPrestamo)

        Spacer(modifier = Modifier.height(16.dp))
        BotonMenu(
            texto="libros prestados",
            onClick = onPrestados)


        Spacer(modifier = Modifier.height(16.dp))


        BotonMenu(
            texto="Estudiantes",
            onClick = onEstudiantes)

    }

}


