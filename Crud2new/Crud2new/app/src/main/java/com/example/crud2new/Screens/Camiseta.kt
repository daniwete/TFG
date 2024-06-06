package com.example.crud2new.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.R
import com.example.crud2new.viewModel.CamisetaVM
import com.example.crud2new.viewModel.UserVM


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Camiseta(navController: NavController, viewModel: CamisetaVM, viewModelu: UserVM) {

    val camiseta: Camiseta by viewModel.camiseta.observeAsState(Camiseta())
    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))

    var optionsExpanded by remember { mutableStateOf(false) }

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
                    IconButton(onClick = { navController.navigate("${AppScreens.MenuInicio.ruta}?id=${usuario._id}") }) {
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF000000)
                        )
                    }
                }

                ElevatedCard(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            2.dp,
                            Color(0xFFFFA726),
                            shape = RoundedCornerShape(15.dp)  // Bordes redondeados más suaves
                        )
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(Color.White),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp // Sombra leve
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFFFFFFFF))
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = camiseta.nombre,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,  // Texto en negrita
                            fontSize = 28.sp,
                            color = Color(0xFF000000)  // Cambiar color de texto
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Image(
                            modifier = Modifier
                                .size(250.dp)
                                .border(
                                    2.dp,
                                    Color(0xFFFFA726),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clip(shape = RoundedCornerShape(10.dp)),
                            painter = rememberAsyncImagePainter(camiseta.imagen),
                            contentDescription = "Imagen Producto",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "€${camiseta.precio}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,  // Texto en negrita
                            fontSize = 22.sp,
                            color = Color(0xFFFF0000)  // Cambiar color de texto
                        )
                    }
                }

                ElevatedCard(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            1.dp,
                            Color(0xFFFFA726),
                            shape = RoundedCornerShape(15.dp)  // Bordes redondeados más suaves
                        )
                        .clip(shape = RoundedCornerShape(15.dp)),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp // Sombra leve
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                            .background(Color(0xFFFFFFFF)),

                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Text(
                            text = "Descripción:",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = camiseta.descripcion,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Serif,
                            fontSize = 20.sp,
                            color = Color(0xFF000000)
                        )
                    }
                }

                ElevatedCard(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(AppScreens.Carrito.ruta + "?id=${usuario._id}")
                                viewModelu.addToCart(camiseta, navController, usuario._id)
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFA726),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Añadir al",
                                color = Color.White,
                                style = TextStyle(fontSize = 20.sp)
                            )
                            Icon(
                                modifier = Modifier.padding(start = 5.dp),
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "Localized description",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }


    }
}