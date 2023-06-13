package com.pradeep.virtuallabsnitk.onboarding

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import com.pradeep.virtuallabsnitk.utils.Constants

class SharedPref(val context: Context) {


    val sharedPref =
       context.getSharedPreferences(Constants.sharedPrefKey, ComponentActivity.MODE_PRIVATE)

    fun setFirstTimeUserToFalse(){
        sharedPref.edit().apply{
            this.putBoolean(
                Constants.firstTimeUser,
                false
            ).apply()
        }
    }

    fun getFirstTimeUser():Boolean{
       return sharedPref.getBoolean(Constants.firstTimeUser,true)
    }
}