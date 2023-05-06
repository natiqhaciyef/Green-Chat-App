package com.natiqhaciyef.greenchatapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.data.model.UserStory

@Preview
@Composable
fun UserStoryItem(story: UserStory = UserStory("","")) {
    Column(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .height(100.dp)
            .width(80.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier){
            Image(
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp),
                painter = painterResource(id = story.image.toInt()),
                contentDescription = "User story image",
                contentScale = ContentScale.Crop,
            )
            Image(
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.avatarbadge),
                contentDescription = "Is active dot",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            text = "${story.name}",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

    }
}