package com.pradeep.virtuallabsnitk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pradeep.virtuallabsnitk.R

val lexend= FontFamily(
    Font(R.font.lexend_bold),
    Font(R.font.lexend_extrabold),
    Font(R.font.lexend_light),
    Font(R.font.lexend_medium),
    Font(R.font.lexend_extralight),
    Font(R.font.lexend_regular),
    Font(R.font.lexend_semibold),
    Font(R.font.lexend_thin)

)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall =TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),


)