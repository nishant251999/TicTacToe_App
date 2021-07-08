package com.nishant.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nishant.tictactoe.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {

    private lateinit var b : ActivityDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TicTacToe)
        b = ActivityDataBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnStartGame.setOnClickListener {
            if(b.etName1.text.toString().isNotEmpty() && b.etName2.text.toString().isNotEmpty()) {

                val name1 = b.etName1.text.toString()
                val name2 = b.etName2.text.toString()

                val sendIntent = Intent(this, MainActivity::class.java)
                sendIntent.putExtra("PLAYER_NAME_1", name1)
                sendIntent.putExtra("PLAYER_NAME_2", name2)
                startActivity(sendIntent)
            }
        }
    }
}