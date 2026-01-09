package com.paylastikca.presentation.view.profileScreen.contactInformationScreens

import android.widget.NumberPicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import java.util.Calendar

@Composable
fun dateOfBirth(
    currentBirthDate: String,
    onDismiss: () -> Unit,
    onSave: (formattedDate: String) -> Unit
) {


    val colors = MaterialTheme.colorScheme
    val nowYear = Calendar.getInstance().get(Calendar.YEAR)


    val initial = remember(currentBirthDate) {
        parseBirthDateDdMmYyyy(currentBirthDate)
            ?: parseBirthDateTrLoose(currentBirthDate)
            ?: Triple(1, 1, 1990)
    }

    var day by remember { mutableIntStateOf(initial.first.coerceIn(1, 31)) }
    var month by remember { mutableIntStateOf(initial.second.coerceIn(1, 12)) }
    var year by remember { mutableIntStateOf(initial.third.coerceIn(1920, nowYear)) }

    fun isLeap(y: Int) =
        (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)

    fun daysInMonth(m: Int, y: Int) = when (m) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (isLeap(y)) 29 else 28
        else -> 31
    }

    fun clampDay() {
        val max = daysInMonth(month, year)
        if (day > max) day = max
    }

    LaunchedEffect(month, year) { clampDay() }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 2.dp,
            color = colors.surface,
            contentColor = colors.onSurface,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    "Doğum Tarihi",
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    AndroidNumberPicker(
                        value = day,
                        range = 1..daysInMonth(month, year),
                        formatter = { it.toString() },
                        onValueChange = { day = it },
                        modifier = Modifier.weight(1f),
                        textColor = colors.onSurface
                    )


                    val months = listOf(
                        "Oca","Şub","Mar","Nis","May","Haz",
                        "Tem","Ağu","Eyl","Eki","Kas","Ara"
                    )
                    AndroidNumberPicker(
                        value = month,
                        range = 1..12,
                        formatter = { months[it - 1] },
                        onValueChange = { month = it },
                        modifier = Modifier.weight(1f),
                        textColor = colors.onSurface,
                    )


                    AndroidNumberPicker(
                        value = year,
                        range = 1920..nowYear,
                        formatter = { it.toString() },
                        onValueChange = { year = it },
                        modifier = Modifier.weight(1f),
                        textColor = colors.onSurface,
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = colors.primary
                        ),
                        border = BorderStroke(1.dp, colors.primary)
                    ) {
                        Text("İptal")
                    }


                    Button(
                        onClick = {
                            val dd = day.toString().padStart(2, '0')
                            val mm = month.toString().padStart(2, '0')
                            onSave("$dd/$mm/$year")
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary
                        )
                    ) {
                        Text("Kaydet")
                    }

                }
            }
        }
    }
}

@Composable
private fun AndroidNumberPicker(
    value: Int,
    range: IntRange,
    formatter: (Int) -> String,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textColor : Color
) {
    AndroidView(
        modifier = modifier.height(140.dp),
        factory = { context ->
            NumberPicker(context).apply {
                minValue = range.first
                maxValue = range.last
                wrapSelectorWheel = true
                setFormatter { v -> formatter(v) }
                this.value = value
                setOnValueChangedListener { _, _, newVal -> onValueChange(newVal) }

                (0 until childCount).forEach { i ->
                    val child = getChildAt(i)
                    if (child is android.widget.EditText) {
                        child.setTextColor(textColor.toArgb())
                        child.setBackgroundColor(android.graphics.Color.TRANSPARENT)
                    }
                }

            }
        },
        update = { picker ->
            picker.minValue = range.first
            picker.maxValue = range.last
            picker.setFormatter { v -> formatter(v) }

            val clamped = value.coerceIn(range.first, range.last)
            if (picker.value != clamped) picker.value = clamped

            (0 until picker.childCount).forEach { i ->
                val child = picker.getChildAt(i)
                if (child is android.widget.EditText) {
                    child.setTextColor(textColor.toArgb())
                    child.setBackgroundColor(android.graphics.Color.TRANSPARENT)
                }
            }

        }
    )
}





private fun parseBirthDateDdMmYyyy(s: String): Triple<Int, Int, Int>? {
    val parts = s.trim().split("/", "-", ".", " ").filter { it.isNotBlank() }
    if (parts.size < 3) return null
    val d = parts[0].toIntOrNull() ?: return null
    val m = parts[1].toIntOrNull() ?: return null
    val y = parts[2].toIntOrNull() ?: return null
    if (m !in 1..12 || d !in 1..31) return null
    return Triple(d, m, y)
}

private fun parseBirthDateTrLoose(s: String): Triple<Int, Int, Int>? {

    val map = mapOf(
        "ocak" to 1, "şubat" to 2, "subat" to 2, "mart" to 3, "nisan" to 4,
        "mayıs" to 5, "mayis" to 5, "haziran" to 6, "temmuz" to 7,
        "ağustos" to 8, "agustos" to 8, "eylül" to 9, "eylul" to 9,
        "ekim" to 10, "kasım" to 11, "kasim" to 11, "aralık" to 12, "aralik" to 12
    )

    val t = s.trim().lowercase().split(" ", "-", "/", ".").filter { it.isNotBlank() }
    if (t.size < 3) return null

    val day = t[0].toIntOrNull() ?: return null
    val year = t.last().toIntOrNull() ?: return null

    val monthToken = t[1]
    val month = map[monthToken] ?: monthToken.toIntOrNull()
    if (month == null || month !in 1..12) return null

    return Triple(day, month, year)
}
