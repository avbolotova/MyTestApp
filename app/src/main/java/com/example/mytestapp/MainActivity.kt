package com.example.mytestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val player = Player(20, 10, 100, 1..6)
        val monster = Monster(15, 8, 80, 2..8)
        var healCount = 0

        while (player.isAlive() && monster.isAlive()) {
            player.attack(monster)
            if (monster.isAlive()) {
                monster.attack(player)
            }
            if (healCount < 4) {
                player.heal(30.0)
                healCount++
            }
        }

        if (player.isAlive()) {
            println("Player wins!")
        } else {
            println("Monster wins!")
        }
    }
}