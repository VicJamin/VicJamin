package com.example.bibliotech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.compose.rememberNavController
import com.example.bibliotech.ui.Navegacion
import com.example.bibliotech.ui.PantallaPrincipal
import com.example.bibliotech.ui.theme.BiblioTechTheme
import androidx.lifecycle.lifecycleScope
import com.example.bibliotech.data.librosPrueba
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as BibliotecaApplication
        val repository = app.libroRepository

        lifecycleScope.launch(Dispatchers.IO) {

            if (repository.obtenerLibros().isEmpty()) {
                librosPrueba.forEach { libro ->
                    repository.insertarLibro(libro)
                }
            }

            val librosGuardados = repository.obtenerLibros()

            println("LIBROS EN ROOM: ${librosGuardados.size}")

            librosGuardados.forEach {
                println("Libro: ${it.id} - ${it.titulo}")
            }
        }


        setContent {
            BiblioTechTheme {
                val navController = rememberNavController()

                Navegacion(
                    navController = navController
                )
            }
        }



    }
}



