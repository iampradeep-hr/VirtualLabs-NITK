package com.pradeep.virtuallabsnitk.onboarding

import androidx.annotation.RawRes
import com.pradeep.virtuallabsnitk.R

data class HorizontalPagerContent(
    val title: String,
    @RawRes val res: Int,
    val desc: String
)


fun getHorizontalPagerContent():List<HorizontalPagerContent>{
    return listOf(
        HorizontalPagerContent(
            title = "Virtual Lab Experiments Enhancing Learning at NIT Surathkal",
            res = R.raw.screen1,
            desc = ""
        ),
        HorizontalPagerContent(
            title = "",
            res = R.raw.screen2,
            desc = ""
        ),
        HorizontalPagerContent(
            title = "",
            res = R.raw.screen3,
            desc = ""
        ),

    )

}
