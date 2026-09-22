package com.example.bibliotech.ui

import android.app.Application
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.data.librosPrueba

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bibliotech.viewmodel.LibroViewModel
import androidx.compose.runtime.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion(
    navController: NavHostController
) {
    var mensaje by remember { mutableStateOf<String?>(null) }

    NavHost(navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            PantallaPrincipal(
                onCatalogo = {
                    navController.navigate("catalogo")
                },
                onPrestamo = {
                    navController.navigate("prestamo")
                },
                onPrestados = {
                    navController.navigate("prestados")
                },
                onEstudiantes = {
                    navController.navigate("estudiantes")
                }
            )
        }

        composable("catalogo") {
            PantallaCatalogo(
                onRegresar = {
                    navController.popBackStack()
                },
                {idLibro -> navController.navigate("detalle/$idLibro")},
                onAgregarLibro = {navController.navigate("agregar")},
                mensaje = mensaje,
                onMensajeMostrado = { mensaje = null }
            )
        }
        //--RUTA PARA ENVIAR A PANTALLA AGREGAR LIBRO
        composable("agregar"){
            PantallaAgregarLibro(
                viewModel = viewModel(),
                onGuardar = {
                    //mensaje a mostrar cuando se guarde el libro
                    mensaje = "✔ Libro guardado con éxito"
                    navController.popBackStack()
                },
                onCancelar = {
                    navController.popBackStack()
                }

            )
        }


        composable("detalle/{idLibro}") {
            val idLibro = it.arguments?.getString("idLibro")?.toIntOrNull() //###
            //--
            val app = LocalContext.current.applicationContext as BibliotecaApplication

            val viewModel: LibroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override  fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return LibroViewModel(app as Application) as T
                    }
                }
            )

            val libro by viewModel.libroSeleccionado.collectAsState()
            //#
            LaunchedEffect(idLibro) {
                if (idLibro != null){
                    viewModel.cargarLibroPorId(idLibro)
                }
            }
            if (libro != null){
                PantallaDetalleLibro(libro = libro!!,
                    onRegresar = {navController.popBackStack()},


                    //añadi este otro parametro para que pueda editar el libro
                    navController = navController,




                    onEditar = {
                        //invoca a la ruta de edicion pasando el id del libro
                            idLibro -> navController.navigate("editar/$idLibro")
                    },
                    onEliminar = {
                        //elimina el libro pasando el objeto libro
                            libroEliminar-> viewModel.eliminarLibro(libroEliminar)
                        //mensaje a mostrar cuando se elimine el libro
                        mensaje = "✔ Libro eliminado con éxito"
                        //regresa a la pantalla de catalogo
                        navController.popBackStack()
                    })
            }
        }
        composable("editar/{idLibro}") {
            val idLibro = it.arguments?.getString("idLibro")?.toIntOrNull() //###
            val app = LocalContext.current.applicationContext as BibliotecaApplication
            val viewModel: LibroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override  fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return LibroViewModel(app as Application) as T
                    }
                }
            )
            val libro by viewModel.libroSeleccionado.collectAsState()

            LaunchedEffect(idLibro) {
                if (idLibro != null){
                    viewModel.cargarLibroPorId(idLibro)
                }
            }
            if (libro != null) {
                PantallaEditarLibro(
                    libro = libro!!,



                    /*
                    onGuardar = { libroEditado ->
                        viewModel.actualizarLibro(libroEditado)
                        //mensaje a mostrar cuando se guarde el libro
                        mensaje = "✔ Libro modificado con éxito"
                        navController.popBackStack()
                        // --------- REALICE UN PEQUEÑO CAMBIO PARA QUE LA NOTIFICACIÓN DE MODIFICADO
                        //APAREZCA EN PANTALLADETALLELIBRO, YA QUE PARA PODER VISUALIZARLA, TENIAMOS QUE AMNUALMENTE VOLVER
                        //HASTA PANTALLA CATALOGO
                    }*/



                    onGuardar = { libroEditado ->

                        viewModel.actualizarLibro(libroEditado)

                        // Enviamos el mensaje a la pantalla anterior (Detalle)
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(
                                "mensaje",
                                "✓ Cambios guardados correctamente"
                            )

                        navController.popBackStack()

                    },
                    onCancelar = {
                        navController.popBackStack()
                    }

                )
            }
        }
        composable("prestamo") {

            PantallaPrestamo(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }

        composable("prestados") {

            PantallaLibrosPrestados(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }
    }
}