package com.nishant.tictactoe

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.nishant.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var turn = true
    lateinit var board : Array<Button>
    var status = arrayOf(
        intArrayOf(-1,-1,-1),
        intArrayOf(-1,-1,-1),
        intArrayOf(-1,-1,-1)
    )
    var count = 0
    lateinit var name1: String
    lateinit var name2: String

    lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnReset.setOnClickListener {
            initializeBoard()
        }
        name1 = intent.getStringExtra("PLAYER_NAME_1").toString()
        name2 = intent.getStringExtra("PLAYER_NAME_2").toString()

        b.tvTurn.text = "$name1's turn"

        board = arrayOf(b.iv00,b.iv01,b.iv02,b.iv10,b.iv11,b.iv12,b.iv20,b.iv21,b.iv22)
        for(i in board.indices) {
            val iv = board[i]
            iv.setOnClickListener {
                updateValue(iv, i)
                val winner = if(ifGameOver(0)==0) 0 else if(ifGameOver(1)==1) 1 else -1
                if(winner != -1) {
                    val str = if(winner==0) name1 else name2
                    b.tvTurn.text = "Game Over \n$str Wins"
                    disableBoard()
                }
                if(count==9 && winner == -1) {
                    b.tvTurn.text = "Game Drawn"
                }
            }
        }
    }

    private fun disableBoard() {
        for(iv in board)
            iv.isEnabled = false
    }

    private fun ifGameOver(num: Int): Int {
        //checking horizontal lines
        for(i in 0..2) {
            var flag = true
            for(j in 0..2) {
                if(status[i][j] != num)
                    flag = false
            }
            if(flag)    return num
        }
        //checking vertical lines
        for(i in 0..2) {
            var flag = true
            for(j in 0..2) {
                if(status[j][i] != num)
                    flag = false
            }
            if(flag)    return num
        }
        //checking diagonals
        if(status[0][0] == num && status[1][1] == num && status[2][2] == num)
            return num
        if(status[0][2] == num && status[1][1] == num && status[2][0] == num)
            return num
        return -1
    }
    private fun updateValue(iv: Button, i: Int) {
        val num = if(turn)  0 else 1
        when(i) {
            0 -> {
                status[0][0] = num
            }
            1 -> {
                status[0][1] = num
            }
            2 -> {
                status[0][2] = num
            }
            3 -> {
                status[1][0] = num
            }
            4 -> {
                status[1][1] = num
            }
            5 -> {
                status[1][2] = num
            }
            6 -> {
                status[2][0] = num
            }
            7 -> {
                status[2][1] = num
            }
            8 -> {
                status[2][2] = num
            }
        }
        if(turn) {
            iv.setBackgroundColor(getColor(R.color.green))
            b.tvTurn.text = "$name2's turn"
            iv.text = "X"
        } else {
            iv.setBackgroundColor(getColor(R.color.red))
            b.tvTurn.text = "$name1's turn"
            iv.text = "O"
        }
        iv.isEnabled = false
        turn = !turn
        count++
    }

    private fun initializeBoard() {
        b.tvTurn.text = "$name1's turn"
        turn = true
        count = 0
        for(i in status) {
            for(j in i.indices) {
                i[j] = -1
            }
        }
        for(iv in board) {
            iv.setBackgroundColor(getColor(R.color.violet))
            iv.isEnabled = true
            iv.text = ""
        }
    }
}

