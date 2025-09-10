import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.el_aouthmanie.nticapp.ui.compenents.AsyncImageCard
import com.el_aouthmanie.nticapp.ui.compenents.TitleAppBar
import kotlin.math.roundToInt

@Composable
fun EventsScreen() {
    Scaffold(
        topBar = { TitleAppBar(title = "events") }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item { TopSection() }
            item { FeaturedEvent() }
            item { EventCategory(title = "Tech Events", events = List(6) { "Event $it" }) }

            item { EventCategory(title = "Social Gatherings", events = List(4) { "Event $it" }) }
            item { DraggableLazyRow(List(20){"hellow $it"})}
        }
    }
}

@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            items(100) {

                    AsyncImage(
                        model = "https://i.pravatar.cc/100?img=$it",
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .animateItem()
                            .size(80.dp)
                            .padding(10.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.Green, CircleShape)

                    )
                }


        }
    }
}

@Composable
fun FeaturedEvent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = "https://picsum.photos/600/300",
            contentDescription = "Featured Event",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
        )
        Text(
            text = "BIGGEST EVENT OF THE YEAR",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun EventCategory(title: String, events: List<String>) {
    var eventList by remember { mutableStateOf(events) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        LazyRow(
            modifier = Modifier.padding(top = 8.dp)

        ) {
            items(eventList) {
                AsyncImageCard(model = "https://picsum.photos/150/200", text = "hello world")
            }
            if (events.size > 5) {
                item {
                    TextButton(
                        onClick = {
                            eventList = eventList + List(5) { "New Event ${eventList.size + it}" }
                        },

                        ) {
                        Text("More")
                    }
                }

            }
        }
    }
    HorizontalDivider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(10.dp))
}

@Composable
fun DraggableLazyRow(itemsList: List<String>) {
    val itemOffsets = remember { mutableStateMapOf<String, Float>() }

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(itemsList, key = { it }) { item ->
            var offsetX by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            offsetX += dragAmount.x

                        }
                    }
                    .padding(8.dp)
                    .background(Color.Blue, shape = RoundedCornerShape(8.dp))
                    .size(100.dp)
                    ,
                contentAlignment = Alignment.Center
            ) {
                Text(text = item, color = Color.White)
            }
        }
    }
}
