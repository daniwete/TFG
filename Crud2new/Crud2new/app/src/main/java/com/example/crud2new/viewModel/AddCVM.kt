package com.example.crud2new.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController

import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.RespuestaApi
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class AddCVM: ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _precio = MutableLiveData<String>()
    val precio: LiveData<String> = _precio

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _imagen = MutableLiveData<String>()
    val imagen: LiveData<String> = _imagen

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo


    fun CambiarInputs(nombre: String, precio: String,descripcion: String, imagen: String) {
        _nombre.value = nombre
        _precio.value = precio
        _descripcion.value = descripcion
        _imagen.value = imagen

        _botonActivo.value = nombre(nombre) && precio(precio) && desc(descripcion) && image(imagen)
    }

    private fun nombre(nombre: String?): Boolean = !nombre.isNullOrEmpty()

    private fun precio(precio: String?): Boolean = !precio.isNullOrEmpty()

    private fun desc(descripcion: String): Boolean =  !descripcion.isNullOrEmpty()

    private fun image(imagen: String): Boolean =  !imagen.isNullOrEmpty()


    fun RegisterC(nombre: String, precio: String, desc: String, image: String,navController: NavController,id:String){

        val numberValue: Number = precio.toDouble()

        val camiseta = Camiseta(nombre, desc, numberValue, image)
        viewModelScope.launch {
            try {
                val result = api.addCamiseta(camiseta)
                Log.d("Resultado del registro", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Camiseta> = result.body()!!
                    if (response.exito == true){
                        navController.navigate(AppScreens.MenuInicio.ruta + "?id=${id}")
                    }
                }
            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
            }
        }
    }
}