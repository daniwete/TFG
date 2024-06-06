package com.example.crud2new.Screens

import androidx.annotation.DrawableRes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crud2new.Model.Usuario
import com.example.crud2new.R
import com.example.crud2new.viewModel.PassVM
import com.example.crud2new.viewModel.UserVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassCh(navController: NavController, viewModel: PassVM, viewModelu: UserVM) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val contrasenaA: String by viewModel.contrasenaA.observeAsState(initial = "")
    val contrasena: String by viewModel.contrasena.observeAsState(initial = "")
    val botonActivo: Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val user: Usuario by viewModelu.user.observeAsState(Usuario(emptyList()))
    val passwordChangeSuccess: Boolean? by viewModel.passwordChangeSuccess.observeAsState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(109, 39, 145, 255),
                    titleContentColor = colorResource(id = R.color.white),
                    navigationIconContentColor = colorResource(id = R.color.white),
                    actionIconContentColor = colorResource(id = R.color.white),
                ),
                title = {
                    Text(
                        "Hola ${user.nombre}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.fbshopsinfondo),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 1.dp)
                            .width(105.dp)
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Cambio contraseña",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))

                RegistroTextFields(
                    value = contrasenaA,
                    onValueChange = { viewModel.CambiarInputs(it, contrasena) },
                    label = "Contraseña actual"
                )
                Spacer(modifier = Modifier.height(5.dp))

                RegistroTextFields(
                    value = contrasena,
                    onValueChange = { viewModel.CambiarInputs(contrasenaA, it) },
                    label = "Contraseña",
                    esContra = true
                )

                Column(
                    modifier = Modifier
                        .offset(y = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = { viewModel.Cambiar(user.correo, contrasenaA, contrasena, navController) },
                        colors = ButtonDefaults.buttonColors(Color(109, 39, 145, 255)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
                    ) {
                        Text(
                            text = "Cambiar contraseña",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        if (passwordChangeSuccess != null) {
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("OK")
                    }
                },
                title = {
                    Text("Cambio de contraseña")
                },
                text = {
                    Text("Cambio de contraseña realizado correctamente.")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroTextFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes icon: Int? = null,
    esContra: Boolean = false
) {
    Column {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label, fontSize = 15.sp) },
            modifier = Modifier.width(330.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedIndicatorColor = colorResource(id = R.color.black),
                focusedIndicatorColor = colorResource(id = R.color.black),
                focusedLabelColor = colorResource(id = R.color.black),
                unfocusedLabelColor = colorResource(id = R.color.black)
            ),
            visualTransformation = if (esContra) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}