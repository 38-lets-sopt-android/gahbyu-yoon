package com.example.letssopt.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Main : BottomNavItem("main", "메인", Icons.Default.Home)
    object Purchase : BottomNavItem("purchase", "개별구매", Icons.Default.ShoppingCart)
    object Webtoon : BottomNavItem("webtoon", "웹툰", Icons.Default.List)
    object Search : BottomNavItem("search", "찾기", Icons.Default.Search)
    object Library : BottomNavItem("library", "보관함", Icons.Default.Folder)
}