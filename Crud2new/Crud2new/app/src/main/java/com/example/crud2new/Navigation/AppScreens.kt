package com.example.crud2new.Navigation

    // Definición de una clase sellada llamada AppScreens con una propiedad ruta
    sealed class AppScreens(val ruta: String) {

        object MenuInicio : AppScreens("MenuInicio")

        object HomeLog : AppScreens("HomeLog")

        object Inicio : AppScreens("Inicio")

        object Login: AppScreens("Login")

        object Register: AppScreens("Register")

        object Camiseta : AppScreens("Camiseta")

        object Perfil : AppScreens("Perfil")

        object PassCh:AppScreens("PassCh")

        object AddC: AppScreens("AddCamiseta")

        object PersonaPerfil : AppScreens("PersonaPerfil")

        object Carrito : AppScreens("Carrito")

        object Pantalones: AppScreens("Pantalones")

        object Botas : AppScreens("Botas")

        object GuantesDePortero : AppScreens("GuantesDePortero")

    }
