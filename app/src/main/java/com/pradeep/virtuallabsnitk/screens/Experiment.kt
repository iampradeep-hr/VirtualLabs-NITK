package com.pradeep.virtuallabsnitk.screens

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pradeep.virtuallabsnitk.R
import com.pradeep.virtuallabsnitk.ui.theme.lexend
import kotlinx.coroutines.launch

data class DrawerItem(val icon: ImageVector, val text: String, val destination: String)

val drawerItems = listOf(
    DrawerItem(Icons.Default.FavoriteBorder, "Aim", "aim"),
    DrawerItem(Icons.Default.Info, "Theory", "theory"),
    DrawerItem(Icons.Default.Menu, "Procedure", "procedure"),
//    DrawerItem(Icons.Default.CheckCircle, "Self Evaluation", "self_evaluation"),
    DrawerItem(Icons.Default.PlayArrow, "Simulation", "simulation"),
//    DrawerItem(Icons.Default.AccountBox, "Assignment", "assignment"),
//    DrawerItem(Icons.Default.MailOutline, "Quiz", "quiz"),
//    DrawerItem(Icons.Default.PlayArrow, "Videos", "videos"),
//    DrawerItem(Icons.Default.Create, "References", "references"),
//    DrawerItem(Icons.Default.Person, "Feedback", "feedback")
)

@Composable
fun DrawerContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        drawerItems.forEach { drawerItem ->
            DrawerItem(
                icon = drawerItem.icon,
                text = drawerItem.text,
                onClick = { navController.navigate(drawerItem.destination) }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    // Replace this with your custom drawer item composable
    OutlinedButton(
        onClick = {
            onClick()
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text, fontFamily = lexend)
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SampleExperiment() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(0xFFFFE57F))
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    )
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    AsyncImage(
                        model = R.drawable.virtual_labs,
                        contentDescription = null,
                        modifier = Modifier
                            .height(250.dp)
                            .width(280.dp)
                            .scale(3.5f)
                            .padding(8.dp)
                    )
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = Color.Black
                        )
                    }
                },

                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.right),
                            contentDescription = "Dropdown",
                            tint = Color.Black
                        )
                    }
                },
                backgroundColor = Color(0xFFFFE57F),
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                DrawerContent(navController)
            }
        },
        content = {
            NavHost(navController, startDestination = drawerItems.first().destination) {
                drawerItems.forEach { drawerItem ->
                    composable(drawerItem.destination) {
                        when (drawerItem.destination) {
                            "aim" -> AimScreen()
                            "theory" -> TheoryScreen()
                            "procedure" -> ProcedureScreen()
//                            "self_evaluation" -> SelfEvaluationScreen()
                            "simulation" -> DiskTypeFlywheel()
//                            "assignment" -> AssignmentScreen()
//                            "quiz" -> QuizScreen()
//                            "videos" -> VideosScreen()
//                            "references" -> ReferencesScreen()
//                            "feedback" -> FeedbackScreen()
                            else -> Text("Invalid destination")
                        }
                    }
                }
            }
        })
}


@Composable
fun AimScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Text(
            text = "Free vibration of cantilever beam",
            softWrap = true,
            fontFamily = lexend,
            fontSize = 25.sp,
            textAlign = TextAlign.Center

        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color(0xFFFFD180))
        ) {
            Text(
                text = "The purpose of the experiment is to determine the logarithmic decrement, " +
                        "damping ratio, damping frequency and natural frequency of a cantilever beam under free vibration",
                softWrap = true,
                fontFamily = lexend,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )

        }
    }
}

@Composable
fun TheoryScreen() {
    val text1 = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Learning Objectives")
        }
        append("\n\n")
        bulletPoint("Model a given real system to an equivalent simplified model of a cantilever beam with suitable assumptions / idealizations.")
        bulletPoint("Calculate the logarithmic decrement, damping ratio, damping frequency, and natural frequency of the system.")
        bulletPoint("Find the stiffness and the critical damping of the system.")
        bulletPoint("Calculate the damping coefficient of the system.")
    }

    val text2 = buildAnnotatedString {

        append("\n\n")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Introduction")
        }
        append("\n\n")
        append("A system is said to be a cantilever beam system if one end of the system is rigidly fixed to a support and the other end is free to move. A real system is shown below, try to make suitable assumptions to deduce the system to a cantilever beam.")
        // Add the image here if needed
    }

    val text3 = buildAnnotatedString {

        append("\n\n")
        append("Vibration analysis of a cantilever beam system is important as it can explain and help us analyze a number of real-life systems. As shown in the above examples, real systems can be simplified to a cantilever beam, thereby helping us make design changes accordingly for the most efficient systems.")

        append("\n\n")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Natural Frequency of Cantilever Beam")
        }
        append("\n\n")
        append("When given an excitation and left to vibrate on its own, the frequency at which a cantilever beam will oscillate is its natural frequency. This condition is called Free vibration. The value of the natural frequency depends only on system parameters of mass and stiffness. When a real system is approximated to a simple cantilever beam, some assumptions are made for modeling and analysis (Important assumptions for undamped system are given below):\n\n")
        bulletPoint("The mass (m) of the whole system is considered to be lumped at the free end of the beam")
        bulletPoint("No energy-consuming element (damping) is present in the system i.e. undamped vibration")
        bulletPoint("The complex cross-section and type of material of the real system have been simplified to equate to a Cantilever beam")
        append("\n\n")
        append("The governing equation for such a system (spring mass system without damping under free vibration) is as below:\n\n")

    }
    val text4 = buildAnnotatedString {
        append("k the stiffness of the system is a property which depends on the length (l), moment of inertia (I), and Young's Modulus (E) of the material of the beam and for a cantilever beam is given by:\n\n")


    }
    val text5 = buildAnnotatedString {
        append("Damping in a Cantilever Beam")
        append("\n\n")
        append("Although there is no visible damper (dashpot), the real system has some amount of damping present in it. When a system with damping undergoes free vibration, the damping property must also be considered for the modeling and analysis.\n\n")
        append("Single degree of freedom mass spring damper system under free vibration is governed by the following differential equation:\n\n")

    }
    val text6 = buildAnnotatedString {
        append("c is the damping present in the system and ζ is the damping factor of the system, which is nothing but the ratio of damping c and critical damping cc. Critical damping can be seen as the damping just sufficient to avoid oscillations. At the critical condition ζ=1. For real systems, the value of ζ is less than 1. For a system where ζ < 1, the differential equation solution is a pair of complex conjugates. The displacement solution is given by\n\n")

    }
    val text7 = buildAnnotatedString {
        append("where x0 and v0 are initial displacement and velocity, and ωd is the damped natural frequency of the system. The damped natural frequency is calculated as below:\n\n")

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text(
            text1,

            )
        AsyncImage(model = R.drawable.canti1, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Text(
            text2,

            )
        AsyncImage(model = R.drawable.canti2, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Text(
            text3,

            )
        AsyncImage(model = R.drawable.canti3, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Text(
            text4,

            )
        AsyncImage(model = R.drawable.canti4, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Text(
            text5,

            )
        AsyncImage(model = R.drawable.canti5, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Text(
                text6,

        )
        AsyncImage(model = R.drawable.canti6, contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .height(150.dp))
        Text(
                text7,

        )

    }


}

@Composable
private fun AnnotatedString.Builder.bulletPoint(text: String) {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append("\u2022 ")
    }
    append(text)
}


@Composable
fun ProcedureScreen() {
    Text(text = "Yet to be Implemented. Please ignore the minor UI design issues")
}