package com.example.finalsecure.ui.login.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalsecure.api.API.retrofitService
import com.example.finalsecure.models.Direccion
import com.example.finalsecure.models.UsuarioRegisterDTO
import com.example.finalsecure.navigation.AppScreen
import com.example.finalsecure.ui.login.viewmodel.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegisterContent(viewModel: RegisterViewModel, navController: NavController) {
    RegisterScreen(viewModel, navController)
}

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavController) {
    val context = LocalContext.current

    val username by viewModel.username.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val passwordRepeat by viewModel.passwordRepeat.observeAsState("")
    val provincia by viewModel.province.observeAsState("")
    val municipio by viewModel.municipality.observeAsState("")
    val registerEnabled by viewModel.registerEnabled.observeAsState(true)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(value = username, onValueChange = { viewModel.updateUsername(it) }, label = { Text("Username") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { viewModel.updatePassword(it) }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = passwordRepeat, onValueChange = { viewModel.updatePasswordRepeat(it) }, label = { Text("Repeat Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = provincia, onValueChange = { viewModel.updateProvince(it) }, label = { Text("Provincia") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = municipio, onValueChange = { viewModel.updateMunicipality(it) }, label = { Text("Municipio") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Validar que las contraseñas coinciden antes de enviar la solicitud
                if (password == passwordRepeat) {
                    // Ejecutar la solicitud de registro
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            // Crear el objeto de solicitud de registro
                            val usuarioRegisterDTO = UsuarioRegisterDTO(
                                username = username,
                                password = password,
                                passwordRepeat = passwordRepeat,
                                roles = null,  // Asumiendo que el rol se asignará luego
                                direccion = Direccion(provincia, municipio)  // Crear la dirección
                            )

                            // Realizar la solicitud a la API para registrar el usuario
                            val result = retrofitService.register(usuarioRegisterDTO)

                            // Comprobar si la respuesta fue exitosa
                            if (result.isSuccessful) {
                                // Registro exitoso, navegar a la pantalla principal
                                withContext(Dispatchers.Main) {
                                    viewModel.delayed {
                                        navController.navigate(AppScreen.HomeScreen.route)
                                    }
                                }
                            } else {
                                // Manejar error en la respuesta de la API
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Error: ${result.message()}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } catch (e: Exception) {
                            // Manejar excepción en caso de error de red u otros problemas
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    // Si las contraseñas no coinciden, mostrar mensaje de error
                    Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        ) {
            Text("Register")
        }
    }
}
