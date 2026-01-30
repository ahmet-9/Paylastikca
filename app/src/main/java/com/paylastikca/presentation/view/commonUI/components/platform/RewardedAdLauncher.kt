package com.paylastikca.presentation.view.commonUI.components.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


interface RewardedAdLauncher {
    fun show(
        onReward: () -> Unit,
        onClosed: () -> Unit,
        onFailed: () -> Unit
    )
}

@Composable
fun rememberRewardedAdLauncher(): RewardedAdLauncher {
    return remember {
        object : RewardedAdLauncher {
            override fun show(onReward: () -> Unit, onClosed: () -> Unit, onFailed: () -> Unit) {
                // TODO: AdMob Rewarded Ad burada açılacak.

                onFailed()
            }
        }
    }
}
