package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.game.sample.componets.GravityComponent
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.StateComponent

class GravitySystem(private val state: StateComponent) : IteratingSystem(
    Family.all(
        PositionComponent::class.java,
        GravityComponent::class.java
    ).get()
) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = GeneralMappers.cmPosition.get(entity)
        val gravity = GeneralMappers.cmGravity.get(entity)

        if (state.state == StateComponent.states.playing) {
            gravity.speedDrop++
            position.positionY = position.positionY - gravity.speedDrop
        }
    }
}