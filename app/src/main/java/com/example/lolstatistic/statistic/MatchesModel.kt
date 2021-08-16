package com.example.lolstatistic.statistic

import androidx.lifecycle.MutableLiveData

class MatchesModel {
    var allMatches = MutableLiveData<Int>()
    var winMatches = MutableLiveData<Int>()
    var loseMatches = MutableLiveData<Int>()
}