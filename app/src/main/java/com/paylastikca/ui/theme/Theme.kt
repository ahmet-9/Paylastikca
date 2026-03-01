package com.paylastikca.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

)

private val LightColorScheme = lightColorScheme(



/*buttons, bottomBar, emphasis */
   primary = BrandOrange,
   onPrimary = Color.White ,
    primaryContainer = BrandOrangeLight ,
    onPrimaryContainer = Color.Black,



  //  secondary = ,
  //  onSecondary = ,
  //  secondaryContainer = ,
  //  onSecondaryContainer = ,



    tertiary = rewardGreen,
    onTertiary = Color.White,
  //  tertiaryContainer = ,
  //  onTertiaryContainer = ,


    /* general background, text */
   background = Color.White,
    onBackground = Color.Black ,
    surface = Color.White ,
    onSurface = Color.Black,


    /* card,  backgrounds */
    surfaceVariant = lighterLightGray,
    onSurfaceVariant = Gray,



    /* black background*/
    inverseSurface = blackCard,
    inverseOnSurface = Color.White,




/* border , frame, line */
    outline = lightGray,
    outlineVariant =  lighterLightGray ,

    error = errorBoxRed ,
    onError = Color.White,
   // errorContainer = ,
 //   onErrorContainer = ,

  //  inverseSurface = ,
  //  inverseOnSurface = ,
  //  inversePrimary = ,

 //   scrim = ,
  //  surfaceTint = ,



)

@Composable
fun PaylastikcaTheme(
    darkTheme: Boolean =  isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}