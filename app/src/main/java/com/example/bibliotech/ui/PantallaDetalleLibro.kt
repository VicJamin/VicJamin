package com.example.bibliotech.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.model.Libro

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

//IMPORTS AGREGADOS PARA MOSTRAR EL MENSAJE DE NOTIFICACIÓN
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
@Composable
fun PantallaDetalleLibro(
    libro: Libro,
    onRegresar: () -> Unit,
    onEditar:(Int) -> Unit,
    onEliminar:(Libro) -> Unit,




//MODIFIQUE AQUÍ LA LINEA DE ABAJO PARA QUE LA VENTANA EMERGENTE APAREZCA EN PANTALLA DETALLE LIBRO
    navController: NavController,




    ) {
    val snackbarHostState = remember { SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val mensaje =
        backStackEntry
            ?.savedStateHandle
            ?.get<String>("mensaje")

    LaunchedEffect(mensaje) {

        if (mensaje != null) {
            snackbarHostState.showSnackbar(mensaje)
            backStackEntry
                ?.savedStateHandle
                ?.remove<String>("mensaje")
        }




    }
    var mostrarDialogo by remember { mutableStateOf(false) }

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold(containerColor = Color.Black,
        //Añadiremos el snackbarHost
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("Detalle de Libro",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }){paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(20.dp).padding(paddingValues)){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = "Libro",
                modifier = Modifier.height(30.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = libro.titulo,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Autor: ${libro.autor}",color = Color.White)
            Text("Categoria: ${libro.categoria}",color = Color.White)
            Text("Año: ${libro.anio}",color = Color.White)
            Text("Disponible: ${libro.disponible}",color = Color.White)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Button(
                    onClick = {
                        onEditar(libro.id)
                    },
                    modifier = Modifier.weight(1f))
                {
                    Text("Editar")
                }
                Button(
                    onClick = {
                        //  onEliminar(libro.id)
                        mostrarDialogo = true
                    },
                    modifier = Modifier.weight(1f))
                {
                    Text("Eliminar")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onRegresar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Regresar")
            }
            //Construiremos la ventana emergente cuando presionemos el boton eliminar
            if(mostrarDialogo){
                AlertDialog(
                    onDismissRequest = {mostrarDialogo = false},
                    title = {
                        Text("Confirmacion")
                    },
                    text = {
                        Text("¿Estas seguro de eliminar \"${libro.titulo}\"?")
                    },
                    confirmButton = {
                        Button( onClick = {
                            mostrarDialogo = false
                            onEliminar(libro)
                        }) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            mostrarDialogo = false
                        }) {
                            Text("Cancelar")
                        }
                    })
            }
        }
    }
}