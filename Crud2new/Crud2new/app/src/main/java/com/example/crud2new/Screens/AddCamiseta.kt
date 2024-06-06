package com.example.crud2new.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.R
import com.example.crud2new.viewModel.AddCVM
import com.example.crud2new.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCamiseta(viewModel: UserVM, viewModelC: AddCVM, navController: NavController){

    val usuario: Usuario by viewModel.user.observeAsState(Usuario(emptyList()))
    val botonActivo: Boolean by viewModelC.botonActivo.observeAsState(false)
    val nombre: String by viewModelC.nombre.observeAsState("")
    val precio: String by viewModelC.precio.observeAsState("")
    val descripcion: String by viewModelC.descripcion.observeAsState("")
    val imagen: String by viewModelC.imagen.observeAsState("")


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
                    IconButton(onClick = { navController.navigate("${AppScreens.Carrito.ruta}?id=${usuario._id}") }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { navController.navigate("${AppScreens.Perfil.ruta}?id=${usuario._id}") }) {
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
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(innerPadding))
        {

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Regístrar Camiseta",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))

                RegistroTextField(
                    value = nombre,
                    onValueChange = { viewModelC.CambiarInputs(it, precio, descripcion, imagen) },
                    label = "Nombre"
                )
                Spacer(modifier = Modifier.height(5.dp))


                RegistroTextField(
                    value = precio,
                    onValueChange = { viewModelC.CambiarInputs(nombre, it, descripcion, imagen) },
                    label = "Precio de la camiseta"
                )
                Spacer(modifier = Modifier.height(5.dp))

                RegistroTextField(
                    value = descripcion,
                    onValueChange = { viewModelC.CambiarInputs(nombre, precio, it, imagen) },
                    label = "Descripción"
                )
                Spacer(modifier = Modifier.height(5.dp))

                RegistroTextField(
                    value = imagen,
                    onValueChange = { viewModelC.CambiarInputs(nombre, precio, descripcion, it) },
                    label = "Imagen"
                )
                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    modifier = Modifier
                        .offset(y = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = { viewModelC.RegisterC(nombre, precio,descripcion,imagen,navController,usuario._id) },
                        colors = ButtonDefaults.buttonColors(Color(137, 189, 187)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
                    ) {
                        Text(
                            text = "Registrar Camiseta",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }
        }
    }

}