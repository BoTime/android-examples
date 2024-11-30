package com.botime.learning.textfieldvalueexample.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DpAndSpComparisonScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(innerPadding)
            .fillMaxWidth(),
    ) {
        Text(text = "dp and sp")
        // TODO
    }
}