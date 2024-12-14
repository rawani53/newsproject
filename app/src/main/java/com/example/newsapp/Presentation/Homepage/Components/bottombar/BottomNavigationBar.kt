package com.example.newsapp.Presentation.Homepage.Components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.newsapp.data.model.homepage.bottomnavdataitem

@Composable
fun BottomNavBar() {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val navTabItems = listOf(
        bottomnavdataitem(
            title = "Home",
            SelectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        bottomnavdataitem(
            title = "Saved",
            SelectedIcon = Icons.Filled.Favorite,
            unSelectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false
        ),
        bottomnavdataitem(
            title = "Settings",
            SelectedIcon = Icons.Filled.Settings,
            unSelectedIcon = Icons.Outlined.Settings,
            hasNews = true
        )
    )

    NavigationBar {
        navTabItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { selectedItemIndex = index },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge { Text(text = item.badgeCount.toString()) }
                            } else {
                                if (item.hasNews) {
                                    Badge()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) {
                                item.SelectedIcon
                            } else {
                                item.unSelectedIcon
                            },
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(item.title) }
            )
        }
    }
}