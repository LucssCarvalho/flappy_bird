package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Texture
import com.game.sample.componets.AnimationComponent
import com.game.sample.componets.RenderbleComponent
import com.game.sample.componets.StateComponent
import com.game.sample.componets.StateComponent.states.playing
import com.game.sample.componets.StateComponent.states.tap_to_start
import com.game.sample.entities.PipeBottomEntity
import com.game.sample.entities.PipeTopEntity
import java.util.Random

class PipeGeneratorSystem(
    val sizeWidth: Float,
    val sizeHeight: Float,
    val texturePipeBottom: Texture,
    val texturePipeTop: Texture
) :
    IteratingSystem(
        Family.all(
            StateComponent::class.java
        ).get()
    ) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val state = GeneralMappers.cmState.get(entity)

        if (state.state == playing) {
            state.pipeGenerator -= deltaTime

            val randomValue = Random()
            val randomSpaceBetweenPipes = randomValue.nextInt(400) - 200
            val spaceBetweenPipes = 300

            if (state.pipeGenerator < 0) {
                state.pipeGenerator += 3
                PipeTopEntity.create(
                    engine as PooledEngine,
                    sizeWidth,
                    sizeHeight / 2 + spaceBetweenPipes / 2 + randomSpaceBetweenPipes,
                    texturePipeTop
                )
                PipeBottomEntity.create(
                    engine as PooledEngine,
                    sizeWidth,
                    sizeHeight / 2 - texturePipeBottom.height - spaceBetweenPipes / 2 + randomSpaceBetweenPipes,
                    texturePipeBottom
                )
            }
        }
    }
}