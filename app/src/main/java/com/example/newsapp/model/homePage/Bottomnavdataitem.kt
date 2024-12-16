package com.example.newsapp.model.homePage

import androidx.compose.ui.graphics.vector.ImageVector

data class Bottomnavdataitem(
    val title : String,
    val SelectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
    val hasNews : Boolean,
    val badgeCount : Int? = null
)
