package com.example.bibliotech.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bibliotech.model.Libro

@Dao
interface LibroDao {

    //Funcion para insertar un libro CREATE
    @Insert
    fun insertarLibro(libro: Libro): Long
//Funcion para traer un libro READ
    @Query("SELECT * FROM libros")
    fun obtenerLibros(): List<Libro>

    //Funcion para traer un libro en la base de datos ID *READ*
    @Query("SELECT * FROM libros WHERE id = :id")
    fun obtenerLibroPorId(id: Int): Libro?

    @Update
    fun actualizarLibro(libro: Libro)

    @Delete
    fun eliminarLibro(libro: Libro)

}
