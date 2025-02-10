package com.example.myapplication.db

import androidx.lifecycle.LiveData
import com.example.myapplication.model.PlayerGroup

class PlayerGroupRepository(private val playerGroupDao : PlayerGroupDao) {

    val readAllData: LiveData<List<PlayerGroup>> = playerGroupDao.readAllData()

    suspend fun addPlayerGroup(pg : PlayerGroup) {
        playerGroupDao.addPlayerGroup(pg)
    }
    suspend fun updatePlayerGroup(pg : PlayerGroup) {
        playerGroupDao.updatePlayerGroup(pg)
    }
    suspend fun deletePlayerGroup(pg : PlayerGroup) {
        playerGroupDao.deletePlayerGroup(pg)
    }
    suspend fun deleteAllPlayerGroups() {
        playerGroupDao.deleteAllPlayerGroups()
    }
}