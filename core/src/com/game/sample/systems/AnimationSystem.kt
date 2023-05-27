package com.game.sample.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.game.sample.componets.AnimationComponent
import com.game.sample.componets.RenderbleComponent

class AnimationSystem :
    IteratingSystem(
        Family.all(AnimationComponent::class.java,
        RenderbleComponent::class.java
        ).get(), 899
    ) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val animation = GeneralMappers.cmAnimation.get(entity)
        val renderble = GeneralMappers.cmRenderble.get(entity)

        animation.stateTime += deltaTime
        renderble.texture = animation.getKeyFrame()
    }
}