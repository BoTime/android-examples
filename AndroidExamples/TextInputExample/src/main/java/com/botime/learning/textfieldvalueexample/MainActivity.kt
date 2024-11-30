package com.botime.learning.textfieldvalueexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.botime.learning.mynavigation.MyApp
import com.botime.learning.mynavigation.Destination
import com.botime.learning.textfieldvalueexample.ui.screen.DpAndSpComparision
import com.botime.learning.textfieldvalueexample.ui.screen.DpAndSpComparisonScreen
import com.botime.learning.textfieldvalueexample.ui.screen.TextInput
import com.botime.learning.textfieldvalueexample.ui.screen.TextInputScreen
import com.botime.learning.textfieldvalueexample.ui.theme.TextFieldValueExampleTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TextFieldValueExampleTheme {
                Scaffold { innerPadding ->
                    val destinationMapping: Map<Destination, @Composable() () -> Unit> = mapOf(
                        TextInput() to { TextInputScreen(innerPadding) },
                        DpAndSpComparision() to { DpAndSpComparisonScreen(innerPadding) }
                    )
                    MyApp(TextInput(), destinationMapping)
                }
            }
        }
    }
}

