package com.botime.learning.layoutexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun StaggeredGridScreen() {
    val n = 1000000
    val randomColor = Random(0)
    val randomHeight = Random(1)
    val colors = List(n) { randomColor.nextLong(0xFF000000, 0xFFFFFFFF) }
    val heights = List(n) { randomHeight.nextInt(40, 400) }
    val padding = 10.dp
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = padding,
        horizontalArrangement = Arrangement.spacedBy(padding),
        content = {
            items(n) { index ->
                GridCell(index, colors[index], heights[index])
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(padding)
    )
}

@Composable
fun GridCell(number: Int, color: Long, height: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(height.dp),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(color)),
            contentAlignment = Alignment.Center
        ) {
            val textColor: Long = (0xFFFFFFFF - color) + 0xFF000000
            println("color: $color, textColor: $textColor")
            Text(
                text = "$number",
                modifier = Modifier
                    .width(30.dp),
                textAlign = TextAlign.Center,
                color = Color(textColor)
            )
        }
    }
}

@Preview
@Composable
fun StaggeredGridScreenPreview() {
    StaggeredGridScreen()
}