package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)


        val btnBack: Button = findViewById(R.id.btnBackInstructions)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val textView = findViewById<TextView>(R.id.description)
        textView.setMovementMethod(ScrollingMovementMethod())
        textView.text = "Game Setting\n" +
                "In the beginning, enter the name you want your group to bear. Then enter the number of players your group contains (must be an even number). Enter the names of the group members and then select the name of your group from the start menu. Pairs are randomly selected, but you have the freedom to change it. Once you choose the number of words per player and the number of seconds to guess, you are ready to play!\n" +
                "\nWord entry\n" +
                "It says on the screen which player enters the words. When he finishes, he gives the phone to the player whose name is written on the screen. Only when all the players have entered the words, it will be written on the screen which player starts the game.\n" +
                "\nPhases\n" +
                "Phases change when all terms are explained.\n" +
                "First Stage (Free Explaining) - The explaining player can talk to their partner as much as they want about the concept on the screen.\n" +
                "Second phase (One word) - The explaining player tries to explain the given term to the partner by pantomime.\n" +
                "Third phase (Pantomime) - The explaining player may only use one word when explaining, after which he does not say anything until his partner guesses the term.\n" +
                "\nGame over\n" +
                "After the third stage, the hit words are added up in pairs and the pair with the most hit words wins."
    }

}