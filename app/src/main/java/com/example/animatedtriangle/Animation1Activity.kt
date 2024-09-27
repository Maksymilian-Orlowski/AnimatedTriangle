package com.example.animatedtriangle

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



class Animation1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RotatingTriangleComponent()
        }
    }
}

@Composable
fun RotatingTriangleComponent() {
    var isAnimating by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Canvas(
                modifier = Modifier
                    .size(250.dp)
                    .clickable {
                        isAnimating = true
                    }
            ) {
                if (isAnimating) {
                    val infiniteTransition = rememberInfiniteTransition
                    val rotation by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            animation = tween<Float>(
                                durationMillis = 3000,
                                easing = FastOutLinearInEasing,
                            ),
                        ), label = ""
                    )

                    rotate(rotation) {
                        drawPath(
                            path = Path().apply {
                                moveTo(size.width / 2f, 0f)
                                lineTo(0f, size.height)
                                lineTo(size.width, size.height)
                                close()
                            },
                            color = Color.Blue
                        )
                    }
                } else {
                    drawPath(
                        path = Path().apply {
                            moveTo(size.width / 2f, 0f)
                            lineTo(0f, size.height)
                            lineTo(size.width, size.height)
                            close()
                        },
                        color = Color.Blue
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun RotatingTriangleComponentPreview() {
    RotatingTriangleComponent()
}
