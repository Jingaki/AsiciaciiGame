package com.example.myapplication.model
import kotlinx.serialization.*

@Serializable
data class Player(
    private val name : String,
    private var numOfWins : Int = 0,
    private var paired : Boolean = false,
    private var numOfCorrectAnswers : Int = 0
    ) {

    //geteri
    fun getName() : String {
        return name
    }
    fun getNumOfWins() : Int {
        return numOfWins
    }
    fun getNumOfCorrectAnswers() : Int {
        return numOfCorrectAnswers
    }
    //seteri
    fun setPaired(b : Boolean) {
        paired = b
    }
    fun resetNumOfCorrectAnswers() {
        numOfCorrectAnswers = 0
    }
    //random korisne fje
    fun isPaired() : Boolean {
        return paired
    }
    fun winsIncrease() {
        numOfWins++
    }
    fun correctAnswersIncrease() {
        numOfCorrectAnswers++
    }
    override fun toString() : String {
        return this.getName()
    }
}

////main metod za testiranje fja
//fun main(){
//    val p1 = Player("P1")
//    val p2 = Player("P2")
//    val lista = mutableListOf<Player>()
//    lista.add(p1)
//    lista.add(p2)
//    p1.winsIncrease()
//    p2.correctAnswersIncrease()
//    println(p1.getNumOfWins())
//    println(p2.getNumOfCorrectAnswers())
//}













