package com.botime.learning.layoutexample

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val route: String
    val icon: ImageVector
}

object ColumnScreen: Destinations {
    override val route = "Column"
    override val icon = Icons.Rounded.Coffee
}

object RowScreen: Destinations {
    override val route = "Row"
    override val icon = Icons.Rounded.Memory
}


