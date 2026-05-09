package com.example.letssopt.screen.library

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val _myPhotos = MutableStateFlow(
        listOf(
            R.drawable.img_love_,
            R.drawable.img_stranger_5,
            R.drawable.img_hail_,
            R.drawable.img_love_
        )
    )

    val myPhotos: StateFlow<List<Int>> = _myPhotos.asStateFlow()
}


