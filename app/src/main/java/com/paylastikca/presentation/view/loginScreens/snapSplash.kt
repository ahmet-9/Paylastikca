package com.paylastikca.presentation.view.loginScreens

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.app.R










@Composable
fun snapSplash(){

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(0xFFFF7200)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF7200)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(id = R.drawable.snapsplashlogo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(top = 230.dp)
        )

        CircularProgressIndicator(
            modifier = Modifier
                .padding(bottom = 40.dp),
            color = Color.White,
            strokeWidth = 3.dp
        )



    }

}




