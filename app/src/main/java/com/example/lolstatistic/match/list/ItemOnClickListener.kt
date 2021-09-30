package com.example.lolstatistic.match.list

import com.example.lolstatistic.match.details.MatchModel

interface ItemOnClickListener {
    fun onClick(match: MatchModel?)
}