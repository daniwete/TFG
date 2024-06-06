package com.example.crud2new.repositorios

import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.RespuestaApi
import com.example.crud2new.Model.Usuario

import retrofit2.Response
import retrofit2.http.*
import java.util.List

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepoApi {

    //Instruments

    @GET("/camisetas/todos")
    suspend fun getCamisetas(): Response<RespuestaApi<kotlin.collections.List<Camiseta>>>

    @GET("/camisetas/esp/{id}")
    suspend fun getCamiseta(@Path("id") id: String): Response<RespuestaApi<Camiseta>>

    @POST("/camisetas/agregar")
    suspend fun addCamiseta(@Body intrument: Camiseta): Response<RespuestaApi<Camiseta>>


    //Users

    @GET("/usuarios/get/{id}")
    suspend fun getUsuario(@Path("id") id: String): Response<RespuestaApi<Usuario>>

    @POST("/usuarios/post")
    suspend fun addUsuario(@Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @POST("/usuarios/auth")
    suspend fun authUsuario(@Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @PUT("/usuarios/put/{id}")
    suspend fun actUsuario(@Path("id") id: String, @Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @PUT("/usuarios/agregarCarrito/{id}/ins/{idC}")
    suspend fun actUsuario(@Path("id") id: String,@Path("idC") idC: String): Response<RespuestaApi<Usuario>>

    @DELETE("/usuarios/{id}")
    suspend fun deleteUsuario(@Path("id") id: String): Response<RespuestaApi<Usuario>>
}