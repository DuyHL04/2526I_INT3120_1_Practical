package com.example.affirmationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.affirmationsapp.ui.theme.AffirmationsAppTheme



data class AffirmationItem(val text: String, val imageRes: Int)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AffirmationList(
                        sampleData(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AffirmationCard(item: AffirmationItem) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ){
        Row (
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.text,
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Lặp lại mỗi ngày . Tạo thói quen tích cực",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}
@Composable
fun AffirmationList(
    affirmationitems: List<AffirmationItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(affirmationitems) { item ->
            AffirmationCard(item)
        }

    }
}
fun sampleData(): List<AffirmationItem> {
    return listOf(
        AffirmationItem("Tôi đủ giỏi để vượt qua thử thách.", R.drawable.flower),
        AffirmationItem("Mỗi ngày tôi tiến bộ hơn một chút.", R.drawable.sun),
        AffirmationItem("Tôi xứng đáng với điều tốt đẹp.", R.drawable.calm)
    )
}