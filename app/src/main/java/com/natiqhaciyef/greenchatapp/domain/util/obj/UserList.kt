package com.natiqhaciyef.greenchatapp.domain.util.obj

import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.data.model.UserStory
import com.natiqhaciyef.greenchatapp.data.model.UserTextModel

object UserList {
    val storyList = mutableListOf(
        UserStory(image = "${R.drawable.user_4}", "Leslie"),
        UserStory(image = "${R.drawable.user_1}", "Philip"),
        UserStory(image = "${R.drawable.user_2}", "Aubrey"),
        UserStory(image = "${R.drawable.user_3}", "Mitchell"),
    )

    val chatList = mutableListOf(
        UserTextModel(
            image = R.drawable.user_9,
            name = "Theresa",
            textDesc = "Okay, i’ll work on it when it’s...",
            time = "06:21",
            count = 4,
            isRead = false
        ),
        UserTextModel(
            image = R.drawable.user_5,
            name = "Annette",
            textDesc = "Okay, it’s all noted.",
            time = "Yesterday",
            isRead = true
        ),

        UserTextModel(
            image = R.drawable.user_6,
            name = "Eleanor",
            textDesc = "Okay, it’s all noted.",
            time = "Yesterday",
            isRead = true
        ),

        UserTextModel(
            image = R.drawable.user_7,
            name = "Kathryn",
            textDesc = "Okay, it’s all noted.",
            time = "Yesterday",
            isRead = true
        ),
        UserTextModel(
            image = R.drawable.user_8,
            name = "Wade",
            textDesc = "Okay, it’s all noted.",
            time = "Yesterday",
            isRead = true
        )
    )
}