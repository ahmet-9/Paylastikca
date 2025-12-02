package com.paylastikca.presentation.view.homepageScreen.giftWheel



import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlin.math.sign
import kotlin.random.Random


data class StarData(
    val x: Float,
    val y: Float,
    val radius: Float,
    val alpha: Float
)

@Composable
fun GiftWheelScreen() {



    val scope = rememberCoroutineScope()

    var wheelRotation by remember { mutableStateOf(0f) }
    var angularVelocity by remember { mutableStateOf(0f) }
    var center by remember { mutableStateOf(Offset.Zero) }
    var isSpinning by remember { mutableStateOf(false) }

    val needleAngle = remember { Animatable(0f) }

    val sliceCount = 8
    val dotCount = 36
    val sliceColors = listOf(
        Color(0xFFF44336),
        Color(0xFFFFC107),
        Color(0xFF4CAF50),
        Color(0xFF03A9F4),
        Color(0xFF9C27B0),
        Color(0xFFFF9800),
        Color(0xFF009688),
        Color(0xFF3F51B5)
    )

    val labels = listOf(
        "5 Çip",
        "1 Çip",
        "1 Reklam İzle",
        "3 Reklam İzle",
        "2 Çip",
        "4 Çip",
        "10 Çip",
        "15 Çip"
    )

    var lastStep by remember { mutableStateOf(0) }


    val stars = remember {
        List(40) {
            val x = Random.nextFloat()
            val y = Random.nextFloat()
            val radius = Random.nextInt(14, 32).toFloat()
            val alpha = Random.nextFloat() * 0.4f + 0.3f
            StarData(x, y, radius, alpha)
        }
    }


    LaunchedEffect(isSpinning) {
        while (isSpinning && isActive) {
            wheelRotation += angularVelocity
            angularVelocity *= 0.985f
            if (kotlin.math.abs(angularVelocity) < 0.1f) {
                isSpinning = false
                angularVelocity = 0f
                needleAngle.animateTo(0f)
            }
            delay(16L)
        }
    }


    LaunchedEffect(wheelRotation) {
        val step = (wheelRotation / (360f / dotCount)).toInt()
        if (step != lastStep) {
            lastStep = step
            val dir = angularVelocity.sign
            scope.launch {
                needleAngle.animateTo(-18f * dir, tween(40))
                needleAngle.animateTo(-6f * dir, tween(40))
                needleAngle.animateTo(0f, tween(40))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            val starColor = Color(0xFFFF9800)
            stars.forEach { star ->
                val x = star.x * size.width
                val y = star.y * size.height
                val radius = star.radius
                val alpha = star.alpha


                drawLine(
                    color = starColor.copy(alpha = alpha),
                    start = Offset(x - radius, y),
                    end = Offset(x + radius, y),
                    strokeWidth = 2f
                )

                drawLine(
                    color = starColor.copy(alpha = alpha),
                    start = Offset(x, y - radius),
                    end = Offset(x, y + radius),
                    strokeWidth = 2f
                )

                drawLine(
                    color = starColor.copy(alpha = alpha),
                    start = Offset(x - radius * 0.7f, y - radius * 0.7f),
                    end = Offset(x + radius * 0.7f, y + radius * 0.7f),
                    strokeWidth = 2f
                )

                drawLine(
                    color = starColor.copy(alpha = alpha),
                    start = Offset(x - radius * 0.7f, y + radius * 0.7f),
                    end = Offset(x + radius * 0.7f, y - radius * 0.7f),
                    strokeWidth = 2f
                )
            }
        }

        Box(contentAlignment = Alignment.Center) {
            // wheell
            Canvas(
                modifier = Modifier
                    .size(300.dp)
                    .rotate(wheelRotation)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (!isSpinning) {
                                    isSpinning = true
                                }
                            },
                            onDrag = { change, _ ->
                                val touchX = change.position.x - center.x
                                val touchY = change.position.y - center.y
                                val angle = atan2(touchY, touchX) * (180f / Math.PI.toFloat())

                                val diff = angle - wheelRotation
                                val direction = if (diff >= 0) 1 else -1
                                var velocity = kotlin.math.abs(diff) * 2f * direction

                                if (kotlin.math.abs(velocity) < 20f) {
                                    velocity = 20f * direction
                                }
                                angularVelocity = velocity.coerceIn(-60f, 60f)
                                wheelRotation = angle
                            }
                        )
                    }
            ) {
                val sweep = 360f / sliceCount
                val radius = size.minDimension / 2
                center = Offset(size.width / 2, size.height / 2)

                // frame
                drawCircle(
                    color = Color(0xFFFFD700),
                    radius = radius,
                    style = Stroke(width = 30f)
                )


                repeat(sliceCount) { i ->
                    rotate(i * sweep) {
                        drawArc(
                            color = sliceColors[i % sliceColors.size],
                            startAngle = 0f,
                            sweepAngle = sweep,
                            useCenter = true
                        )
                        drawIntoCanvas { canvas ->
                            val paint = android.graphics.Paint().apply {
                                color = Color.White.toArgb()
                                textSize = 32f
                                textAlign = android.graphics.Paint.Align.CENTER
                                isFakeBoldText = true
                            }
                            val angleDeg = sweep / 2f
                            val angleRad = Math.toRadians(angleDeg.toDouble())
                            val textX = center.x + (radius / 1.8f) * cos(angleRad).toFloat()
                            val textY = center.y + (radius / 1.8f) * sin(angleRad).toFloat()
                            canvas.nativeCanvas.save()
                            canvas.nativeCanvas.rotate(angleDeg, textX, textY)
                            canvas.nativeCanvas.drawText(labels[i], textX, textY, paint)
                            canvas.nativeCanvas.restore()
                        }
                    }
                }

                // points
                repeat(dotCount) { i ->
                    val angle = (360f / dotCount) * i
                    val rad = Math.toRadians(angle.toDouble())
                    val x = (radius * cos(rad)).toFloat() + center.x
                    val y = (radius * sin(rad)).toFloat() + center.y
                    drawCircle(Color.White, radius = 6f, center = Offset(x, y))
                }
            }

            // star
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFD700)),
                contentAlignment = Alignment.Center
            ) {
                Text("★", color = Color.White, fontSize = 36.sp, modifier = Modifier.offset(y = (-4).dp))
            }

            // pointer
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-5).dp)
            ) {
                Canvas(
                    modifier = Modifier.size(18.dp, 28.dp)
                ) {
                    rotate(needleAngle.value, pivot = Offset(size.width / 2, 6f)) {
                        drawRoundRect(
                            color = Color(0xFFFFFFFF),
                            topLeft = Offset(size.width / 2 - 4f, 6f),
                            size = Size(12f, size.height - 12f),
                            cornerRadius = CornerRadius(6f, 6f)
                        )
                        drawRoundRect(
                            color = Color(0xFFFFFFFF),
                            topLeft = Offset(size.width / 2 - 9f, size.height - 10f),
                            size = Size(24f, 8f),
                            cornerRadius = CornerRadius(4f, 4f)
                        )
                    }
                    drawCircle(
                        color = Color(0xFFC28840),
                        radius = 6f,
                        center = Offset(size.width / 2, 6f)
                    )
                }
            }
        }
    }
}



 /* ******(spare)********

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlin.math.sign

@Composable
fun GiftWheelScreen() {
    val scope = rememberCoroutineScope()

    var wheelRotation by remember { mutableStateOf(0f) }
    var angularVelocity by remember { mutableStateOf(0f) }
    var center by remember { mutableStateOf(Offset.Zero) }
    var isSpinning by remember { mutableStateOf(false) }

    val needleAngle = remember { Animatable(0f) }

    val sliceCount = 8
    val dotCount = 36
    val sliceColors = listOf(
        Color(0xFFF44336),
        Color(0xFFFFC107),
        Color(0xFF4CAF50),
        Color(0xFF03A9F4),
        Color(0xFF9C27B0),
        Color(0xFFFF9800),
        Color(0xFF009688),
        Color(0xFF3F51B5)
    )

    val labels = listOf(
        "5 Çip",
        "1 Çip",
        "1 Reklam İzle",
        "3 Reklam İzle",
        "2 Çip",
        "4 Çip",
        "10 Çip",
        "15 Çip"
    )

    var lastStep by remember { mutableStateOf(0) }

    // physics simulation
    LaunchedEffect(isSpinning) {
        while (isSpinning && isActive) {
            wheelRotation += angularVelocity
            angularVelocity *= 0.985f
            if (kotlin.math.abs(angularVelocity) < 0.1f) {
                isSpinning = false
                angularVelocity = 0f
                needleAngle.animateTo(0f)
            }
            delay(16L)
        }
    }


    LaunchedEffect(wheelRotation) {
        val step = (wheelRotation / (360f / dotCount)).toInt()
        if (step != lastStep) {
            lastStep = step
            val dir = angularVelocity.sign
            scope.launch {
                needleAngle.animateTo(-18f * dir, tween(40))
                needleAngle.animateTo(-6f * dir, tween(40))
                needleAngle.animateTo(0f, tween(40))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            // wheel
            Canvas(
                modifier = Modifier
                    .size(300.dp)
                    .rotate(wheelRotation)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (!isSpinning) {
                                    isSpinning = true
                                }
                            },
                            onDrag = { change, _ ->
                                val touchX = change.position.x - center.x
                                val touchY = change.position.y - center.y
                                val angle = atan2(touchY, touchX) * (180f / Math.PI.toFloat())

                                val diff = angle - wheelRotation
                                val direction = if (diff >= 0) 1 else -1
                                var velocity = kotlin.math.abs(diff) * 2f * direction

                                if (kotlin.math.abs(velocity) < 20f) {
                                    velocity = 20f * direction
                                }
                                angularVelocity = velocity.coerceIn(-60f, 60f)
                                wheelRotation = angle
                            }
                        )
                    }
            ) {
                val sweep = 360f / sliceCount
                val radius = size.minDimension / 2
                center = Offset(size.width / 2, size.height / 2)

                // frame
                drawCircle(
                    color = Color(0xFFFFD700),
                    radius = radius,
                    style = Stroke(width = 30f)
                )


                repeat(sliceCount) { i ->
                    rotate(i * sweep) {
                        drawArc(
                            color = sliceColors[i % sliceColors.size],
                            startAngle = 0f,
                            sweepAngle = sweep,
                            useCenter = true
                        )
                        drawIntoCanvas { canvas ->
                            val paint = android.graphics.Paint().apply {
                                color = Color.White.toArgb()
                                textSize = 32f
                                textAlign = android.graphics.Paint.Align.CENTER
                                isFakeBoldText = true
                            }
                            val angleDeg = sweep / 2f
                            val angleRad = Math.toRadians(angleDeg.toDouble())
                            val textX = center.x + (radius / 1.8f) * cos(angleRad).toFloat()
                            val textY = center.y + (radius / 1.8f) * sin(angleRad).toFloat()
                            canvas.nativeCanvas.save()
                            canvas.nativeCanvas.rotate(angleDeg, textX, textY)
                            canvas.nativeCanvas.drawText(labels[i], textX, textY, paint)
                            canvas.nativeCanvas.restore()
                        }
                    }
                }

                // points
                repeat(dotCount) { i ->
                    val angle = (360f / dotCount) * i
                    val rad = Math.toRadians(angle.toDouble())
                    val x = (radius * cos(rad)).toFloat() + center.x
                    val y = (radius * sin(rad)).toFloat() + center.y
                    drawCircle(Color.White, radius = 6f, center = Offset(x, y))
                }
            }

            // star
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFD700)),
                contentAlignment = Alignment.Center
            ) {
                Text("★", color = Color.White, fontSize = 36.sp, modifier = Modifier.offset(y = (-4).dp))
            }

            // pointer
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-5).dp)
            ) {
                Canvas(
                    modifier = Modifier.size(18.dp, 28.dp)
                ) {
                    rotate(needleAngle.value, pivot = Offset(size.width / 2, 6f)) {
                        drawRoundRect(
                            color = Color(0xFFFFFFFF),
                            topLeft = Offset(size.width / 2 - 4f, 6f),
                            size = Size(12f, size.height - 12f),
                            cornerRadius = CornerRadius(6f, 6f)
                        )
                        drawRoundRect(
                            color = Color(0xFFFFFFFF),
                            topLeft = Offset(size.width / 2 - 9f, size.height - 10f),
                            size = Size(24f, 8f),
                            cornerRadius = CornerRadius(4f, 4f)
                        )
                    }
                    drawCircle(
                        color = Color(0xFFC28840),
                        radius = 6f,
                        center = Offset(size.width / 2, 6f)
                    )
                }
            }
        }
    }
}

******(spare)******** */








