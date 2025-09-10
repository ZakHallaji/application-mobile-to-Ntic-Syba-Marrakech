package com.el_aouthmanie.nticapp.ui.compenents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AsyncImageCard(modifier : Modifier = Modifier,
                   model : String,
                   text : String,

                   ) {
    val sts = remember {
        mutableStateOf(true)
    }
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(90.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            ) {
            AsyncImage(
                model = model,
                contentDescription = "Event Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                onError = {
                    sts.value = true
                }
            )
            if (sts.value) {
                CircularProgressIndicator(
                    modifier = Modifier.matchParentSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )
            Text(
                text = text,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun helo() {
    AsyncImageCard(model = "hh", text = "tahya ltlj", modifier = Modifier)
}
//@Preview(showBackground = false)
//@Composable
//private fun helod() {
//    AsyncImageCard(model = "hh", text = "tahya ltlj")
//}