package com.natiqhaciyef.greenchatapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
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
import com.natiqhaciyef.greenchatapp.data.model.UserTextModel
import com.natiqhaciyef.greenchatapp.ui.theme.AppDarkGray
import com.natiqhaciyef.greenchatapp.ui.theme.AppOrange

@Preview
@Composable
fun UserStoryItem(story: UserStory = UserStory("", "")) {
    Column(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .height(100.dp)
            .width(80.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier) {
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


//@Preview
@Composable
fun UserChatView(userTextModel: UserTextModel) {
    Row(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = userTextModel.image),
            contentDescription = "Description",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(55.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(0.70f),
                text = userTextModel.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(0.70f),
                text = userTextModel.textDesc,
                fontSize = 15.sp,
                color = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, end = 15.dp)
                    .fillMaxWidth(),
                text = userTextModel.time,
                fontSize = 15.sp,
                color = if (userTextModel.count != null) AppOrange else AppDarkGray,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End
            )
            if (userTextModel.count != null && userTextModel.count != 0) {
                Box(
                    modifier = Modifier
                        .padding(end = 20.dp, top = 5.dp)
                        .size(25.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.text_count_background),
                        contentDescription = "Count",
                        modifier = Modifier
                            .size(25.dp)
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = "4",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End
                    )

                }
            }


        }
    }
}