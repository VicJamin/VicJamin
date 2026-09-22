package com.example.bibliotech.data


import com.example.bibliotech.model.Libro


class LibroRepository(
    private val libroDao: LibroDao
) {

    fun insertarLibro(libro: Libro): Long {
        return libroDao.insertarLibro(libro)
    }

    fun obtenerLibros(): List<Libro> {
        return libroDao.obtenerLibros()
    }

    //funcion para traer un libro en la base de datos ID *READ*
    fun obtenerLibroPorId(id: Int): Libro? {
        return libroDao.obtenerLibroPorId(id)
    }

    fun actualizarLibro(libro: Libro) {
        libroDao.actualizarLibro(libro)
    }

    fun eliminarLibro(libro: Libro ) {
        libroDao.eliminarLibro(libro)
    }
}
