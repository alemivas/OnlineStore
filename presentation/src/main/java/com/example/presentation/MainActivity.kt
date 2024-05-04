package com.example.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.theme.OnlineStoreTheme
import com.example.presentation.main_screen.MainScreen
import com.example.presentation.onboarding_screen.text
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.Text

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


//    var pref : SharedPreferences? = null
//    var pref = getSharedPreferences("MY_APP_PREFERENCES", Context.MODE_PRIVATE)
//    val pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)

    var first = true
    var sharedPreferences: SharedPreferences? = null
//    var sharedEditor: SharedPreferences.Editor? = null

//    sharedPreferences = getSharedPreferences("MY_APP_PREFERENCES", Context.MODE_PRIVATE)

    first = sharedPreferences?.getBoolean("isFirst", true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlineStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MainScreen()
//                    sharedPreferences = getPreferences(MODE_PRIVATE)
//                    sharedEditor = sharedPreferences!!.edit()

//                    Test(first/*, pref*/)

                    Column(

                    ) {
                        Text (text =
                            if (first/*true*/){
                                "first"
                            }else{
                                "NO first"
                            }
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Test(first:Boolean/*, pref: SharedPreferences*/){

    Column(

    ) {
        Text (text =
            if (first){
                "first"
            }else{
                "NO first"
            }
        )
    }

//    val editor = pref.edit()
//    editor?.putBoolean("isFirst", false)
//    editor?.apply()
}