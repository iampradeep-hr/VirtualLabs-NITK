package com.pradeep.virtuallabsnitk.onboarding

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun OnBoardingScreen() {

    val pagerState = rememberPagerState()
    val list = getHorizontalPagerContent()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(count = list.size, state = pagerState) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = list[currentPage].title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        softWrap = true,
                        textAlign = TextAlign.Center,

                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    LottieLoader(res = list[currentPage].res)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = list[currentPage].desc,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.W500,
                        softWrap = true
                    )
                }
            }
            HorizontalPagerIndicator(pagerState = pagerState)
        }
    }


}

@Composable
fun LottieLoader(
    @RawRes res: Int
) {
    val composition by
    rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), contentAlignment = Alignment.Center
    ) {
        LottieAnimation(composition, iterations = LottieConstants.IterateForever)
    }


}

