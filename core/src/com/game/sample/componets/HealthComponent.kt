package com.game.sample.componets

import com.badlogic.ashley.core.Component

class HealthComponent : Component {

    enum class componentStateTypes {
        alive,
        dead,
        invincible
    }

   var componentState = componentStateTypes.dead
}