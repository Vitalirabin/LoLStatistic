package com.example.lolstatistic.statistic

import androidx.lifecycle.MutableLiveData

class MatchesModel {
    val allMatches = MutableLiveData<Int>()
    val winMatches = MutableLiveData<Int>()
    val loseMatches = MutableLiveData<Int>()
}