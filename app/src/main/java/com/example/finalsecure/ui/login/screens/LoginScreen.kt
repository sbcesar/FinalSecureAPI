package com.example.finalsecure.ui.login.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalsecure.R
import com.example.finalsecure.ui.theme.BrilliantGray
import com.example.finalsecure.ui.theme.SkyBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent() {
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
                        Text(text = "©2024 MediaFire", fontSize = 11.sp)
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
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
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
            // EmailField()
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