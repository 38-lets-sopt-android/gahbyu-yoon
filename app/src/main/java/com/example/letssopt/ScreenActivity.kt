package com.example.letssopt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.ui.theme.LETSSOPTTheme


class ScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Main : BottomNavItem("main", "메인", Icons.Default.Home)
    object Purchase : BottomNavItem("purchase", "개별구매", Icons.Default.ShoppingCart)
    object Webtoon : BottomNavItem("webtoon", "웹툰", Icons.Default.List)
    object Search : BottomNavItem("search", "찾기", Icons.Default.Search)
    object Library : BottomNavItem("library", "보관함", Icons.Default.Folder)
}


@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Color(0xFF141414),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Main.route) {
                MainListScreen()
            }
            composable(BottomNavItem.Purchase.route) { TextScreen("개별구매 화면") }
            composable(BottomNavItem.Webtoon.route) { TextScreen("웹툰 화면") }
            composable(BottomNavItem.Search.route) { TextScreen("찾기 화면") }
            composable(BottomNavItem.Library.route) { LibraryScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Main,
        BottomNavItem.Purchase,
        BottomNavItem.Webtoon,
        BottomNavItem.Search,
        BottomNavItem.Library
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun TextScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
    }
}

@Composable
fun LibraryScreen() {
    val myPhotos = listOf(
        R.drawable.love_,
        R.drawable.stranger_5,
        R.drawable.hail_,
        R.drawable.love_
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "찜한 목록",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 100.dp)
        ) {
            items(myPhotos) { photoRes ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.7f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        coil.compose.AsyncImage(
                            model = photoRes,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Cancel,
                        contentDescription = "삭제",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

data class ContentItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: Int
)


@Composable
fun MainBannerSection() {
    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "방금 막 도착한 신상 컨텐츠",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        Text(
            text = "예능부터 드라마까지!",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 12.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                androidx.compose.ui.layout.ContentScale
                coil.compose.AsyncImage(
                    model = R.drawable.main_banner,
                    contentDescription = "배너 이미지",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun MainListScreen(viewModel: MainViewModel = viewModel()) {

    val sections by viewModel.sections.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            MainBannerSection()
        }

        items(sections) { (title, contentItems) ->
            if (title == "왓챠 파티") {
                ContentHorizontalSection(
                    title = title,
                    items = contentItems,
                    itemWidth = 196.dp,
                    itemHeight = 185.dp
                )
            } else {
                ContentHorizontalSection(
                    title = title,
                    items = contentItems,
                    itemWidth = 100.dp,
                    itemHeight = 150.dp
                )
            }
        }
    }
}


@Composable
fun ContentHorizontalSection(
    title: String,
    items: List<ContentItem>,
    itemWidth: androidx.compose.ui.unit.Dp = 120.dp,
    itemHeight: androidx.compose.ui.unit.Dp = 180.dp
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .width(itemWidth)
                        .height(itemHeight)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        coil.compose.AsyncImage(
                            model = item.imageUrl,
                            contentDescription = item.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContentCard(content: ContentItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = content.title, style = MaterialTheme.typography.titleMedium)
            Text(text = content.subtitle, style = MaterialTheme.typography.bodySmall)
        }
    }
}




