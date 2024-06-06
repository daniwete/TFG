package com.example.crud2new.viewModel

import android.util.Log
import android.util.Patterns
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

class LoginVM: ViewModel() {
    private val _correo = MutableLiveData<String>()
    val correo : LiveData<String> = _correo

    private val _contrasena = MutableLiveData<String>()
    val contrasena : LiveData<String> = _contrasena

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _noValido = MutableLiveData<Boolean>()
    val noValido : LiveData<Boolean> = _noValido


    fun CambiarInputs(correo: String, contrasena: String){
        _correo.value = correo
        _contrasena.value = contrasena
        _botonActivo.value = EsCorreoValido(correo) && EsContraValida(contrasena)
    }

    private fun EsCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    private fun EsContraValida(contrasena: String): Boolean = contrasena.length > 6

    fun IntentarInicioSesion(correo: String, contrasena: String, navController: NavController){
        viewModelScope.launch {
            try {
                Log.d("Lo intenta almenos", noValido.value.toString())
                Log.d("Lo intenta almenos", correo)
                Log.d("Lo intenta almenos", contrasena)

                val result = api.authUsuario(Usuario("","",correo,contrasena,emptyList()))

                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){
                        val usuario = response.datos!!

                        Log.d("Intenando inicio", usuario._id)

                        navController.navigate(AppScreens.MenuInicio.ruta+"?id=${usuario._id}")

                    }
                }else{
                    _noValido.value = true
                }
            } catch (ex: Exception) {
                Log.d("falloInicio", ex.message.toString())
            }
        }
    }

    fun DesactivarAlerta(){
        _noValido.value = false
        _correo.value = ""
        _contrasena.value = ""
    }
}