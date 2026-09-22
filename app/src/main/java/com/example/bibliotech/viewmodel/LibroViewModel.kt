package com.example.bibliotech.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        (application as BibliotecaApplication).libroRepository
    //creamos un estado que almacena la lista de libros, iniciativamente vacia

    private val _libros = MutableStateFlow<List<Libro>>(emptyList())
    val libros: StateFlow<List<Libro>> = _libros.asStateFlow()

    private val _libroSeleccionado = MutableStateFlow<Libro?>(null)
    val libroSeleccionado: StateFlow<Libro?> = _libroSeleccionado.asStateFlow()

    //funcion para seleccionar un libro


    fun cargarLibros() {
        viewModelScope.launch(Dispatchers.IO) {
            _libros.value = repository.obtenerLibros()
        }
    }

    fun insertarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertarLibro(libro)
            _libros.value = repository.obtenerLibros()
        }
    }

    fun cargarLibroPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _libroSeleccionado.value = repository.obtenerLibroPorId(id)
        }
    }

    fun actualizarLibro(libro: Libro) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizarLibro(libro)
            _libros.value = repository.obtenerLibros()
        }

    }

    fun eliminarLibro(libro: Libro)  {
        viewModelScope.launch(Dispatchers.IO) {
            repository.eliminarLibro(libro)
            _libros.value = repository.obtenerLibros()
        }
    }
}



