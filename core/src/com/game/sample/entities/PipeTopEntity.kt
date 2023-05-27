package com.game.sample.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Texture
import com.game.sample.componets.AnimationComponent
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.RenderbleComponent
import com.game.sample.componets.SliderComponent

object PipeTopEntity {

    fun create(engine: PooledEngine, startX: Float, startY: Float, texturePipe: Texture): Entity {
        return engine.createEntity().apply {
            add(engine.createComponent(PositionComponent::class.java).apply {
                positionX = startX
                positionY = startY
            })
            add(engine.createComponent(RenderbleComponent::class.java).apply {
                texture = texturePipe
                order = 1
                width = texturePipe.width.toFloat()
                height = texturePipe.height.toFloat()
            })
            add(engine.createComponent(SliderComponent::class.java).apply {
                speed = 200f
                margin = texturePipe.width
                setPoints = false
            })
            engine.addEntity(this)
        }
    }
}