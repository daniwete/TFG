package com.example.crud2new.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.RespuestaApi
import com.example.crud2new.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class CamisetaVM (id:String): ViewModel() {

    private val _camiseta = MutableLiveData<Camiseta>()
    val camiseta: LiveData<Camiseta> = _camiseta


    init {

        viewModelScope.launch{
            getCamiseta(id)
        }

    }

    suspend fun getCamiseta(id: String) {
        try {
            val result = api.getCamiseta(id)
            if (result.isSuccessful) {
                val response: RespuestaApi<Camiseta> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos Camisetas unico", response.datos.toString())
                    _camiseta.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getCamiseta()", e)
            // Maneja los errores aquí
        }
    }

}