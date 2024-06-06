package com.example.crud2new.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.crud2new.Model.Camiseta
import com.example.crud2new.Model.Usuario
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.R
import com.example.crud2new.Screens.componentes.CardCamiseta
import com.example.crud2new.Screens.componentes.CardCamisetas
import com.example.crud2new.viewModel.CamisetaVM
import com.example.crud2new.viewModel.ListadoCamisetasVM
import com.example.crud2new.viewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuInicio(navController: NavController, viewModel: ListadoCamisetasVM, viewModelu: UserVM) {

    var optionsExpanded by remember { mutableStateOf(false) }
    var sortExpanded by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val camisetas: List<Camiseta> by viewModel.camisetas.observeAsState(emptyList())
    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))

    val options = listOf(
        "Pantalones",
        "Botas",
        "Guantes"
    )

    val sortOptions = listOf(
        "Mayor precio",
        "Menor precio",
        "A-Z",
        "Z-A"
    )
    var sortOption by remember { mutableStateOf("Menor precio") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(109, 39, 145, 255),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("", color = Color(92, 91, 91, 119)) },
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
                        onDismissRequest = { optionsExpanded = false }
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    when (option) {
                                        "Pantalones" -> navController.navigate("${AppScreens.Pantalones.ruta}?id=${usuario._id}")
                                        "Botas" -> navController.navigate("${AppScreens.Botas.ruta}?id=${usuario._id}")
                                        "Guantes" -> navController.navigate("${AppScreens.GuantesDePortero.ruta}?id=${usuario._id}")
                                    }
                                    optionsExpanded = false
                                }
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            Log.d("ID DEL USUARIO",usuario._id)
                            navController.navigate("${AppScreens.AddC.ruta}?id=${usuario._id}")
                        }
                    )
                    {
                        Icon(
                            Icons.Rounded.AddCircle,

                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { navController.navigate(AppScreens.Carrito.ruta+"?id=${usuario._id}" )}) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "¡MÁS VENDIDOS!",
                        style = TextStyle(
                            color = Color.Red,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 36.sp
                        ),
                        modifier = Modifier.padding(start = 50.dp, bottom = 36.dp)
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Button(
                            onClick = { sortExpanded = !sortExpanded },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(111, 110, 110, 119),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Ordenar por")
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                        DropdownMenu(
                            expanded = sortExpanded,
                            onDismissRequest = { sortExpanded = false }
                        ) {
                            sortOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option) },
                                    onClick = {
                                        sortOption = option
                                        sortExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                val sortedCamisetas = when (sortOption) {
                    "Mayor precio" -> camisetas.sortedByDescending { it.precio.toDouble() }
                    "Menor precio" -> camisetas.sortedBy { it.precio.toDouble() }
                    "A-Z" -> camisetas.sortedBy { it.nombre }
                    "Z-A" -> camisetas.sortedByDescending { it.nombre }
                    else -> camisetas
                }
                val chunkedCamisetas = sortedCamisetas.chunked(2)
                items(chunkedCamisetas) { chunk ->
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(chunk) { camiseta ->
                            CardCamiseta(
                                painter = camiseta.imagen,
                                titulo = camiseta.nombre,
                                total = "${camiseta.precio} €",
                                alClickar = {
                                    navController.navigate(AppScreens.Camiseta.ruta + "?id=" + camiseta._id + "&idu=${usuario._id}")
                                },
                                navController = navController,
                                viewModel = CamisetaVM(camiseta._id),
                                viewModelu = viewModelu
                            )
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLog(navController: NavController, viewModel: ListadoCamisetasVM){

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val camisetas: List<Camiseta> by viewModel.camisetas.observeAsState(emptyList())


    LaunchedEffect(Unit) {

        viewModel.getCamisetas()

    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(109, 39, 145, 255),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("", color = Color(92, 91, 91, 119)) },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.fbshopsinfondo),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 150.dp)
                            .width(105.dp)
                            .clickable {
                                navController.navigate("MenuInicio")
                            }
                            .height(65.dp),
                        contentScale = ContentScale.Crop
                    )


                },
                actions = {
                    IconButton(onClick = { navController.navigate("Carrito") }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { navController.navigate("Perfil") }) {
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
            LazyColumn(modifier = Modifier
                .padding(top = 15.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize(),
                content = {
                    items(camisetas.size) { x ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CardCamisetas(camisetas[x].nombre, "${camisetas[x].precio} €",navController)
                        }
                    }
                }
            )
        }
    }


}