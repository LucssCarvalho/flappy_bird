package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.game.sample.componets.ControlComponent
import com.game.sample.componets.GravityComponent
import com.game.sample.componets.StateComponent
import com.game.sample.componets.StateComponent.*

class ControlSystem : IteratingSystem(
    Family.all(
        ControlComponent::class.java,
        GravityComponent::class.java
    ).get()
) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val gravity = GeneralMappers.cmGravity.get(entity)

        if (Gdx.input.justTouched()) {
            gravity.speedDrop = -15f
        }
    }
}