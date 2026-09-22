package com.example.bibliotech.ui.componentes

import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.Bottom
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlaceholderVerticalAlign.Companion.Bottom
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle.Alignment.Companion.Bottom
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bibliotech.model.Libro
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment

@Composable

fun TarjetaLibro(libro: Libro, onVerDetalles: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column (modifier = Modifier.padding(10.dp)){

                Spacer(modifier = Modifier.width(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically){

            Icon(
                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = "Libro",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))


                    Column{
                        Text(
                            text = libro.titulo,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(text = "Autor: ${libro.autor}")
                    }


        }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


        }

        Button(
            onClick = onVerDetalles,
            modifier = Modifier.align(Alignment.End).padding(6.dp)
        ) {
            Text("Ver detalles")
        }
    }
}


