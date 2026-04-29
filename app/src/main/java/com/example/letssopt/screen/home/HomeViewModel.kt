package com.example.letssopt.screen.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.model.ContentItem



data class HomeSection(
    val title: String,
    val items: List<ContentItem>
)

class HomeViewModel : ViewModel() {
    private var contentItems = emptyList<HomeSection>()

    fun getContentItem(): List<HomeSection> {
        if (contentItems.isEmpty()) {
            contentItems = createContentItems()
        }
        return contentItems
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
