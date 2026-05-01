package com.example.letssopt.screen.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.model.ContentItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class HomeSection(
    val title: String,
    val items: List<ContentItem>
)

class HomeViewModel(
    private val homeRepository: HomeRepository = HomeRepository()
) : ViewModel() {
    private var _contentItems = MutableStateFlow<List<HomeSection>>(emptyList())
    val contentItems: StateFlow<List<HomeSection>> = _contentItems.asStateFlow()

    init {
        loadContentItems()
    }

    private fun loadContentItems() {

        _contentItems.value = homeRepository.getHomeSection()
    }
}



