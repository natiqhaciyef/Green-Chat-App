package com.natiqhaciyef.green_chat_app.data.model

import java.io.Serializable

data class ChatModel(
    var users: List<String>,
    var chat: List<String>,
): Serializable
