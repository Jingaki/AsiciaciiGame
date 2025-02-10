package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.model.Game
import com.example.myapplication.model.PlayerGroup

class ChooseGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_group)

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var currentPGroup = PlayerGroup("")

        // Get existing groups
        val existingGroups = DatabaseServiceProvider.db.getPlayerGroupNames()

        // Initialize Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)

        // Create ArrayAdapter for Spinner
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            existingGroups
        )

        // Set dropdown layout style
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set adapter to spinner
        spinner.adapter = arrayAdapter

        // Handle selection
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGroup = parent.getItemAtPosition(position)
                val groupName = selectedGroup.toString()

                val textView = findViewById<TextView>(R.id.teamMembers)
                textView.movementMethod = ScrollingMovementMethod()

                currentPGroup = DatabaseServiceProvider.db.getPlayerGroup(groupName)

                var text = ""
                for (player in currentPGroup.getPlayers()) {
                    text += player.getName() + "\n"
                }

                textView.text = text
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle nothing selected
            }
        }

        val TVError = findViewById<TextView>(R.id.chooseGroupError)

        val btnChoose: Button = findViewById(R.id.btnChooseThisGroup)
        btnChoose.setOnClickListener {
            if (spinner.selectedItem == null || spinner.selectedItem.toString().isEmpty()) {
                TVError.text = "Choose a team!"
            } else {
                val intent = Intent(this, GroupInfoActivity::class.java)
                val currentGame = Game()
                currentGame.setPlayerGroup(currentPGroup)
                DatabaseServiceProvider.db.setGame(currentGame)
                startActivity(intent)
            }
        }

        val btnNewGroup: Button = findViewById(R.id.btnNewGroup)
        btnNewGroup.setOnClickListener {
            val intent = Intent(this, NewGroupActivity::class.java)
            startActivity(intent)
        }
    }
}