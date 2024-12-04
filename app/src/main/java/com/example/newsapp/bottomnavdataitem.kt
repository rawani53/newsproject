package com.example.newsapp

import androidx.compose.ui.graphics.vector.ImageVector

data class bottomnavdataitem(
    val title : String,
    val SelectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
    val hasNews : Boolean,
    val badgeCount : Int? = null

)
