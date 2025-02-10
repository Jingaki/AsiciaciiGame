package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.model.PlayerGroup

//data access object
@Dao
interface PlayerGroupDao {

    @Insert
    suspend fun addPlayerGroup(pg : PlayerGroup)
    @Update
    suspend fun updatePlayerGroup(pg : PlayerGroup)
    @Delete
    suspend fun deletePlayerGroup(pg : PlayerGroup)
    @Query("DELETE FROM playergroups")
    suspend fun deleteAllPlayerGroups()
    @Query("SELECT * FROM playergroups")
    fun readAllData() : LiveData<List<PlayerGroup>>
}