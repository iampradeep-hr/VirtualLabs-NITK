package com.pradeep.virtuallabsnitk.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pradeep.virtuallabsnitk.navigation.Navigation
import com.pradeep.virtuallabsnitk.navigation.NavigationScreen
import com.pradeep.virtuallabsnitk.ui.theme.lexend
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {


    val systemUi = rememberSystemUiController()

    val pagerState = rememberPagerState()
    val list = getHorizontalPagerContent()
    systemUi.setStatusBarColor(color = list[pagerState.currentPage].backgroundColor)

    SharedPref(LocalContext.current)

    val isNextVisible = remember { derivedStateOf { pagerState.currentPage != list.size - 1 } }
    val isPrevVisible = remember { derivedStateOf { pagerState.currentPage != 0 } }
    val isLastVisible = remember {
        derivedStateOf {
            pagerState.currentPage == list.size - 1
        }
    }

    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = list[pagerState.currentPage].backgroundColor)
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)


        ) {
            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                count = list.size
            ) { currentPage ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(), horizontalAlignment = CenterHorizontally
                ) {

                    Text(
                        text = list[currentPage].title,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = lexend,
                        fontSize = 22.sp,
                        color = Color.Black.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AsyncImage(
                        model = list[currentPage].res,
                        contentDescription = null, modifier = Modifier
                            .height(270.dp)
                            .width(300.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = list[currentPage].desc,
                        softWrap = true,
                        textAlign = TextAlign.Center,
                        color = Color.Black.copy(alpha = 0.8f),
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal
                    )
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .padding(vertical = 10.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            if (isPrevVisible.value && !isLastVisible.value) {
                ElevatedButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                    Text(
                        text = "Prev",
                        fontFamily = lexend,
                        fontWeight = FontWeight.W500,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            if (isLastVisible.value) {
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    onClick = {
                        navHostController.navigate(NavigationScreen.SplashScreen.route)
                    }) {
                    Text(
                        text = "Get Started",
                        fontFamily = lexend,
                        fontWeight = FontWeight.W500,
                        fontStyle = FontStyle.Italic
                    )
                }
            }


            if (isNextVisible.value) {
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },

                    ) {
                    Text(
                        text = "Next",
                        fontFamily = lexend,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

        }

    }

}
