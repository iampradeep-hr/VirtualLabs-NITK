package com.pradeep.virtuallabsnitk.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pradeep.virtuallabsnitk.R
import com.pradeep.virtuallabsnitk.navigation.NavigationScreen
import com.pradeep.virtuallabsnitk.ui.theme.lexend
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VibrationCardList(
    navController: NavHostController
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color(0xFFFFE57F))
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    )
    val coroutineScope = rememberCoroutineScope()
    val vibrationCards = listOf(
        "Free vibration of cantilever beam",
        "Free vibration of simply supported beam",
        "Free vibration of fixed beam",
        "Forced vibration of SDOF system",
        "Base Excitation",
        "Rotating Unbalance",
        "2DOF Forced vibration",
        "Dynamic Vibration Absorber"
    )
    val scrollState = rememberScrollState()

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
                    .padding(10.dp)
                    .scrollable(
                        state = scrollState,
                        orientation = Orientation.Vertical,

                        )
            ) {

                OutlinedTextField(
                    value = "", onValueChange = {

                    }, modifier = Modifier
                        .fillMaxWidth().padding(10.dp), leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    shape = MaterialTheme.shapes.small
                )

                vibrationCards.forEachIndexed { index, s ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 4.dp)
                            .clickable {
                                navController.navigate(NavigationScreen.SampleExperimentScreen.route)
                            },
                        elevation = CardDefaults.elevatedCardElevation(2.dp),
                        colors = CardDefaults.elevatedCardColors(Color(0xFF7986CB))

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()

                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${index + 1}) $s", color = Color.White)
                            Icon(Icons.Default.ArrowForward, contentDescription = null)
                        }


                    }
                }


            }


        }
    )

}
