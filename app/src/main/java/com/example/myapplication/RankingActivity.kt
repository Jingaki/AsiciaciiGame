package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.db.PlayerGroupViewModel
import com.example.myapplication.model.Player

private lateinit var mPlayerGroupViewModel: PlayerGroupViewModel
class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val btnMainMenu: Button = findViewById(R.id.btnMenu)
        btnMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val tvRangList = findViewById<TextView>(R.id.tvRanking)
        val tvListOfPoints = findViewById<TextView>(R.id.tvListOfPoints)
        var text = ""
        var num : Int
        val listOfPairs = DatabaseServiceProvider.db.getGame().sortedByAnswers()
        for(pair in listOfPairs){
            text = "$text \n ${pair.first} & ${pair.second}"
        }
        tvRangList.text = text

        text=""
        for(p in listOfPairs){
            num = DatabaseServiceProvider.db.getGame().pointsOfAPair(p)
            if(num ==DatabaseServiceProvider.db.getGame().pointsOfAPair(listOfPairs.first()) )

            //increase the number of wins and change the base
                 winsIncrease(p)
            text = "$text \n $num"
        }
        tvListOfPoints.text = text
        //resets number of correct answers
        DatabaseServiceProvider.db.getGame().getPlayerGroup().resetAnswers()
        //changing the base
        mPlayerGroupViewModel = ViewModelProvider(this)[PlayerGroupViewModel::class.java]
        updatePlayGroup()

    }

    private fun winsIncrease(pair: Pair<Player, Player>) {
        pair.first.winsIncrease()
        pair.second.winsIncrease()
    }
    private fun updatePlayGroup() {
        val updatedPlayerGroup = DatabaseServiceProvider.db.getGame().getPlayerGroup()
        mPlayerGroupViewModel.updatePlayerGroup(updatedPlayerGroup)
    }
}