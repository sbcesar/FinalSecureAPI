package com.example.finalsecure.ui.login.screens

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalsecure.navigation.AppScreen
import com.example.finalsecure.ui.login.viewmodel.RegisterViewModel

@Composable
fun RegisterContent(viewModel: RegisterViewModel, navController: NavController) {
    RegisterScreen(viewModel, navController)
}

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavController) {
    val username by viewModel.username.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val passwordRepeat by viewModel.passwordRepeat.observeAsState("")
    val provincia by viewModel.province.observeAsState("")
    val municipio by viewModel.municipality.observeAsState("")
    val registerEnabled by viewModel.registerEnabled.observeAsState(false)

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
                viewModel.delayed {
                    navController.navigate(
                        AppScreen.HomeScreen.route
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = registerEnabled
        ) {
            Text("Register")
        }
    }
}
