package com.pradeep.virtuallabsnitk.screens



import android.graphics.Paint
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI

import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DiskTypeFlywheel() {

    var radius by remember { mutableStateOf(11) }
    var mass by remember { mutableStateOf(0.2f) }
    var loadPosition by remember { mutableStateOf(0f) }
    var isPlaying by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var blue11 = 0f;
    var blue22 = 0f;
    var rotationAngle by remember { mutableStateOf(0f) }



    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(color = Color(0xFF8C9EFF))
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {

                //constants
                val blueLineY1 = size.height * 0.3f
                val blueLineY2 = size.height - size.height * 0.1f
                val handleThickness = 30f
                blue11 = blueLineY1
                blue22 = blueLineY2
                val diskCenterX = size.width / 3.5f
                val diskCenterY = blueLineY1 + handleThickness / 2


                drawLine(
                    color = Color.Blue,
                    start = Offset(x = size.width * 0.9f, y = blueLineY1),
                    end = Offset(x = size.width * 0.9f, y = blueLineY2),
                    strokeWidth = 10f
                )
                //base line
                drawLine(
                    color = Color.Black,
                    start = Offset(x = size.width * 0.1f, y = size.height * 0.9f),
                    end = Offset(x = size.width * 0.9f, y = blueLineY2),
                    strokeWidth = 3f
                )

                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(x = size.width * 0.6f, y = blueLineY1),
                    size = Size(width = (size.width / 3) - 80f, height = 30f)
                )

                drawCircle(
                    color = Color(0xFF9BD0CB),
                    radius = radius.toFloat() * 12f,
                    center = Offset(x = diskCenterX, y = diskCenterY),
                )



                drawCircle(
                    color = Color.Gray,
                    radius = 20f,
                    center = Offset(x = diskCenterX, y = diskCenterY),
                )

                drawRect(
                    color = Color(0xFF9BD0CB),
                    topLeft = Offset(
                        x = size.width * 0.7f,
                        y = (diskCenterY - (radius.toFloat() * 12f) - handleThickness + 30f)
                    ),
                    size = Size(width = 50f, height = 2 * radius.toFloat() * 12f)
                )
                drawLine(
                    color = Color.Gray,
                    start = Offset(x = diskCenterX, y = diskCenterY - radius.toFloat() * 12f),
                    end = Offset(
                        x = size.width * 0.7f + 30f,
                        y = (diskCenterY - (radius.toFloat() * 12f))
                    ),
                    strokeWidth = 8f
                )
                drawLine(
                    color = Color.Gray,
                    start = Offset(x = diskCenterX, y = diskCenterY + radius.toFloat() * 12f),
                    end = Offset(
                        x = size.width * 0.7f + 30f,
                        y = (diskCenterY + (radius.toFloat() * 12f))
                    ),
                    strokeWidth = 8f
                )

                // Draw disk rotation line
                val lineStart = Offset(diskCenterX, diskCenterY)
                val lineEnd = Offset(
                    diskCenterX + (radius.toFloat() * 12f * cos(rotationAngle)),
                    diskCenterY + (radius.toFloat() * 12f * sin(rotationAngle))
                )
                drawLine(
                    color = Color.DarkGray,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = 10f, cap = StrokeCap.Round
                )

                // Draw the rotating disk
                drawCircle(
                    color = Color(0xFF9BD0CB),
                    radius = radius.toFloat() * 12f,
                    center = Offset(diskCenterX, diskCenterY),
                    style = Stroke(width = 5f)
                )

                // Update rotation angle
                if (isPlaying && loadPosition < (blue22 - blue11 - 100f)) {
                    rotationAngle += 0.05f
                }

                // Update load position based on falling time
                val loadX = size.width * 0.8f
                val loadY = diskCenterY + 80f + loadPosition
                drawLine(
                    color = Color.Black,
                    strokeWidth = 7f,
                    start = Offset(x = size.width * 0.8f, y = blueLineY1),
                    end = Offset(x = size.width * 0.8f, y = loadY)
                )
                drawCircle(
                    color = Color(0xFFFF9E80),
                    radius = mass * 80f,
                    center = Offset(x = loadX, y = loadY)
                )

                val text = "Height 2m"
                val textOffset = Offset(x = size.width * 0.93f, y = (blueLineY2 - blueLineY1) / 2)
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.rotate(90f, textOffset.x, textOffset.y)
                    canvas.nativeCanvas.drawText(
                        text,
                        textOffset.x,
                        textOffset.y,
                        Paint().apply {
                            color = Color.Red.toArgb()
                            textSize = 40f
                        }
                    )
                }


            }

        }





        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFCCFF90))
                .padding(8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(text = "Variables", fontSize = 20.sp, fontWeight = FontWeight.W600)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SliderWithTextBox(
                    sliderValue = radius.toFloat(),
                    onSliderValueChange = { newValue ->
                        radius = newValue.toInt()
                    },
                    valueRange = 11f..18f,
                    textValue = radius.toString(),
                    headingText = "R (cm)"
                )
                SliderWithTextBox(
                    sliderValue = mass,
                    onSliderValueChange = { newValue ->
                        mass = newValue
                    },
                    valueRange = 0.2f..0.7f,
                    textValue = mass.toString(),
                    headingText = "m (kg)"
                )

                Button(onClick = {
                    isPlaying = !isPlaying
                    coroutineScope.launch {
                        if (isPlaying) {
                            while (loadPosition < (blue22 - blue11 - 100f)) {
                                loadPosition += 5f
                                delay(16)
                            }
                        }
                    }

                }) {
                    Text(text = if (isPlaying) "Pause" else "Play")
                }
                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        // Reset values to initial state
                        radius = 11
                        mass = 0.2f
                        loadPosition = 0f
                        isPlaying = false
                    }
                ) {
                    Text(text = "Reset")
                }


            }
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderWithTextBox(
    valueRange: ClosedFloatingPointRange<Float>,
    sliderValue: Float,
    textValue: String,
    onSliderValueChange: (newValue: Float) -> Unit,
    headingText: String,
) {
    Column(modifier = Modifier.padding(5.dp)) {
        Slider(
            value = sliderValue,
            onValueChange = {
                onSliderValueChange(it)
            },
            valueRange = valueRange,
            )
        Text(
            text = "$headingText: $textValue",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
    }
}