package com.pradeep.virtuallabsnitk.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.ui.graphics.Color
import com.pradeep.virtuallabsnitk.R

data class HorizontalPagerContent(
    val title: String,
    @DrawableRes val res: Int,
    val desc: String,
    val backgroundColor: Color
)


fun getHorizontalPagerContent(): List<HorizontalPagerContent> {
    return listOf(
        HorizontalPagerContent(
            title = "Virtual Lab Experiments Enhancing Learning at NITK Surathkal",
            res = R.drawable.virtual_labs,
            backgroundColor = Color(0xFFFFFFFF),
            desc = "An Initiative of Ministry of Education Under the National Mission on Education through ICT"
        ),
        HorizontalPagerContent(
            title = "Comprehensive Lab Simulations",
            res = R.drawable.lab_simulation,
            backgroundColor = Color(0xFFFF9800).copy(alpha = 0.5f),
            desc = "Access a wide range of virtual lab simulations that accurately replicate the real-life laboratory environment. Conduct experiments, manipulate variables, and observe results, all within the app. No more limitations due to time, equipment availability, or safety concerns."
        ),
        HorizontalPagerContent(
            title = "Interactive Learning Modules",
            res = R.drawable.interactive,
            backgroundColor = Color(0xFFB2FF59).copy(alpha = 0.5f),
            desc = "Dive into interactive learning modules designed to enhance your understanding of key scientific concepts. Engage in self-paced learning through videos, animations, quizzes, and informative text, all crafted by subject matter experts from NIT Surathkal."
        ),
        HorizontalPagerContent(
            title = "Experiment Customization",
            res = R.drawable.customization,
            backgroundColor = Color(0xFF673AB7).copy(alpha = 0.5f),
            desc="Tailor your virtual lab experiments to meet specific requirements or explore variations of experiments. Modify variables, adjust parameters, and observe the impact of your changes in real-time. This empowers you to develop a deeper understanding of scientific principles and experimental design."
        ),
        HorizontalPagerContent(
            title = "24/7 Access",
            res = R.drawable.anytime,
            backgroundColor = Color(0xFF82B1FF),
            desc = "Enjoy the flexibility of accessing the Virtual Lab Experiments app anytime, anywhere, at your convenience. Whether you're studying in the library, relaxing in your hostel, or commuting, you can engage in immersive laboratory experiences whenever suits you best."
        ),
        HorizontalPagerContent(
            title = "Welcome",
            res = R.drawable.nitk_logo,
            backgroundColor = Color(0xFF69F0AE),
            desc = "Take your laboratory learning to the next level with the Virtual Lab Experiments app tailored specifically for NIT Surathkal students. Embrace the power of technology and embark on a journey of discovery and scientific exploration like never before. Download the app now and unlock a world of limitless possibilities!"
        ),

        )

}
