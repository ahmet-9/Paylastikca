package com.paylastikca.presentation.view.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val AppOrange = Color(0xFFE67E00)
private val ScreenBg = Color(0xFFF6F6F6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileEditScreen(
    onPickPhoto: () -> Unit = {},
    onCancel: () -> Unit = {},
    onSave: (name: String, surname: String, status: String) -> Unit = { _, _, _ -> }
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = AppOrange,
            darkIcons = false
        )
    }


    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Öğrenci") }

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    //DEVELOPMENT OPTİON
    //**“This system can be developed further by building on it in the future.”**
   // val statusOptions = remember { listOf("Öğrenci", "D1", "D2", "D3", "D4", "D5") }


    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White
        ) {

            //DEVELOPMENT OPTİON
            //**“This system can be developed further by building on it in the future.”**

            /*
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            ) {
                Text(
                    text = "Meslek/Statü Seç",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
                )

                statusOptions.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                status = item
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    showSheet = false
                                }
                            }
                            .padding(horizontal = 18.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            fontSize = 14.sp,
                            color = Color(0xFF1A1A1A),
                            modifier = Modifier.weight(1f)
                        )
                        if (item == status) {
                            Text(
                                text = "Seçili",
                                fontSize = 12.sp,
                                color = AppOrange,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                }
            }
            */
        }
    }

    Scaffold(
        containerColor = ScreenBg,
        contentWindowInsets = WindowInsets(0)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .background(AppOrange),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Profili Düzenle",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box(contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF2E5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = AppOrange,
                            modifier = Modifier.size(64.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-6).dp, y = (-6).dp)
                            .clip(CircleShape)
                            .background(AppOrange)
                            .clickable { onPickPhoto() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "edit photo",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Fotoğrafı değiştir",
                    fontSize = 12.sp,
                    color = Color(0xFF9A9A9A),
                    modifier = Modifier.clickable { onPickPhoto() }
                )

                Spacer(Modifier.height(18.dp))


                PrettyOutlinedField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Ad"
                )

                Spacer(Modifier.height(12.dp))

                PrettyOutlinedField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = "Soyad"
                )

                Spacer(Modifier.height(16.dp))


                //DEVELOPMENT OPTİON
                //**“This system can be developed further by building on it in the future.”**
/*

                PrettyPickerField(
                    label = "Meslek/Statü",
                    text = status,
                    onClick = {
                        showSheet = true
                        scope.launch { sheetState.expand() }
                    }
                )
                */

                Spacer(Modifier.height(34.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = AppOrange),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(AppOrange)
                        )
                    ) {
                        Text("İptal", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }

                    Button(
                        onClick = { onSave(name, surname, status) },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppOrange,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Kaydet", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}

@Composable
private fun PrettyOutlinedField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFE0E0E0),
            unfocusedBorderColor = Color(0xFFE0E0E0),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedLabelColor = Color(0xFF6F6F6F),
            unfocusedLabelColor = Color(0xFF8A8A8A),
            cursorColor = AppOrange
        )
    )
}

@Composable
private fun PrettyPickerField(
    label: String,
    text: String,
    onClick: () -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = {},
        readOnly = true,
        enabled = false,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8A8A8A)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color(0xFFE0E0E0),
            disabledContainerColor = Color.White,
            disabledTextColor = Color(0xFF1A1A1A),
            disabledLabelColor = Color(0xFF8A8A8A)
        )
    )
}

