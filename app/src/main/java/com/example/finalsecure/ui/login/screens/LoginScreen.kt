package com.example.finalsecure.ui.login.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalsecure.R
import com.example.finalsecure.api.API.retrofitService
import com.example.finalsecure.models.UsuarioLoginDTO
import com.example.finalsecure.navigation.AppScreen
import com.example.finalsecure.ui.login.viewmodel.LoginViewModel
import com.example.finalsecure.ui.theme.BrilliantGray
import com.example.finalsecure.ui.theme.SkyBlue
import com.example.finalsecure.ui.theme.SoftBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(loginViewModel: LoginViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SkyBlue
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { /* HACE ALGO */ }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_icon),
                            contentDescription = "Menu Icon",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                title = {

                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = BrilliantGray,
                contentColor = Color.Gray,
                modifier = Modifier.height(70.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "©2024 RiotGames", fontSize = 11.sp)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Build 121931", fontSize = 11.sp)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Need help?", fontSize = 11.sp)
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(text = "Submit a ticket", color = SkyBlue, fontSize = 11.sp, modifier = Modifier.clickable { /* TE ENVIA A UNA PAGINA PARA AYUDA */ })
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) {
        LoginScreen(loginViewModel, navController)
    }
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {

    val context = LocalContext.current

    val usernameText by loginViewModel.username.observeAsState("")
    val passwordText by loginViewModel.password.observeAsState("")
    val isPasswordVisible by loginViewModel.isPasswordVisible.observeAsState(false)
    val isLoginEnabled by loginViewModel.loginEnabled.observeAsState(false)

    Surface(
        color = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            RiotGamesLogo()
            Spacer(modifier = Modifier.height(30.dp))
            UsernameField(
                value = usernameText,
                onChange = { loginViewModel.updateEmail(it) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(
                value = passwordText,
                onChange = { loginViewModel.updatePassword(it) },
                modifier = Modifier.fillMaxWidth(),
                isPasswordVisible = isPasswordVisible,
                onClick = { loginViewModel.togglePasswordVisibility() }
            )
            Spacer(modifier = Modifier.padding(13.dp))
            LoginButton(
                text = "LOG IN",
                onClick = {
                    if (usernameText.isNotEmpty() && passwordText.isNotEmpty()) {
                        // Ejecutamos la solicitud de inicio de sesión
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                // Realizar la solicitud a la API con los datos de inicio de sesión
                                val usuarioLoginDTO = UsuarioLoginDTO(usernameText, passwordText)
                                val result = retrofitService.login(usuarioLoginDTO)

                                // Comprobamos si la respuesta fue exitosa
                                if (result.isSuccessful) {
                                    // Aquí manejamos la respuesta si la solicitud fue exitosa
                                    // Por ejemplo, podemos obtener el usuario
                                    val usuario = result.body()
                                    // Realizamos la navegación solo si la autenticación es exitosa
                                    withContext(Dispatchers.Main) {
                                        loginViewModel.delayed {
                                            navController.navigate(AppScreen.HomeScreen.route)
                                        }
                                    }
                                } else {
                                    // Aquí puedes manejar el error si la respuesta fue negativa
                                    withContext(Dispatchers.Main) {
                                        // Mostrar un mensaje de error
                                        Toast.makeText(context, "Login failed: ${result.message()}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } catch (e: Exception) {
                                // En caso de error en la llamada a la API, manejarlo
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    } else {
                        // Mostrar un mensaje si los campos están vacíos
                        Toast.makeText(context, "Por favor ingresa tu usuario y contraseña", Toast.LENGTH_SHORT).show()
                    }
                },
                containerColor = SkyBlue,
                isEnabled = isLoginEnabled
            )
            Spacer(modifier = Modifier.padding(32.dp))
            CreateAccount(
                onLinkClick = {
                    loginViewModel.delayed {
                        navController.navigate(
                            route = AppScreen.RegisterScreen.route
                        )
                    }
                }
            )
        }
    }
}


@Composable
fun RiotGamesLogo() {
    Image(
        painter = painterResource(id = R.drawable.riotgames_logo),
        contentDescription = "Mediafire Logo",
        modifier = Modifier.fillMaxWidth().scale(0.75f).padding(end = 20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameField(value: String, onChange: (String) -> Unit, modifier: Modifier = Modifier, placeholder: String = "Username") {

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        placeholder = { Text(placeholder) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = colorResource(R.color.black),
            unfocusedTextColor = Color.Black,
            containerColor = Color.White,
            unfocusedPlaceholderColor = Color.LightGray,
            focusedPlaceholderColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = SkyBlue,
            cursorColor = SkyBlue,
            selectionColors = TextSelectionColors(
                handleColor = SkyBlue,
                backgroundColor = SoftBlue
            )
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(value: String, onChange: (String) -> Unit, modifier: Modifier = Modifier, placeholder: String = "Password", onClick: () -> Unit, isPasswordVisible: Boolean) {
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        placeholder = { Text(placeholder) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = colorResource(R.color.black),
            unfocusedTextColor = Color.Black,
            containerColor = Color.White,
            unfocusedPlaceholderColor = Color.LightGray,
            focusedPlaceholderColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = SkyBlue,
            cursorColor = SkyBlue,
            selectionColors = TextSelectionColors(
                handleColor = SkyBlue,
                backgroundColor = SoftBlue
            )
        ),
        trailingIcon = {
            val image = if (isPasswordVisible) painterResource(R.drawable.visibility_off) else painterResource(R.drawable.visibility_on)
            val description = if (isPasswordVisible) "Contraseña Oculta" else "Contraseña visible"
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = image,
                    contentDescription = description
                )
            }
        }
    )
}

@Composable
fun LoginButton(text: String, onClick: () -> Unit, contentColor: Color = Color.White, containerColor: Color, isEnabled: Boolean) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor,
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = isEnabled
    ) {
        Text(text = text)
    }
}

@Composable
fun CreateAccount(onLinkClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BrilliantGray, shape = RoundedCornerShape(0.dp))
            .padding(vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 12.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Create an account",
                fontSize = 12.sp,
                color = SkyBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onLinkClick() }
            )
        }
    }
}