package com.game.sample.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Texture
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.RenderbleComponent

object BackgroundEntity {
    fun create(
        engine: PooledEngine,
        startX: Float,
        startY: Float,
        backgroundTexture: Texture,
        width: Float,
        height: Float
    ): Entity {
        return engine.createEntity().apply {
            add(engine.createComponent(PositionComponent::class.java).apply {
                positionX = startX
                positionY = startY
            })
            add(engine.createComponent(RenderbleComponent::class.java).apply {
                texture = backgroundTexture
                this.width = width
                this.height = height
                order = 0
            })
            engine.addEntity(this)
        }
    }
}