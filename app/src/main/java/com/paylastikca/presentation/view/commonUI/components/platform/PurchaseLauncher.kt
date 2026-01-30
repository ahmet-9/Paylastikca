package com.paylastikca.presentation.view.commonUI.components.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface PurchaseLauncher {
    fun buy(
        productId: String,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    )
}


@Composable
fun rememberPurchaseLauncher(): PurchaseLauncher {
    return remember {
        object : PurchaseLauncher {
            override fun buy(productId: String, onSuccess: () -> Unit, onFailed: () -> Unit) {
                onFailed()
            }
        }
    }
}
