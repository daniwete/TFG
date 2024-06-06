package com.example.crud2new.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.RespuestaApi
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class UserVM(id:String): ViewModel() {

    private val _user = MutableLiveData<Usuario>()
    val user : LiveData<Usuario> = _user

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    init {

        viewModelScope.launch{
            getUsuario(id)
        }

    }

    fun getUsuario(id: String){
        viewModelScope.launch {
            try {
                val result = api.getUsuario(id)
                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){

                        _user.value = response.datos!!
                    }
                }
            } catch (ex: Exception) {
                Log.d("Fallo consiguiendo un usuario", ex.message.toString())
            }
        }
    }

    fun addToCart(camiseta: Camiseta, navController: NavController, id:String){
        Log.d("Holas", camiseta.toString())
        viewModelScope.launch {
            try {
                val result = api.actUsuario(id,camiseta._id)
                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){

                        navController.navigate(AppScreens.MenuInicio.ruta+"?id="+id)

                    }
                }
            } catch (ex: Exception) {
                Log.d("Fallo consiguiendo un usuario", ex.message.toString())
            }
        }

    }
}