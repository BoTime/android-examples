package com.botime.learning.layoutexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val columnWidth = 100.dp
        ColumnView(Arrangement.SpaceEvenly, "SpaceEvenly", columnWidth, Color(0xFFd4d4d4))
        ColumnView(Arrangement.SpaceBetween, "SpaceBetween", columnWidth, Color(0xFFb4b4b4))
        ColumnView(Arrangement.SpaceAround, "SpaceAround", columnWidth, Color(0xFF909090))
        ColumnView(Arrangement.Center, "Center", columnWidth, Color(0xFF636363))
    }

}

@Composable
fun ColumnView(
    arrangment: Arrangement.HorizontalOrVertical,
    title: String,
    columnWidth: Dp,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(columnWidth)
            .background(backgroundColor),
        verticalArrangement = arrangment
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .background(Color(0xFFFF0000))
                .fillMaxWidth()
        ) {
            Text(text = title, color = Color(0xFFFFFFFF))
        }
        Box(
            modifier = Modifier
                .height(100.dp)
                .background(Color(0xFF00FF00))
                .fillMaxWidth()
        ) {
            Text(text = title, color = Color(0xFF000000))
        }

        Box(
            modifier = Modifier
                .height(100.dp)
                .background(Color(0xFF0000FF))
                .fillMaxWidth()
        ) {
            Text(text = title, color = Color(0xFFFFFFFF))
        }

        Box(
            modifier = Modifier
                .height(100.dp)
                .background(Color(0xFF000000))
                .fillMaxWidth()
        ) {
            Text(text = title, color = Color(0xFFFFFFFF))
        }
    }
}

@Preview
@Composable
fun ColumnScreenPreview() {
    ColumnScreen()
}
