package com.pradeep.virtuallabsnitk.screens

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pradeep.virtuallabsnitk.R
import com.pradeep.virtuallabsnitk.navigation.NavigationScreen
import com.pradeep.virtuallabsnitk.ui.theme.lexend
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(0xFFFFE57F))
    val scaffoldState = rememberScaffoldState(
        drawerState = androidx.compose.material.rememberDrawerState(initialValue = DrawerValue.Closed),
    )
    val coroutineScope = rememberCoroutineScope()

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
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
                    }
                },
                backgroundColor = Color(0xFFFFE57F),
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        drawerContent = {
            Text(text = "Navigation Drawer")
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))

            ) {
                val cardBody1 = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append("'For the things we have to learn before we can do them, we learn by doing them.' - Aristotle\n")
                    }
                    withStyle(style = SpanStyle()) {
                        append("\u2022 Welcome to Machine Dynamics & Mechanical Vibrations Lab!!")
                        append("\u2022 Click on 'Experiments' to see the list of experiments available. Feel free to explore the lab.\n")
                    }

                }

                val cardBody2 = buildAnnotatedString {
                    withStyle(style = ParagraphStyle()) {
                        append("UG")
                    }
                    withStyle(style = SpanStyle()) {
                        append("\u2022 B.E / B.Tech in Civil Engineering or Mechanical Engineering\n")
                        append("\u2022 Diploma in Civil Engineering or Mechanical Engineering\n")
                    }
                    withStyle(style = ParagraphStyle()) {
                        append("PG")
                    }
                    withStyle(style = SpanStyle()) {
                        append("\u2022 M.Tech / MS in Water Resource Engineering or Environmental Engineering or Coastal Engineering\n")
                    }
                }

                val title = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append("Virtual Labs At\n")
                    }
                    withStyle(
                        SpanStyle(
                            color = Color.Blue.copy(alpha = 0.6f)
                        )
                    ) {
                        append("NITK Surathkal")
                    }
                }
                Text(
                    text = title,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(8.dp)
                )
                SubjectCard(
                    bgImage = R.drawable.machine3,
                    title = "Machine Dynamics and Mechanical Vibrations Lab",
                    body = cardBody1,
                    navController
                )
                SubjectCard(
                    bgImage = R.drawable.fluid,
                    title = "Fluid Mechanics Lab",
                    body = cardBody2,
                    navController
                )

                Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(color = Color.Black.copy(alpha = 0.8f))
                            .padding(10.dp).align(Alignment.BottomCenter)) {
                            Text(text = "Contact Us", fontFamily = lexend, fontSize = 18.sp, color = Color.White)
                            Text(
                                text = "Phone: General Information: 011-26582050\n" +
                                        "Email: support@vlabs.ac.in",
                                fontFamily = lexend,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 15.sp
                            )
                        }
                    }



            }


        }
    )


}


@Composable
fun SubjectCard(
    @DrawableRes bgImage: Int,
    title: String,
    body: AnnotatedString,
    navController: NavHostController
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = bgImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 1.dp, BlurredEdgeTreatment.Unbounded)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontFamily = lexend,
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )

                    FilledIconButton(
                        onClick = {
                            navController.navigate(NavigationScreen.MachineDynamics.route)

                        },
                        colors = IconButtonDefaults.iconButtonColors(Color.White)
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Black,

                            )
                    }
                }


                Text(text = body, fontSize = 10.sp, color = Color.White, softWrap = true)

            }
        }

    }
}
