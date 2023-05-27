package com.game.sample.systems

import com.badlogic.ashley.core.ComponentMapper
import com.game.sample.componets.AnimationComponent
import com.game.sample.componets.GravityComponent
import com.game.sample.componets.HealthComponent
import com.game.sample.componets.PositionComponent
import com.game.sample.componets.RenderbleComponent
import com.game.sample.componets.SliderComponent
import com.game.sample.componets.StateComponent

object GeneralMappers {
    val cmPosition: ComponentMapper<PositionComponent> =
        ComponentMapper.getFor(PositionComponent::class.java)

    val cmGravity: ComponentMapper<GravityComponent> =
        ComponentMapper.getFor(GravityComponent::class.java)

    val cmRenderble: ComponentMapper<RenderbleComponent> =
        ComponentMapper.getFor(RenderbleComponent::class.java)

    val cmAnimation: ComponentMapper<AnimationComponent> =
        ComponentMapper.getFor(AnimationComponent::class.java)

    val cmSlider: ComponentMapper<SliderComponent> =
        ComponentMapper.getFor(SliderComponent::class.java)

    val cmState: ComponentMapper<StateComponent> =
        ComponentMapper.getFor(StateComponent::class.java)

    val cmHealth: ComponentMapper<HealthComponent> =
        ComponentMapper.getFor(HealthComponent::class.java)
}