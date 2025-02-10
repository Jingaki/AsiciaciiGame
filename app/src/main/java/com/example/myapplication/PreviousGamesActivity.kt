package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider

class PreviousGamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_games)

        val btnBack: Button = findViewById(R.id.btnBackPreviousGames)
        btnBack.setOnClickListener {
            val intent = Intent(this, GroupInfoActivity::class.java)
            startActivity(intent)
        }

        val tvPlayers = findViewById<TextView>(R.id.previousGamesList)
        val tvWins = findViewById<TextView>(R.id.tvListOfWins)
        var text = ""
        val sortedPlayers = DatabaseServiceProvider.db.getGame().sortedByWins()
        for (player in sortedPlayers)
            text = "$text \n ${player.getName()}"
        tvPlayers.text=text

        text=""
        for (player in sortedPlayers)
            text = "$text \n ${player.getNumOfWins()}"
        tvWins.text=text
    }
}