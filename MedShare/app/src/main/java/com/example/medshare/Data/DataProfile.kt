package com.example.medshare.Data

import com.example.medshare.Model.Profile
import com.example.medshare.R


class DataProfile {
    fun loadProfile(): List<Profile>{
       return listOf<Profile>(
           Profile(R.string.user_name),
           Profile(R.string.email_address),
           Profile(R.string.pass_word),
           Profile(R.string.hostel_name),
           Profile(R.string.hostel_block),
           Profile(R.string.hostel_floor),
           Profile(R.string.room_number)
       )
       }

    }
