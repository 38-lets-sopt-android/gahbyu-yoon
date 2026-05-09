package com.example.letssopt.screen.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.letssopt.R.drawable.img_main_banner

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
            Box(contentAlignment = Center) {
                AsyncImage(
                    model = img_main_banner,
                    contentDescription = "배너 이미지",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = Crop
                )
            }
        }
    }
}
