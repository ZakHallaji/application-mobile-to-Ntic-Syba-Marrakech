package com.el_aouthmanie.nticapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.el_aouthmanie.nticapp.R
import com.el_aouthmanie.nticapp.modules.intities.Club

import com.el_aouthmanie.nticapp.ui.compenents.TitleAppBar

// Sample data models
data class Event(val title: String, val imageRes: Int)
data class Organizer(val name: String, val events: List<Event>)

@Composable
fun EventsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TitleAppBar(title = "Events")}
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
            ,

        ){
            item {
                ActiveClubsBar(clubs = listOf(
                    Club("it","hello"),
                    Club("it","hello"),
                    Club("it","hello"),
                    Club("it","hello")
                ),
                    modifier = Modifier
                        .height(80.dp)
                        .background(Color.Gray)//to remove
                )
                HorizontalDivider(Modifier.padding(5.dp))
                MainEvent()

            }
            items(13){
                HorizontalDivider()
                OldEventsRow(events = listOf("hehe","hhd","jecjcbe","jecbce"),modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp))
            }

            
        }
    }
}

@Composable
fun ActiveClubsBar(clubs : List<Club>,modifier: Modifier = Modifier) {
    var imageOk by remember {
        mutableStateOf(true)
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()


    ) {
        items(clubs.size){
            val club = clubs[it]
            AsyncImage(
                model = club.iconLink,
                contentDescription = "${club.name}club icon",
                onError = {
                    imageOk =  false
                },

                )
            if (!imageOk) {
                Image(painter = painterResource(id = R.drawable.event), contentDescription = "h")
            }

        }
    }
}

@Composable
fun MainEvent(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(12.dp)

            .clip(RoundedCornerShape(10.dp))

            .background(Color.Red)

    ) {

//        AsyncImage(model = "he", contentDescription = "d", onError = {
//        })
        Image(painter = painterResource(id = R.drawable.event),
            contentDescription = "event image",
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun OldEventsRow(modifier: Modifier = Modifier,events : List<String>) {

    Column(modifier) {
        Text(text = "hello")
        LazyRow {
            items(events.size){
                EventCard()
            }
        }
    }
}





@Composable
fun EventCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(12.dp)

    ) {
        Image(painter = painterResource(id = R.drawable.event), contentDescription = "event")
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun H(modifier: Modifier = Modifier) {
    EventsScreen()
//    MainEvent()
}










