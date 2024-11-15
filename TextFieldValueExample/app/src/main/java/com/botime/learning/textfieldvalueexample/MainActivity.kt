package com.botime.learning.textfieldvalueexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .padding(innerPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextInputArea(
            title = "Example 1: Basic",
            backgroundColor = Color.Gray,
            inputArea = {
                var text by remember { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Enter username") },
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .fillMaxWidth(),
                )
            })

        TextInputArea(
            title = "Example 2: Email and password",
            backgroundColor = Color.Green,
            inputArea = {
                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(text = "Enter email") },
                    modifier = Modifier
                        .background(color = Color.Green)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Enter password") },
                    modifier = Modifier
                        .background(color = Color.Green)
                        .fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            })
    }
}

@Composable
fun TextInputArea(
    title: String,
    backgroundColor: Color,
    inputArea: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(5.dp)
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
        inputArea()
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Scaffold { innerPadding ->
        Screen(innerPadding)
    }
}
