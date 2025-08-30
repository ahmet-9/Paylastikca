package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun shoppingCentreScreen(){
    Scaffold(
        bottomBar = { BottomBar() }   // 👈 buraya verdin mi hep alta sabitlenir
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding) // 👈 içerik alt bara çarpmasın diye padding veriyoruz
        ) {
            // Buraya sayfanın içeriği gelecek
        }
    }
}
