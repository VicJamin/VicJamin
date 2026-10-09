package com.example.bibliotech.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest
import com.example.bibliotech.util.SesionManager

class UsuarioViewModel(application: Application) :
    AndroidViewModel(application) {

    private val usuarioRepository =
        (application as BibliotecaApplication).usuarioRepository

    private val _registroExitoso =
        MutableStateFlow(false)

    val registroExitoso: StateFlow<Boolean> =
        _registroExitoso.asStateFlow()

    private val _usuarioExiste =
        MutableStateFlow(false)

    val usuarioExiste: StateFlow<Boolean> =
        _usuarioExiste.asStateFlow()

    fun registrarUsuario(
        nombre: String,
        usuario: String,
        contrasena: String
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            val usuarioExistente =
                usuarioRepository.obtenerUsuarioPorNombre(
                    usuario
                )

            if (usuarioExistente != null) {

                _usuarioExiste.value = true
                _registroExitoso.value = false

                return@launch
            }

            val contrasenaHash =
                generarHash(contrasena)

            val nuevoUsuario =
                Usuario(
                    nombre = nombre,
                    usuario = usuario,
                    contrasena = contrasenaHash
                )

            usuarioRepository.insertarUsuario(
                nuevoUsuario
            )

            _usuarioExiste.value = false
            _registroExitoso.value = true
        }
    }

    /**
     * Convierte la contraseña original en un hash SHA-256.
     *
     * Un hash es una transformación de la información
     * que produce un valor diferente a la contraseña original.
     *
     * La idea es NO guardar la contraseña directamente
     * en la base de datos.
     */
    private fun generarHash(
        contrasena: String
    ): String {

        // MessageDigest permite aplicar algoritmos
        // de resumen (hash) a un texto.
        //
        // En este caso utilizamos SHA-256.
        val digest =
            MessageDigest.getInstance("SHA-256")

        // Convertimos la contraseña de texto
        // a una secuencia de bytes.
        //
        // Por ejemplo:
        // "123456" → bytes que representan ese texto.
        val bytes =
            contrasena.toByteArray()

        // Aplicamos SHA-256 a los bytes.
        //
        // El resultado es otro conjunto de bytes
        // que representa el hash de la contraseña.
        val hashBytes =
            digest.digest(bytes)

        // Los bytes obtenidos no son cómodos de almacenar
        // o visualizar directamente.
        //
        // Por eso convertimos cada byte a hexadecimal.
        //
        // Ejemplo conceptual:
        // bytes → "8d969eef6ecad3c29a3a629280e686cff8..."
        return hashBytes.joinToString("") {

            // %02x convierte cada byte en
            // dos caracteres hexadecimales.
            "%02x".format(it)
        }
    }


    fun reiniciarEstado() {
        _registroExitoso.value = false
        _usuarioExiste.value = false
    }

    // ---------------------------------------------------------
    // ESTADOS DEL LOGIN
    // ---------------------------------------------------------

    private val _loginExitoso =
        MutableStateFlow(false)

    val loginExitoso: StateFlow<Boolean> =
        _loginExitoso.asStateFlow()

    private val _loginIncorrecto =
        MutableStateFlow(false)

    val loginIncorrecto: StateFlow<Boolean> =
        _loginIncorrecto.asStateFlow()


    // ---------------------------------------------------------
    // INICIAR SESIÓN
    // ---------------------------------------------------------

    fun iniciarSesion(
        usuario: String,
        contrasena: String
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            // La contraseña que escribe el usuario
            // se convierte nuevamente en hash.
            val contrasenaHash =
                generarHash(contrasena)

            // Buscamos en Room un usuario que tenga
            // el nombre de usuario y el mismo hash.
            val usuarioEncontrado =
                usuarioRepository.validarUsuario(
                    nombreUsuario = usuario,
                    contrasena = contrasenaHash
                )

            if (usuarioEncontrado != null) {

                // Las credenciales son correctas.
                _loginExitoso.value = true
                _loginIncorrecto.value = false

            } else {

                // El usuario o la contraseña son incorrectos.
                _loginExitoso.value = false
                _loginIncorrecto.value = true
            }
        }
        fun reiniciarEstado() {

            _registroExitoso.value = false
            _usuarioExiste.value = false
            _loginExitoso.value = false
            _loginIncorrecto.value = false
        }
    }

}
