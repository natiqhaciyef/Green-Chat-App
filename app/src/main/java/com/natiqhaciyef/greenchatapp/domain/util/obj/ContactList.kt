package com.natiqhaciyef.greencamp.domain.util.obj

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.outlined.LocationCity
import com.natiqhaciyef.greencamp.domain.util.classes.ContactModel

object ContactList {
    val list = mutableListOf(
        ContactModel(name = "Nömrə", icon = Icons.Default.PhoneInTalk),
        ContactModel(name = "E-poçt", icon = Icons.Default.Mail),
        ContactModel(name = "Ünvan", icon = Icons.Outlined.LocationCity),
    )
}