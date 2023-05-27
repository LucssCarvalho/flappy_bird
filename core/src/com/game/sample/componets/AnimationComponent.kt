package com.game.sample.componets

import com.badlogic.gdx.graphics.Texture
import com.badlogic.ashley.core.Component

class AnimationComponent: Component {
    val animation = mutableListOf<Texture>()
    var stateTime: Float = 0f
    var durationFrame: Float = 0f

    fun getKeyFrame(): Texture? {
        if (animation.size == 0) return null
        return animation[(stateTime / durationFrame).toInt() % animation.size]
    }
}