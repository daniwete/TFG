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

class ListadoCamisetasVM: ViewModel() {

    private val _camisetas = MutableLiveData<List<Camiseta>>()
    val camisetas: LiveData<List<Camiseta>> = _camisetas

    init {

        viewModelScope.launch{
            getCamisetas()
        }

    }

    suspend fun getCamisetas() {
        try {
            val result = api.getCamisetas()
            if (result.isSuccessful) {
                val response: RespuestaApi<List<Camiseta>> = result.body()!!
                Log.d("Datos de respuesta", response.toString())
                if (response.exito == true){
                    Log.d("Datos Camisetas", response.datos.toString())
                    _camisetas.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getCamisetas()", e)
            // Maneja los errores aquí
        }
    }

}