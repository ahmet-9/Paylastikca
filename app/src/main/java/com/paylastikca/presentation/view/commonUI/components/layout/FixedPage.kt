package com.paylastikca.presentation.view.commonUI.components.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FixedPage(
    padding: PaddingValues,
    horizontalPadding: Dp = 16.dp,

    maxContentWidth: Dp? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = horizontalPadding),

        horizontalAlignment = if (maxContentWidth != null)
            Alignment.CenterHorizontally
        else
            Alignment.Start
    ) {
        if (maxContentWidth == null) {

            content()
        } else {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = maxContentWidth)
            ) {
                content()
            }
        }
    }
}
