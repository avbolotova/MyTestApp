package com.example.mytestapp

open class Creature(
    val attack: Int,
    val defense: Int,
    var health: Int,
    val damageRange: IntRange
) {
    init {
        require(attack in 1..30) { "Attack should be in the range 1-30" }
        require(defense in 1..30) { "Defense should be in the range 1-30" }
        require(health >= 0) { "Health should be non-negative" }
        require(damageRange.start >= 1 && damageRange.endInclusive <= 30) {
            "Damage range should be in the range 1-30"
        }
    }

    fun isAlive() = health > 0

    fun takeDamage(damage: Int) {
        require(damage >= 0) { "Damage should be non-negative" }
        health -= damage
        if (health < 0) {
            health = 0
        }
    }

    fun heal(healingPercentage: Double) {
        require(healingPercentage >= 0 && healingPercentage <= 100) { "Healing percentage should be between 0 and 100" }
        val maxHealing = (maxHealth() - health) * healingPercentage / 100
        health += maxHealing.toInt()
        if (health > maxHealth()) {
            health = maxHealth()
        }
    }

    open fun maxHealth(): Int {
        return 100
    }

    fun calculateModifier(defender: Creature): Int {
        val modifier = attack - defender.defense + 1
        require(modifier >= 1) { "Invalid modifier, attacker's attack and defender's defense values are too close." }
        return modifier
    }

    fun rollDice(modifier: Int): Boolean {
        require(modifier >= 1) { "Invalid modifier, it should be at least 1." }
        val random = java.util.Random()
        return (1..modifier).any { random.nextInt(6) + 1 >= 5 }
    }

    open fun attack(target: Creature) {
        val modifier = calculateModifier(target)
        if (modifier < 1) {
            throw IllegalArgumentException("Invalid modifier, attacker's attack and defender's defense values are too close.")
        }
        if (damageRange.start < 1 || damageRange.endInclusive > 30) {
            throw IllegalArgumentException("Invalid damage range, it should be in the range 1-30.")
        }

        if (rollDice(modifier)) {
            val damage = (damageRange).random()
            target.takeDamage(damage)
        }
    }
}
