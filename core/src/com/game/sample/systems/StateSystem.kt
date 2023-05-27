package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.game.sample.componets.HealthComponent
import com.game.sample.componets.HealthComponent.*
import com.game.sample.componets.StateComponent
import com.game.sample.componets.StateComponent.states

class StateSystem :
    IteratingSystem(
        Family.all(
            StateComponent::class.java
        ).get()
    ) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val state = GeneralMappers.cmState.get(entity)


        if (Gdx.input.justTouched() && state.state == states.tap_to_start) {
            state.state = states.playing
        }
    }
}