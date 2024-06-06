package com.example.crud2new.Screens.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.example.crud2new.viewModel.CamisetaVM
import com.example.crud2new.viewModel.UserVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CardCamiseta(painter: String, titulo: String, total: String, alClickar: () -> Unit, navController: NavController, viewModel: CamisetaVM, viewModelu: UserVM) {

    val camiseta: Camiseta by viewModel.camiseta.observeAsState(Camiseta())
    val usuario: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))

    ElevatedCard(
        onClick = alClickar,
        modifier = Modifier
            .padding(8.dp) // Reduce el padding para hacer las cards más delgadas
            .width(160.dp), // Ajusta el ancho para que quepan más camisetas
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Cambia el color a blanco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp // Sombra leve
        )
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .border(2.dp, Color.Gray)
                    .padding(5.dp),
                painter = rememberAsyncImagePainter(painter),
                contentDescription = "camiseta"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titulo,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = total,
                color = Color.Red, // Cambia el color del texto a gris
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate(AppScreens.Carrito.ruta + "?id=${usuario._id}")
                    viewModelu.addToCart(camiseta, navController, usuario._id)
                },
                modifier = Modifier

                    .height(40.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAB340),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Añadir ",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp)
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(20.dp).scale(1f),
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCamisetas(titulo: String, total: String, navController: NavController) {
    var showPop by remember { mutableStateOf(false) }

    if (showPop) {
        AlertDialog(
            onDismissRequest = { showPop = false },
            title = {
                Text(
                    text = "Para ver este articulo inicia sesión",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate(AppScreens.Login.ruta)
                        showPop = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showPop = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    ElevatedCard(
        onClick = { showPop = true },
        modifier = Modifier
            .wrapContentHeight()
            .padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(109, 39, 145, 255),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = CircleShape)
                    .border(5.dp, Color.Gray)
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.fbshop),
                contentDescription = "camiseta"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titulo,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = total,
                color = Color.LightGray,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}
