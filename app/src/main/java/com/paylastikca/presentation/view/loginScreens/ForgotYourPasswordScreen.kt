package com.paylastikca.presentation.view.loginScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ForgotYourPasswordScreen(

    onBackToLoginClick: () -> Unit = {}




){
    var email by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-50).dp)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                // ÜSTTEKİ LOGO
                Image(
                    painter = painterResource(id = R.drawable.signuploginpasswordscreenimage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(230.dp)
                        .offset(y = 65.dp),
                    contentScale = ContentScale.Fit
                )




                Spacer(modifier = Modifier.height(16.dp))

                // BAŞLIK
                Text(
                    text = "Şifreni Sıfırla",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // GÖLGELİ E-POSTA ALANI
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-posta") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),

                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.mailicon),
                            contentDescription = null,
                            tint = Color.Gray,      // ikon rengi (istersen değişebilirsin)
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF969595),   // 🔥 Tıklayınca çerçeve turuncu
                        unfocusedBorderColor = Color(0xFFD0D0D0), // 🔥 Normalde açık gri
                        cursorColor = Color(0xFFFF7200),          // 🟧 İmleç turuncu
                        focusedLabelColor = Color(0xFFFF7200)     // 🟧 Label (yukarı kayma) turuncu
                    )

                    )

                Spacer(modifier = Modifier.height(32.dp))

                // ORTADAKİ ZARF GÖRSELİ
                Image(
                    painter = painterResource(id = R.drawable.forgotyourpasswordscreenimage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(180.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(24.dp))

                // TURUNCU BUTON
                Button(
                    onClick = { /* doğrulama kodu gönder */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7200)
                    )
                ) {
                    Text(
                        text = "Doğrulama kodu gönder",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // AÇIKLAMA METNİ
                Text(
                    text = "E-posta adresini girdikten sonra, hesabını doğrulaman için sana bir kod göndereceğiz.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // GİRİŞE DÖN LİNKİ
                Text(
                    text = "Girişe dön",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1E63D5),
                    modifier = Modifier.clickable { onBackToLoginClick() }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    /*


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }






    Scaffold(
        containerColor = Color.White
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    painter = painterResource(id = R.drawable.signuploginpasswordscreenimage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(310.dp)
                        .offset(y = 85.dp) ,
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "Paylaştıkça",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 5.dp)
                )



                Spacer(modifier = Modifier.height(40.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-posta") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),

                    )



/*
/* “THIS EMAIL BAR DESIGN SHOULD BE USED FOR THE SIGN-UP SCREEN, SIGN-IN SCREEN,
 AND THE FORGOT-YOUR-PASSWORD SCREEN.”
   */
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-posta") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(14.dp),
                            clip = false
                        )
                        .background(
                            color = Color(0xFFF7F7F7),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        cursorColor = Color(0xFF1E63D5)
                    )
                )

                */

                Image(
                    painter = painterResource(id = R.drawable.forgotyourpasswordscreenimage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(310.dp)
                        .offset(y = 85.dp) ,
                    contentScale = ContentScale.Fit
                )


                Spacer(modifier = Modifier.height(12.dp))







                Spacer(modifier = Modifier.height(12.dp))


     
            }
        }
    }
    */

}