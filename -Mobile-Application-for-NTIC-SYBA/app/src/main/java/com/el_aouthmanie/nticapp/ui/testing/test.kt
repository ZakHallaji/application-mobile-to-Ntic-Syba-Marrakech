import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.R
import com.el_aouthmanie.nticapp.ui.theme.primaryBlue
import com.el_aouthmanie.nticapp.ui.theme.secondaryBackgroundWhite
import com.el_aouthmanie.nticapp.ui.theme.secondaryBorder
import kotlin.random.Random


@Composable
fun NotificationCard(
    icon: Painter,
    title: String,
    subtitle: String,
    backgroundColor: Color = Color.White
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                1.dp,
                secondaryBorder,
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))


            ,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors().copy(
            containerColor = secondaryBackgroundWhite,


        )
    ) {
        Box {
            Canvas(modifier = Modifier.fillMaxSize()) {
                repeat(5) {
                    drawCircle(
                        color = primaryBlue.copy(alpha = Random.nextFloat() * 0.2f),
                        radius = Random.nextFloat() * 100f + 30f,
                        center = center.copy(
                            x = Random.nextFloat() * size.width,
                            y = Random.nextFloat() * size.height
                        )
                    )

                }
                //some lines , i think it absurde for now
//                repeat(3) {
//                    drawLine(
//                        color = primaryBlue.copy(alpha = 0.1f),
//                        start = center.copy(
//
//                            x = Random.nextFloat() * size.width,
//                            y = Random.nextFloat() * size.height
//                        ),
//                        end = center.copy(
//                            x = Random.nextFloat() * size.width,
//                            y = Random.nextFloat() * size.height
//                        ),
//                        strokeWidth = Random.nextFloat() * 6f + 2f
//                    )
//                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Spacer to separate the icon from the text
                Spacer(modifier = Modifier.width(16.dp))

                // Title and subtitle
                Column {
                    Text(
                        text = title,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationsListr() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        // Header
        Text(
            text = "Today",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp)
        )

        // List of notifications
        NotificationCard(
            icon = painterResource(id = R.drawable.profile), // Replace with your icon resource
            title = "new assignment",
            subtitle = "please go and do the test onddddddddddddddddd OFPPT..."
        )

        NotificationCard(
            icon = painterResource(id = R.drawable.profile), // Replace with your icon resource
            title = "stage offer",
            subtitle = "example company offers a stage on ...",
            backgroundColor = Color(0xFF1E90FF) // Blue background for this card
        )

        NotificationCard(
            icon = painterResource(id = R.drawable.profile), // Replace with your icon resource
            title = "schedule update",
            subtitle = "please re-check your schedule"
        )
    }
}
