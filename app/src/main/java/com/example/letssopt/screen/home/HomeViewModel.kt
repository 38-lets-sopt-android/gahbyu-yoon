package com.example.letssopt.screen.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.model.ContentItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class HomeSection(
    val title: String,
    val items: List<ContentItem>
)

class HomeViewModel : ViewModel() {
    private var _contentItems = MutableStateFlow<List<HomeSection>>(emptyList())
    val contentItems: StateFlow<List<HomeSection>> = _contentItems.asStateFlow()

  init{
      loadContentItems()
  }

    private fun loadContentItems(){
        _contentItems.value = createContentItems()
    }



    private fun createContentItems() = listOf(
        HomeSection(
            "왓고리즘",
            items = listOf(
                ContentItem(1, "이사랑통역되나요", "", R.drawable.love_),
                ContentItem(2, "스트레인져", "5", R.drawable.stranger_5),
                ContentItem(3, "헤일메리", "", R.drawable.hail_),
                ContentItem(4, "이사랑통역되나요", "", R.drawable.love_)
            )
        ),
        HomeSection(
            "공개 예정 콘텐츠",
            listOf(
                ContentItem(5, "이사랑통역되나요", "", R.drawable.love_),
                ContentItem(6, "스트레인져", "5", R.drawable.stranger_5),
                ContentItem(7, "헤일메리", "", R.drawable.hail_),
                ContentItem(8, "이사랑통역되나요", "", R.drawable.love_)
            )
        ),
        HomeSection(
            "왓챠 파티",
            listOf(
                ContentItem(9, "왕과사는남자", "", R.drawable.king_),
                ContentItem(10, "파묘", "5", R.drawable.pamio_)
            )
        )
    )
}
