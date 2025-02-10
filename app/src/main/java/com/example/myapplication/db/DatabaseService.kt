package com.example.myapplication.db

import com.example.myapplication.model.Game
import com.example.myapplication.model.PlayerGroup

interface DatabaseService {
    fun connect() : DatabaseService
    fun getPlayerGroupNames() : MutableList<String>
    fun addPlayerGroup (playerGroup: PlayerGroup)
    fun getPlayerGroup (name: String) : PlayerGroup
    fun setPlayerGroups (playerGroups : MutableList<PlayerGroup>)
    fun removePlayerGroup (playerGroup: PlayerGroup)
    fun setGame(game: Game)
    fun getGame() : Game
}