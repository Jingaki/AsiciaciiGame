package com.example.myapplication.model

import android.content.res.Resources
import com.example.myapplication.R
import java.io.Serializable

class Game : Serializable {

    private var plGroup : PlayerGroup = PlayerGroup("")
    private var numOfWordsPerPlayer : Int = 0
    private var currentPlayer : Player = Player("")
    private var listOfPairs = mutableListOf<Pair<Player, Player>>()
    private var currentGamePhase : Int = 1
    private var wordBank = mutableListOf<String>()
    private var currentWord : String = ""
    private var timer : Int = 20//Resources.getSystem().getInteger(R.integer.DefaultSeconds).toInt()

    fun getPlayerGroup() : PlayerGroup {
        return plGroup
    }
    fun getNumOfWordsPerPlayer() : Int {
        return numOfWordsPerPlayer
    }
    fun getWords() : MutableList<String> {
        return wordBank
    }
    fun getPairs() : MutableList<Pair<Player, Player>> {
        return listOfPairs
    }
    fun getCurrentPlayer() : Player {
        return currentPlayer
    }
    fun getCurrentGamePhase() : Int {
        return currentGamePhase
    }
    fun getCurrentWord() : String {
        return currentWord
    }

    fun getNextWord(word: String) : String? {
        val i = wordBank.indexOf(word)
        return if (i==wordBank.size-1) {
            null
        } else {
            currentWord=wordBank[i+1]
            wordBank[i + 1]
        }
    }
    fun getTimer() : Int {
        return timer
    }

    fun nextPlayer() {
        val i = listForWordEntering().indexOf(currentPlayer)
        currentPlayer = if (i==listForWordEntering().size-1)
            listForWordEntering()[0]
        else
            listForWordEntering()[i+1]
    }
    fun setPlayerGroup(pg: PlayerGroup) {
        plGroup = pg
    }
    fun setNumOfWordsPerPlayer(n : Int) {
        numOfWordsPerPlayer = n
    }
    fun setCurrentGamePhase(n: Int) {
        currentGamePhase = n
    }
    fun setCurrentWord(word: String) {
        currentWord = word
    }
    fun setCurrentPlayer(player: Player) {
        currentPlayer=player
    }
    fun setTimer(n : Int) {
        timer = n
    }
    fun makeRandomPairs(){
        val list = mutableListOf<Pair<Player, Player>>()
        val players : MutableList<Player> = plGroup.getPlayers()
        players.shuffle()
        for(i in players.indices) {
            while (!players[i].isPaired()) {
                val random = (0 until players.size).random()
                if (!players[random].isPaired() && players[i]!=players[random]) {
                    list.add(Pair(players[i], players[random]))
                    players[i].setPaired(true)
                    players[random].setPaired(true)
                }
            }
        }
        listOfPairs = list
        for(player in players)
            player.setPaired(false)
    }
    fun sortedByAnswers() : List<Pair<Player, Player>>{
        return listOfPairs.sortedWith(answersComparator)
    }
    fun listForWordEntering() : MutableList<Player> {
        val pom : MutableList<Player> = mutableListOf()
        for (pair in listOfPairs)
            pom.add(pair.first)
        for (pair in listOfPairs)
            pom.add(pair.second)
        return pom
    }
    fun addWord(word : String) {
        wordBank.add(word)
    }
    fun shuffleWords() {
        wordBank.shuffle()
    }
    fun isAlreadyEntered(word: String) : Boolean {
        return wordBank.contains(word)
    }

    fun pairedWith (player: Player) : Player {
        val theirPair: Player
        var pair = listOfPairs.find { it.first == player }
        if (pair == null) {
            pair = listOfPairs.find { it.second == player }
            theirPair = pair!!.first
        } else
            theirPair = pair.second
        return theirPair
    }
    fun numOfCorrectAnswersInPair(player: Player) : Int {
        return player.getNumOfCorrectAnswers() + pairedWith(player).getNumOfCorrectAnswers()
    }
    fun pointsOfAPair(pair: Pair<Player, Player>) : Int {
        return pair.first.getNumOfCorrectAnswers() + pair.second.getNumOfCorrectAnswers()
    }

    fun sortedByWins() : List<Player> {
        return plGroup.sortedByWins()
    }
    fun reset() {
        plGroup.resetAnswers()
        setTimer(Resources.getSystem().getInteger(R.integer.DefaultSeconds))
    }
    override fun toString(): String {
        return this.plGroup.toString()
    }

}