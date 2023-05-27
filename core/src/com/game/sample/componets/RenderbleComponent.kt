package com.game.sample.componets

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture

class RenderbleComponent : Component {
    var texture: Texture? = null
    var height: Float = 0f
    var width: Float = 0f
    var positionX: Float = 0f
    var positionY: Float = 0f
    var order: Int = 0
}