package com.paylastikca.presentation.view.common_ui.components.layout


import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun Dp.scaled(scale: Float): Dp = (this.value * scale).dp
fun TextUnit.scaled(scale: Float): TextUnit = (this.value * scale).sp
