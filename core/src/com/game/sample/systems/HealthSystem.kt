package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.game.sample.componets.HealthComponent

class HealthSystem : IteratingSystem(
    Family.all(
        HealthComponent::class.java
    ).get()
) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {

    }
}