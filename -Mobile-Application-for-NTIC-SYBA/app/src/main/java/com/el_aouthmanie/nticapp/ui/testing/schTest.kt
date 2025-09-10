import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Header Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("HELLO, Aoutmane", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Education is the most powerful weapon...",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Schedule Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("08:30 - 13:30", color = Color.White, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("LAHYANI", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("Projet de synthese", color = Color.White.copy(0.8f))
                Text("M203", color = Color.White.copy(0.8f))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search in announcements") },
            leadingIcon = {
                Icon(painter = painterResource(id = android.R.drawable.ic_menu_search), contentDescription = "Search")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Today", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Announcements List
        AnnouncementItem("new assignment", "please go and do the test on OFPPT...")
        AnnouncementItem("stage offer", "example company offers a stage on ...")
        AnnouncementItem("schedule update", "please re-check your schedule")
    }
}

@Composable
fun AnnouncementItem(title: String, description: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_dialog_info),
                contentDescription = "Icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text(description, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
