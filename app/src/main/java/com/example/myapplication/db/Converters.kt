package com.example.myapplication.db

import androidx.room.TypeConverter
import com.example.myapplication.model.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromMutableListOfPlayers(list : MutableList<Player>) : String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toMutableListOfPlayers(value: String?) : MutableList<Player> {
        val listType = object :TypeToken<MutableList<Player>>(){}.type
        return Gson().fromJson(value, listType)
    }
}