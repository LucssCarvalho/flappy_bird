package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.RenderbleComponent
import com.game.sample.componets.StateComponent
import java.awt.Color

class RenderSystem(val batch: SpriteBatch, val camera: OrthographicCamera) : IteratingSystem(
    Family.all(
        RenderbleComponent::class.java,
        PositionComponent::class.java
    ).get(), 900
) {

    val list = mutableListOf<RenderbleComponent>()

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val renderble = GeneralMappers.cmRenderble.get(entity)
        val position = GeneralMappers.cmPosition.get(entity)

        renderble.positionX = position.positionX
        renderble.positionY = position.positionY

        list.add(renderble)
    }

    override fun update(deltaTime: Float) {

            list.clear()

            super.update(deltaTime)

            list.sortBy { it.order }

            camera.update()
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//        batch.projectionMatrix = camera.combined
            batch.begin()
            list.forEach { renderble ->
                batch.draw(
                    renderble.texture,
                    renderble.positionX,
                    renderble.positionY,
                    renderble.width,
                    renderble.height
                )
            }
            batch.end()
        }
}