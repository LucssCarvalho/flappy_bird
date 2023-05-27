package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.SliderComponent
import com.game.sample.componets.StateComponent
import com.game.sample.componets.StateComponent.*

class SliderSystem(private val state: StateComponent) :
    IteratingSystem(Family.all(SliderComponent::class.java, PositionComponent::class.java).get()) {

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val slider = GeneralMappers.cmSlider.get(entity)
        val position = GeneralMappers.cmPosition.get(entity)

        if (state.state == states.playing) {
            position.positionX -= slider.speed * deltaTime

            if (position.positionX < -slider.margin) {
                engine.removeEntity(entity)
            }

            if (!slider.setPoints && position.positionX < 100) {
                state.points++
                slider.setPoints = true
            }
        }
    }
}