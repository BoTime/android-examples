package com.botime.learning.changeicon

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.botime.learning.changeicon.ui.theme.ChangeIconTheme

/**
 * Reference: https://oguzhanaslann.medium.com/dynamic-app-icon-in-android-a61f8570ab9f
 */
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChangeIconTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(innerPadding)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun App(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val componentList = listOf(
        ComponentName(
            context,
            "${context.packageName}.MainActivityAliasThanksgiving"
        ),
        ComponentName(
            context,
            "${context.packageName}.MainActivity"
        ),
    )
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Select an icon", fontWeight = FontWeight.Bold, fontSize = 40.sp)
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_thanksgiving_round),
                contentDescription = "Thanksgiving Icon",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(Color(0x00000000))
                    .clickable {
                        changeIcon(
                            context, componentList[0], componentList
                        )
                    }
            )
            Image(
                painter = painterResource(R.drawable.ic_launcher_round),
                contentDescription = "Boring Icon",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(Color(0x00000000))
                    .clickable {
                        changeIcon(
                            context, componentList[1], componentList
                        )
                    }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun changeIcon(context: Context, enableComponent: ComponentName, components: List<ComponentName>) {
    for (componentName in components) {
        if (componentName == enableComponent) {
            context.packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        } else {
            context.packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }

    Log.i("===", "package name: ${context.packageName}")
    Toast
        .makeText(context, "Switching icons", Toast.LENGTH_LONG)
        .show()
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChangeIconTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            App(innerPadding)
        }
    }
}