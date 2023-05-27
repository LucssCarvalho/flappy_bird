package com.game.sample.componets

import com.badlogic.ashley.core.Component

class StateComponent: Component {
    enum class states {
        playing,
        tap_to_start,
        pause,
        lose
    }

    var pipeGenerator = 0f
    var state = states.tap_to_start
    var points: Int = 0
}