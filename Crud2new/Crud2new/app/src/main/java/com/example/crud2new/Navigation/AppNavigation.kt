package com.example.crud2new.Navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crud2new.Screens.AddCamiseta
import com.example.crud2new.Screens.Botas
import com.example.crud2new.Screens.Camiseta
import com.example.crud2new.Screens.Carrito



import com.example.crud2new.Screens.GuantesDePortero
import com.example.crud2new.Screens.HomeLog

import com.example.crud2new.Screens.Login
import com.example.crud2new.Screens.Inicio
import com.example.crud2new.Screens.MenuInicio
import com.example.crud2new.Screens.Pantalones
import com.example.crud2new.Screens.PassCh
import com.example.crud2new.Screens.Perfil
import com.example.crud2new.Screens.PersonaPerfil
import com.example.crud2new.Screens.Register
import com.example.crud2new.viewModel.AddCVM
import com.example.crud2new.viewModel.CamisetaVM
import com.example.crud2new.viewModel.ListadoCamisetasVM
import com.example.crud2new.viewModel.LoginVM
import com.example.crud2new.viewModel.PassVM
import com.example.crud2new.viewModel.RegisterVM
import com.example.crud2new.viewModel.UserVM


@Composable
fun appNavegacion(){
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.Inicio.ruta)
    {
        composable(
            route = "${ AppScreens.MenuInicio.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                Log.d("holi",id)
                MenuInicio(
                    navController = navigationController,
                    viewModel = ListadoCamisetasVM(),

                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${ AppScreens.HomeLog.ruta}",

            ) {
            HomeLog(
                navController = navigationController,
                viewModel = ListadoCamisetasVM(),
            )

        }

        composable(AppScreens.Inicio.ruta) { Inicio(navigationController) }

        composable("${AppScreens.Register.ruta}") {
            Register(
                navController = navigationController,
                viewModel = RegisterVM(),

            )
        }

        composable(
            route = "${AppScreens.Camiseta.ruta}?id={id}&idu={idu}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                } ,
                navArgument(name = "idu") {
                    type = NavType.StringType
                }
            )

        ) {
            val idu = it.arguments?.getString("idu")
            val id = it.arguments?.getString("id")

            if (id != null && idu != null) {
                Camiseta(
                    navController = navigationController,
                    viewModel = CamisetaVM(id),
                    viewModelu = UserVM(idu)
                )
            }
        }
        composable(
            route = "${ AppScreens.Perfil.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                Log.d("holi",id)
                Perfil(
                    navController = navigationController,
                    viewModelu = UserVM(id)
                )
            }
        }


        composable(AppScreens.Login.ruta) {
            Login(
                navController = navigationController,
                viewModel = LoginVM()

            )
        }
        composable(
            route = "${AppScreens.Carrito.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                Carrito(
                    navController = navigationController,
                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${AppScreens.Pantalones.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                Pantalones(
                    navController = navigationController,
                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${AppScreens.Botas.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                Botas(
                    navController = navigationController,
                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${AppScreens.GuantesDePortero.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
               GuantesDePortero(
                    navController = navigationController,
                    viewModelu = UserVM(id)
                )
            }
        }
        composable(
            route = "${AppScreens.AddC.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id")
            if( id != null) {
                AddCamiseta(
                    navController = navigationController,
                    viewModel =UserVM(id) ,
                    viewModelC = AddCVM()
                )
            }
        }

        composable(
            route = "${AppScreens.PassCh.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                PassCh(
                    navController = navigationController,
                    viewModel = PassVM(),
                    viewModelu = UserVM(id)
                )
            }
        }

        composable(
            route = "${AppScreens.PersonaPerfil.ruta}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                PersonaPerfil(
                    navController = navigationController,
                    viewModel = UserVM(id),

                )
            }
        }
    }
}




