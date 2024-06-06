package com.example.crud2new.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.R
import com.example.crud2new.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carrito(navController: NavController, viewModelu: UserVM) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))
    val camisetas: List<Camiseta> = usuario.camisetas
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¡CARRITO!",
                    style = TextStyle(
                        color = Color.Red,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 36.sp
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                        .weight(1f)  // Allow LazyColumn to take available space
                ) {
                    items(camisetas.size) { x ->
                        ElevatedCard(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(camisetas[x].imagen),
                                    contentDescription = "Imagen producto",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .border(
                                            2.dp,
                                            Color(0xFFFFA726),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clip(shape = RoundedCornerShape(10.dp))
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = camisetas[x].nombre,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Start,
                                        fontFamily = FontFamily.SansSerif,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = camisetas[x].descripcion,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Start,
                                        fontFamily = FontFamily.Serif,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "${camisetas[x].precio}€",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Red,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
                Text(
                    text = "TOTAL: ${calcularTotal(camisetas)}€",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End)  // Align text to the end
                )
            }
        }
    }
}

fun calcularTotal(camisetas: List<Camiseta>): Double {
    var total = 0.0
    for (camiseta in camisetas) {
        val precioNumerico = camiseta.precio.toDouble() ?: 0.0
        total += precioNumerico
    }
    return total
}
