package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.model.Game

class GroupInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        val btnBack: Button = findViewById(R.id.btnBackGroupInfo)
        btnBack.setOnClickListener{
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }

        val tVListOfPairs = findViewById<TextView>(R.id.listOfPairs)
        val numbers = resources.getStringArray(R.array.NumOfWords)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, numbers)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView3)
        autocompleteTV.setAdapter(arrayAdapter)
        //-----------------------------------------------------------
        val seconds = resources.getStringArray(R.array.Seconds)
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_item, seconds)
        val autocompleteTV2 = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView4)
        autocompleteTV2.setAdapter(arrayAdapter2)

        val game:Game = DatabaseServiceProvider.db.getGame()
        game.makeRandomPairs()
        var text =""

        for (p in game.getPairs()){
            text = "$text \n ${p.first} & ${p.second}"
        }
        tVListOfPairs.text = text


        autocompleteTV.setOnItemClickListener { parent, _, position, _ ->
            val selectedNumOfWords = parent.getItemAtPosition(position)
            val numOfWords = selectedNumOfWords.toString().toInt()
            game.setNumOfWordsPerPlayer(numOfWords)
        }

        autocompleteTV2.setOnItemClickListener { parent, _, position, _ ->
            val selectedSeconds = parent.getItemAtPosition(position)
            val seconds = selectedSeconds.toString().toInt()
            game.setTimer(seconds)
            DatabaseServiceProvider.db.getGame().setTimer(seconds)
        }

        val tvError = findViewById<TextView>(R.id.numOfWordsError)
        val btnStart: Button = findViewById(R.id.btnStart)
        btnStart.setOnClickListener{
            if(autocompleteTV2.text.toString().isEmpty()){
                val defaultSeconds = resources.getInteger(R.integer.DefaultSeconds)
                DatabaseServiceProvider.db.getGame().setTimer(defaultSeconds)
            }
            if(autocompleteTV.text.toString().isEmpty()){
                tvError.text = "Select a line number!"
            }else{
                val intent = Intent(this, WordsEnteringActivity::class.java)
                startActivity(intent)
            }
        }


        val btnPreviousGames: Button = findViewById(R.id.btnPreviousGames)
        btnPreviousGames.setOnClickListener{
            val intent = Intent(this, PreviousGamesActivity::class.java)
            startActivity(intent)
        }

        val btnChangePairs: Button = findViewById(R.id.btnChangePairs)
        btnChangePairs.setOnClickListener{
            game.makeRandomPairs()
            text=""
            for (p in game.getPairs()){
                text = "$text \n ${p.first} & ${p.second}"
            }
            tVListOfPairs.text = text
        }
    }
}