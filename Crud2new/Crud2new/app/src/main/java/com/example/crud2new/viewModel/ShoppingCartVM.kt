package com.example.crud2new.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.RespuestaApi
import com.example.crud2new.Model.Usuario
import com.example.crud2new.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class ShoppingCartVM(id:String): ViewModel() {

    private val _camisetas = MutableLiveData<List<Camiseta>>()
    val camisetas: LiveData<List<Camiseta>> = _camisetas

    init {

        viewModelScope.launch{

            getCamisetasShoppingCart(id)

        }

    }

    private suspend fun getCamisetasShoppingCart(id:String) {
        try {
            Log.d("NNNNNNNNNNNN", id)
            val result = api.getUsuario(id)
            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
                Log.d("Datos de respuesta", response.toString())
                if (response.exito == true){
                    Log.d("Datos Camisetas", response.datos.toString())
                    val camisetase = response.datos!!.camisetas
                    _camisetas.value = camisetase
                }
            }
        } catch (e: Exception) {
            Log.e("Error en el carrito", e.toString())
        }
    }


}