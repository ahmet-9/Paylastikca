package com.paylastikca.presentation.view.loginScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.paylastikca.ui.theme.authBlue
import com.paylastikca.ui.theme.authBlueDisabled

@Composable
fun logInScreen(

    onSignUpClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {}
){

    val colors = MaterialTheme.colorScheme


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }






    Scaffold(
        containerColor = colors.background
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-60).dp)
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
                    modifier = Modifier.padding(top = 5.dp),
                    color = colors.onBackground
                )



                Spacer(modifier = Modifier.height(40.dp))





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
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    )

                )

                Spacer(modifier = Modifier.height(12.dp))


                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Şifre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.lockicon),
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    ),

                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null,
                                tint = colors.onSurfaceVariant
                            )
                        }
                    }
                )



                Spacer(modifier = Modifier.height(32.dp))



                Button(
                    onClick = { onSignUpClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = authBlue,
                        disabledContainerColor = authBlueDisabled,
                        contentColor = colors.onPrimary
                    )
                ) {
                    Text(text = "Giriş yap", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))





                OutlinedButton(
                    onClick = { onGoogleClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(colors.surface)
                            .shadow(1.dp, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_g),
                            contentDescription = "Google logo",
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Google ile devam et", fontSize = 15.sp, color = colors.onSurface)
                }


                Spacer(modifier = Modifier.height(32.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Şifreni mi unuttun?",
                        fontSize = 15.sp,
                        color = colors.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.None,
                        modifier = Modifier.clickable { onSignInClick() }.padding(start = 30.dp)
                    )
                    Text(
                        text = "Üye ol",
                        fontSize = 16.sp,
                        color = authBlue,

                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.None,
                        modifier = Modifier.clickable { onSignInClick() }.padding(end = 35.dp)
                    )
                }
            }
        }
    }

}

