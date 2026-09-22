package com.example.bibliotech.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bibliotech.model.Libro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarLibro(
    libro: Libro,
    onGuardar: (Libro) -> Unit,
    onCancelar: () -> Unit
) {

    var titulo by remember { mutableStateOf(libro.titulo) }
    var autor by remember { mutableStateOf(libro.autor) }
    var categoria by remember { mutableStateOf(libro.categoria) }
    var anio by remember { mutableStateOf(libro.anio.toString()) }


    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text("Editar Libro", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues).padding(16.dp)
        ) {
            Text("Editar Libro", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = {titulo = it},
                label = {Text ("Titulo") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = autor,
                onValueChange = {autor = it},
                label = {Text ("Autor") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = categoria,
                onValueChange = {categoria = it},
                label = {Text ("Categoría") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = anio,
                onValueChange = {anio = it},
                label = {Text ("Año") },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val libroEditado = libro.copy(
                        titulo = titulo,
                        autor = autor,
                        categoria = categoria,
                        anio = anio.toIntOrNull() ?: libro.anio,

                    )
                    onGuardar(libroEditado)
                },
                modifier = Modifier.fillMaxWidth()
            ){Text("Guardar Cambios")}
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = onCancelar,
                modifier = Modifier.fillMaxWidth()
            ){Text("Cancelar")}
        }
    }
}