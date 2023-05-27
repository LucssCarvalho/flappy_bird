package com.game.sample.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Texture
import com.game.sample.componets.AnimationComponent
import com.game.sample.componets.ControlComponent
import com.game.sample.componets.GravityComponent
import com.game.sample.componets.HealthComponent
import com.game.sample.componets.HealthComponent.componentStateTypes
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.RenderbleComponent
import com.game.sample.componets.StateComponent

object BirdEntity {

    fun create(
        engine: PooledEngine,
        startX: Float,
        startY: Float
    ): Entity {
        return engine.createEntity().apply {
            val animation = (engine.createComponent(AnimationComponent::class.java).apply {
                animation.addAll(
                    arrayOf(
                        Texture("passaro1.png"),
                        Texture("passaro2.png"),
                        Texture("passaro3.png")
                    )
                )
                durationFrame = 0.20f
            })
            add(animation)
            add(engine.createComponent(PositionComponent::class.java).apply {
                positionX = startX
                positionY = startY
            })
            add(engine.createComponent(RenderbleComponent::class.java).apply {
                texture = animation.animation[0]
                this.width = animation.animation[0].width.toFloat()
                this.height = animation.animation[0].height.toFloat()
                order = 2
            })
            add(engine.createComponent(GravityComponent::class.java))
            add(engine.createComponent(ControlComponent::class.java))

            engine.addEntity(this)
        }
    }
}