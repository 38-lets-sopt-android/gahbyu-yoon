package com.example.letssopt.navigation

import com.example.letssopt.R

sealed class BottomNavItem(val route: String, val label: String, val iconRes: Int) {
    object Main : BottomNavItem("main", "메인", R.drawable.ic_bottom_bar_main_24)
    object Purchase : BottomNavItem("purchase", "개별구매", R.drawable.ic_bottom_bar_category_24)
    object Webtoon : BottomNavItem("webtoon", "웹툰", R.drawable.ic_bottom_bar_wallet_24)
    object Search : BottomNavItem("search", "찾기", R.drawable.ic_bottom_search_24)
    object Library : BottomNavItem("library", "보관함", R.drawable.ic_bottom_bar_folder_24)
}