package com.example.lolstatistic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.lolstatistic.match.list.MatchDataBase
import com.example.lolstatistic.splash.SplashFragment


class MainActivity : AppCompatActivity() {

    var db: MatchDataBase = Room.databaseBuilder(
        applicationContext,
        MatchDataBase::class.java, "populus-database"
    ).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}