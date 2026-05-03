package com.example.letssopt.screen.home


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.screen.home.component.ContentHorizontalSection
import com.example.letssopt.screen.home.component.MainBannerSection
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val sections by viewModel.contentItems.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            MainBannerSection()
        }

        items(sections) { section ->
            if (section.title == "왓챠 파티") {
                ContentHorizontalSection(
                    title = section.title,
                    items = section.items.toImmutableList(),
                    itemWidth = 196.dp,
                    itemHeight = 185.dp
                )
            } else {
                ContentHorizontalSection(
                    title = section.title,
                    items = section.items.toImmutableList(),
                    itemWidth = 100.dp,
                    itemHeight = 150.dp
                )
            }
        }
    }
}