package com.example.letssopt.screen.library

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val _myPhotos = MutableStateFlow(
        listOf(
            R.drawable.love_,
            R.drawable.stranger_5,
            R.drawable.hail_,
            R.drawable.love_
        )
    )

    val myPhotos: StateFlow<List<Int>> = _myPhotos.asStateFlow()
}


