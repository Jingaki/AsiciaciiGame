package com.example.myapplication.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.PlayerGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerGroupViewModel(application: Application) : AndroidViewModel(application){

    val readAllData : LiveData<List<PlayerGroup>>
    private val repository : PlayerGroupRepository

    init {
        val playerGroupDao = PlayerGroupDatabase.getDatabase(application).playerGroupDao()
        repository = PlayerGroupRepository(playerGroupDao)
        readAllData = repository.readAllData
    }
    fun addPlayerGroup(pg : PlayerGroup) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayerGroup(pg)
        }
    }
    fun updatePlayerGroup(pg : PlayerGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayerGroup(pg)
        }
    }
    fun deletePlayerGroup(pg : PlayerGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlayerGroup(pg)
        }
    }
    fun deleteAllPlayerGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPlayerGroups()
        }
    }
}