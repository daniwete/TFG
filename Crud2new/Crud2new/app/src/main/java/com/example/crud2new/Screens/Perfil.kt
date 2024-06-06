package com.example.crud2new.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.R
import com.example.crud2new.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavController, viewModelu: UserVM) {

    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))

    var optionsExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val options = listOf(
        "Camiseta",
        "Botas",
        "Pantalones",
        "Guantes"
    )

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(109, 39, 145, 255),
                    titleContentColor = MaterialTheme.colorScheme.primary,


                    ),
                title = { Text("", color = Color.Black) },

                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.fbshopsinfondo),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 150.dp)
                            .width(105.dp)
                            .clickable {
                                navController.navigate("${AppScreens.MenuInicio.ruta}?id=${usuario._id}")
                            }
                            .height(65.dp),
                        contentScale = ContentScale.Crop
                    )


                    Button(
                        onClick = { optionsExpanded = !optionsExpanded },
                        modifier = Modifier.padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(

                            containerColor = Color(111, 110, 110, 119),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(

                            imageVector = Icons.Filled.Menu,

                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = optionsExpanded,
                        onDismissRequest = { optionsExpanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    when (option) {
                                        "Camiseta" -> navController.navigate("${AppScreens.MenuInicio.ruta}?id=${usuario._id}")
                                        "Botas" -> navController.navigate("${AppScreens.Botas.ruta}?id=${usuario._id}")
                                        "Pantalones" -> navController.navigate("${AppScreens.Pantalones.ruta}?id=${usuario._id}")
                                        "Guantes" -> navController.navigate("${AppScreens.PersonaPerfil.ruta}?id=${usuario._id}")
                                    }
                                    optionsExpanded = false
                                }
                            )
                        }
                    }

                },

                actions = {
                    IconButton(onClick = { navController.navigate(AppScreens.Carrito.ruta+"?id=${usuario._id}") }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { navController.navigate(AppScreens.Perfil.ruta+"?id=${usuario._id}") }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }


                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .background(Color(255, 255, 255, 255))
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { navController.popBackStack()},
                    modifier = Modifier

                        .padding(end = 330.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                // Título de la pantalla
                Text(
                    text = "Perfil",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Spacer(modifier = Modifier.size(50.dp))
                // Opciones de ajustes

                Button(
                    onClick = { navController.navigate(AppScreens.PersonaPerfil.ruta+"?id=${usuario._id}") },
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .height(50.dp)
                        .width(250.dp)
                        .shadow(5.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 39, 145, 255),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = "Perfil",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(120.dp))

                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp).scale(2f)
                        )
                    }
                }
                Button(
                    onClick = { navController.navigate(AppScreens.PassCh.ruta+"?id=${usuario._id}") },
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .height(50.dp)
                        .width(250.dp)
                        .shadow(5.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 39, 145, 255),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),

                    ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = "Contraseña",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(40.dp))

                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp).scale(2f)
                        )
                    }
                }


                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .height(50.dp)
                        .width(250.dp)
                        .shadow(5.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 39, 145, 255),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Cerrar sesión",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(20.dp))

                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp).scale(2f)
                        )
                    }
                }

                // Muestra el diálogo de confirmación cuando showDialog es true
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            // Al cerrar el diálogo, restablece showDialog a false
                            showDialog = false
                        },
                        title = {
                            Text("Cerrar sesión")
                        },
                        text = {
                            Text("¿Está seguro de que desea cerrar sesión?")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    // Navegar de regreso a la pantalla de inicio de sesión
                                    navController.navigate("Login")
                                    // Restablecer showDialog a false al confirmar
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Green,
                                    contentColor = Color.White
                                ),
                            ) {
                                Text("Aceptar")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    // Al hacer clic en "Cancelar", simplemente cierra el diálogo
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ),
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}