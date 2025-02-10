package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.db.PlayerGroupViewModel
import com.example.myapplication.model.Player
import com.example.myapplication.model.PlayerGroup

var firstTimeIn: Boolean = true

class MainActivity : AppCompatActivity() {
    private lateinit var mPlayerGroupViewModel: PlayerGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay: Button = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener {
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }
        val btnInstruction: Button = findViewById(R.id.btnInstructions)
        btnInstruction.setOnClickListener {
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }


        mPlayerGroupViewModel = ViewModelProvider(this)[PlayerGroupViewModel::class.java]
        mPlayerGroupViewModel.readAllData.observe(this) { playergroups ->
            DatabaseServiceProvider.db.setPlayerGroups(
                playergroups as MutableList<PlayerGroup>
            )
        }
    }
}