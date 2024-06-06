package com.example.crud2new.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.crud2new.Model.RespuestaApi
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.retrofit.InstanciaRetrofit.RetrofitInstance.api

import kotlinx.coroutines.launch

class PassVM: ViewModel() {

    private val _contrasena = MutableLiveData<String>()
    val contrasena: LiveData<String> = _contrasena

    private val _contrasenaA = MutableLiveData<String>()
    val contrasenaA: LiveData<String> =  _contrasenaA

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    // LiveData to track the success of the password change
    private val _passwordChangeSuccess = MutableLiveData<Boolean>()
    val passwordChangeSuccess: LiveData<Boolean> = _passwordChangeSuccess

    fun CambiarInputs(contraA: String, contra: String) {
        _contrasenaA.value = contraA
        _contrasena.value = contra

        _botonActivo.value = ContraA(contraA) && Contra(contra)
    }

    private fun Contra(contra: String): Boolean = contra.isNotEmpty()

    private fun ContraA(contra: String?): Boolean = contra?.isNotEmpty() ?: false

    fun Cambiar(correo: String, contraA: String, contra: String, navController: NavController) {
        val usuario = Usuario("", "", correo, contraA, emptyList())

        viewModelScope.launch {
            try {
                val result = api.authUsuario(usuario)
                Log.d("Resultado del registro", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    val id = response.datos
                    if (id != null) {
                        val nuevoU = Usuario("", "", "", contra, emptyList())
                        Log.d("nuevoU", nuevoU.toString())

                        val act = api.actUsuario(id._id, nuevoU)
                        Log.d("Resultado del registro", act.toString())

                        if (act.isSuccessful) {
                            _passwordChangeSuccess.value = true
                            navController.navigate(AppScreens.Login.ruta)
                        } else {
                            _passwordChangeSuccess.value = false
                        }
                    } else {
                        _passwordChangeSuccess.value = false
                    }
                } else {
                    _passwordChangeSuccess.value = false
                }
            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
                _passwordChangeSuccess.value = false
            }
        }
    }
}
