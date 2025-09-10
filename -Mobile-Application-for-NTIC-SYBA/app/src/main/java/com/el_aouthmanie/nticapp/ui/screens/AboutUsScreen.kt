import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.ktx.app

//// Data class for User
//data class User(
//    val name: String = "",
//    val email: String = "",
//    val age: Int = 0
//)
//
//@Composable
//fun AddUserScreen() {
//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    // Initialize Firebase if not already initialized
//
//    // State variables
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var age by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = age,
//            onValueChange = { age = it.filter { c -> c.isDigit() } },
//            label = { Text("Age") },
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number
//            )
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                if (name.isNotEmpty() && email.isNotEmpty() && age.isNotEmpty()) {
//                    val user = User(
//                        name = name,
//                        email = email,
//                        age = age.toIntOrNull() ?: 0
//                    )
//
//                    scope.launch {
//                        addUserToFirestore(
//                            user = user,
//                            context = context,
//                            onSuccess = {
//                                name = ""
//                                email = ""
//                                age = ""
//                            }
//                        )
//                    }
//                }
//            },
//            enabled = name.isNotEmpty() && email.isNotEmpty() && age.isNotEmpty()
//        ) {
//            Text("Add User")
//        }
//    }
//}
//
//private fun addUserToFirestore(
//    user: User,
//    context: Context,
//    onSuccess: () -> Unit
//) {
//    val db = Firebase.firestore
//
//    db.collection("users")
//        .add(user)
//        .addOnSuccessListener {
//            Toast.makeText(context, "User added successfully!", Toast.LENGTH_SHORT).show()
//            onSuccess()
//        }
//        .addOnFailureListener { e ->
//            Toast.makeText(context, "Error adding user: ${e.message}", Toast.LENGTH_SHORT).show()
//        }
//}
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.R

@Composable
fun AboutUsScreen() {
    val scrollState = rememberScrollState()
    val alpha by animateFloatAsState(
        targetValue = if (scrollState.value > 100) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), label = "alphaAnimation"
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile), // Replace with your logo
            contentDescription = "University Logo",
            modifier = Modifier.size(100.dp).clip(CircleShape).alpha(alpha)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Prestigious University",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Our university is dedicated to fostering knowledge, innovation, and leadership. We provide world-class education with a strong focus on research and real-world applications.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "At our institution, students engage in diverse fields ranging from Science and Technology to Arts and Business. We pride ourselves on creating an inclusive and dynamic learning environment where students thrive.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        repeat(20){
            Text(
                text = "At our institution, students engage in diverse fields ranging from Science and Technology to Arts and Business. We pride ourselves on creating an inclusive and dynamic learning environment where students thrive.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        SocialMediaIcons()
    }
}

@Composable
fun SocialMediaIcons() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { /* Open Twitter */ }) {
            Icon(painter = painterResource(id = R.drawable.event), contentDescription = "Twitter", tint = Color.Blue)
        }
        IconButton(onClick = { /* Open Facebook */ }) {
            Icon(painter = painterResource(id = R.drawable.event), contentDescription = "Facebook", tint = Color.Blue)
        }
        IconButton(onClick = { /* Open LinkedIn */ }) {
            Icon(painter = painterResource(id = R.drawable.event), contentDescription = "LinkedIn", tint = Color.Blue)
        }
    }
}