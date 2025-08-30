package com.paylastikca.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R






// Kalam font ailesi
val KalamFont = FontFamily(
    Font(R.font.kalam_regular, weight = FontWeight.Normal),
    Font(R.font.kalam_bold, weight = FontWeight.Bold),
    Font(R.font.kalam_light, weight = FontWeight.Light)
)

// Varsayılan Typography düzeni
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = KalamFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)




