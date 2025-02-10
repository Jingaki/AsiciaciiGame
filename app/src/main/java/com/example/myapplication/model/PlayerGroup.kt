package com.example.myapplication.model

//ovako importujute klase/fajlove u svoje activitije
//import com.example.myapplication.model.Player
//import com.example.myapplication.model.winsComparator
//import com.example.myapplication.model.answersComparator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "playergroups")
data class PlayerGroup(
    @PrimaryKey
    private var name : String,
    @ColumnInfo(name="numOfPlayers")
    private var numOfPlayers : Int = 0,
    @ColumnInfo(name="players")
    private var players : MutableList<Player> = mutableListOf()
    ) {

    //kontrola plGroup-a
    fun add(player : Player){
        players.add(player)
        numOfPlayers++
    }
    //opciono: brisanje clana iz grupe

    //geteri
    fun getName() : String {
        return name
    }
    fun getPlayers() : MutableList<Player> {
        return players
    }
    fun getNumOfPlayers() : Int {
        return numOfPlayers
    }
    //seteri
    fun setPlayerGroupName(pgName : String) {
        name = pgName
    }
    fun setPlayers(pgList : List<Player>) {
        players = mutableListOf()
        for(player in pgList)
            players.add(player)
    }
    fun resetAnswers() {
        players.map { player: Player -> player.resetNumOfCorrectAnswers() }
    }
    //fje za rad sa igracima
    fun sortedByWins() : List<Player> {
        return players.sortedWith(winsComparator)
    }
    override fun toString() : String {
        return this.players.toString()
    }
}

//main za testiranje
//fun main(){
//    //val pg1 = PlayerGroup("PG1")
//
//}