package com.natiqhaciyef.greenchatapp.data.model

data class UserTextModel(
    var image: Int,
    var name: String,
    var textDesc: String,
    var time: String,
    var count: Int? = null,
    var isRead: Boolean
)
