package com.example.medshare.Data

import com.example.medshare.Model.Welcome
import com.example.medshare.R

class DataWelcome() {
    fun loadWelcome(): List<Welcome>{
        return listOf<Welcome>(
            Welcome(R.drawable.carosel_image),
            Welcome(R.drawable.carosel_image),
            Welcome(R.drawable.carosel_image)
        )
    }
}