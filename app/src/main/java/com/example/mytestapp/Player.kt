package com.example.mytestapp

class Player(
    attack: Int,
    defense: Int,
    health: Int,
    damageRange: IntRange
) : Creature(attack, defense, health, damageRange) {

    override fun maxHealth(): Int {
        return 150
    }
}