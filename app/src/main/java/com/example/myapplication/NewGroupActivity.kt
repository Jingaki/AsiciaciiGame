package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class NewGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group)

        val btnBack: Button = findViewById(R.id.btnBackNewGroup)
        btnBack.setOnClickListener {
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }

        val editText = findViewById<EditText>(R.id.newTeamName)
        val textViewError = findViewById<TextView>(R.id.emptyError)

        val numbers = resources.getStringArray(R.array.Numbers)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, numbers)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        autocompleteTV.setAdapter(arrayAdapter)

        var numberOfPlayers = ""
        autocompleteTV.setOnItemClickListener { parent, _, position, _ ->
            val selectedGroup = parent.getItemAtPosition(position)
            numberOfPlayers = selectedGroup.toString()
        }

        val btnNext: Button = findViewById(R.id.btnNextNewGroup)
        btnNext.setOnClickListener {
            if(editText.text.isEmpty()){
                textViewError.text = "Enter your team name!"
            }else {
                if (autocompleteTV.text.isEmpty()) {
                    textViewError.text = "Enter the number of players!"
                }else{
                    val groupName = editText.text.toString()


                    val bundle = Bundle()
                    bundle.putString("id", groupName)
                    bundle.putString("num", numberOfPlayers)
                    val intent = Intent(this, AddPlayersActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        }
    }
}