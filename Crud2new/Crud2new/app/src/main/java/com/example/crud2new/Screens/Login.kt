package com.example.crud2new.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crud2new.R
import com.example.crud2new.Navigation.AppScreens
import com.example.crud2new.viewModel.LoginVM


@Composable
fun Login(navController: NavController, viewModel: LoginVM) {



    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contrasena : String by viewModel.contrasena.observeAsState(initial = "")
    val botonActivo : Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val noValido : Boolean by viewModel.noValido.observeAsState(initial = false)


    if (noValido) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { },
            title = {
                Text(

                    text = "Correo o contraseña incorrectos",
                    textAlign = TextAlign.Center,
                    color = Color.Black

                )
            },
            confirmButton = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(

                        onClick = {
                            viewModel.DesactivarAlerta()
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background( Color(109, 39, 145, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {

        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fbshopsinfondo),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 150.dp)
                    .width(105.dp)

                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 170.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                LoginTextField(value = correo, onValueChange = { viewModel.CambiarInputs(it, contrasena) }, label = "Correo", icon = null,)
                Spacer(modifier = Modifier.height(25.dp))

                LoginTextField(value = contrasena, onValueChange = { viewModel.CambiarInputs(correo, it) }, label = "Contraseña", esContrasena = true)
                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .offset(y = 20.dp),
                ) {
                    ElevatedButton(
                        onClick = { viewModel.IntentarInicioSesion(correo, contrasena, navController) },
                        colors = ButtonDefaults.buttonColors(Color(109, 39, 145, 255)),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(330.dp)
                            .height(40.dp),
                        enabled = botonActivo
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Text(
                            text = "¿No tienes una cuenta? ",
                            color = Color(150,150,150),
                        )
                        Text(
                            text = "Regístrate",
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                navController.navigate(AppScreens.Register.ruta)
                            }
                        )
                    }
                }
            }
        }
        Button(
            onClick = { navController.navigate(AppScreens.MenuInicio.ruta) },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {

        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes icon: Int? = null,
    esContrasena: Boolean = false
) {
    Column {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label, fontSize = 15.sp) },
            modifier = Modifier.width(330.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray,
                unfocusedIndicatorColor = colorResource(id = R.color.black),
                focusedIndicatorColor = colorResource(id = R.color.black),
                //Color de las letras Correo dentro del OutLabel
                focusedLabelColor = colorResource(id = R.color.black),
                //Color de las letras Contraseña dentro del OutLabel
                unfocusedLabelColor = colorResource(id = R.color.black)
            ),
            trailingIcon = {
                if (icon != null) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                    )
                }
            },
            visualTransformation = if (esContrasena) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}

