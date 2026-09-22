package com.example.bibliotech.model

/*
class Libro (

    val id: Int,
    val titulo: String,
    val autor: String,
    val categoria: String,
    val anio: Int,
    val disponible: Boolean = false
    )
*/



//importar la libreria
//Se encarga de comvertir la clase a una tabla
import androidx.room.Entity
import androidx.room.PrimaryKey

//Ahora la clase representa a *Libros en SQlite y ademas sirve de modelo para la UI*

@Entity ("libros")

data class Libro(
    //Identificador unico generado automaticamente
    @PrimaryKey(autoGenerate = true)
    //registro de propiedades de la clase
    val id: Int = 0,
    val titulo: String,
    val autor: String,
    val categoria: String,
    val anio: Int,
    val disponible: Boolean = false
)