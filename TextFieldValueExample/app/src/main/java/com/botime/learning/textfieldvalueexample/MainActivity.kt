package com.botime.learning.textfieldvalueexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.botime.learning.textfieldvalueexample.ui.theme.TextFieldValueExampleTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TextFieldValueExampleTheme {
                Scaffold { innerPadding ->
                    Screen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Screen(innerPadding: PaddingValues) {
    val rainbowColors = listOf(
        Color(0xFFFF0000),
        Color(0xFFFF7F00),
        Color(0xFFFFFF00),
        Color(0xFF00FF00),
        Color(0xFF0000FF),
        Color(0xFF4B0082),
        Color(0xFF9400D3)
    )

    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .padding(innerPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Example(
            title = "Example 1: Basic",
            backgroundColor = rainbowColors[0],
            customization = {
                var text by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Enter some text") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            })

        Example(
            title = "Example 2: Password",
            backgroundColor = rainbowColors[1],
            customization = {
                var password by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Enter password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            })

        Example(
            title = "Example 3: Email validation",
            backgroundColor = rainbowColors[2],
            customization = {
                var email by rememberSaveable { mutableStateOf("") }
                val emailHasErrors by remember {
                    derivedStateOf {
                        if (email.isNotEmpty()) {
                            // Email is considered erroneous until it completely matches EMAIL_ADDRESS.
                            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        } else {
                            false
                        }
                    }
                }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Enter email") },
                    isError = emailHasErrors,
                    supportingText = {
                        if (emailHasErrors) {
                            Text("Incorrect email format.")
                        }
                    }
                )
            })

        Example(
            title = "Example 4: Custom font (千图厚黑)",
            backgroundColor = rainbowColors[3],
            customization = {
                var text by rememberSaveable { mutableStateOf("") }
                val qianTuHouHeiTiFamily = FontFamily(
                    Font(R.font.qian_tu_hou_hei_ti, FontWeight.Light),
                    Font(R.font.qian_tu_hou_hei_ti, FontWeight.Normal),
                    Font(R.font.qian_tu_hou_hei_ti, FontWeight.Normal, FontStyle.Italic),
                    Font(R.font.qian_tu_hou_hei_ti, FontWeight.Medium),
                    Font(R.font.qian_tu_hou_hei_ti, FontWeight.Bold)
                )

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Enter Chinese characters") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textStyle = TextStyle.Default.copy(
                        fontFamily = qianTuHouHeiTiFamily,
                        fontSize = 40.sp
                    )
                )
            })

        Example(
            title = "Example 5: Fancy text",
            backgroundColor = Color.White,
            customization = {
                Text(
                    text = buildAnnotatedString {
                        append("Default + Bold:\n")
                        withStyle(
                            SpanStyle(
                                brush = Brush.linearGradient(
                                    colors = rainbowColors
                                ),
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("red orange yellow green blue indigo violet\n")
                        }
                        append("Monospace + Bold + Italic:\n")
                        withStyle(
                            SpanStyle(
                                brush = Brush.linearGradient(
                                    colors = rainbowColors.reversed()
                                ),
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                fontStyle = FontStyle.Italic
                            )
                        ) {
                            append("violet indigo blue green yellow orange red\n")
                        }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                        .padding(5.dp)

                )
            })

    }
}

@Composable
fun Example(
    title: String,
    backgroundColor: Color,
    customization: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth()

        )
        customization()
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Scaffold { innerPadding ->
        Screen(innerPadding)
    }
}