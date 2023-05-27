package com.game.sample

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.game.sample.componets.StateComponent
import com.game.sample.entities.BackgroundEntity
import com.game.sample.entities.BirdEntity
import com.game.sample.entities.PipeBottomEntity
import com.game.sample.entities.PipeTopEntity
import com.game.sample.systems.AnimationSystem
import com.game.sample.systems.ControlSystem
import com.game.sample.systems.GravitySystem
import com.game.sample.systems.PipeGeneratorSystem
import com.game.sample.systems.RenderSystem
import com.game.sample.systems.SliderSystem
import com.game.sample.systems.StateSystem

class Main : Game() {

    val engine = PooledEngine()

    lateinit var batch: SpriteBatch

    var camera = OrthographicCamera()

    var width = 0
    var height = 0

    val state = StateComponent()

    val viewport = StretchViewport(0f, 0f, camera)

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        this.width = width
        this.height = height
        viewport.update(width, height)
        camera.position.set(width.toFloat(), height.toFloat(), 0f)
    }

    override fun create() {
        batch = SpriteBatch()

        engine.addSystem(StateSystem())
        engine.addSystem(AnimationSystem())
        engine.addSystem(ControlSystem())
        engine.addSystem(GravitySystem(state))
        engine.addSystem(
            PipeGeneratorSystem(
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat(),
                Texture("cano_baixo.png"),
                Texture("cano_topo.png")
            )
        )
        engine.addSystem(RenderSystem(batch, camera))
        engine.addSystem(SliderSystem(state))

        BackgroundEntity.create(
            engine,
            0f,
            0f,
            Texture("fundo.png"),
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
        BirdEntity.create(engine, 100f, (Gdx.graphics.height.toFloat() / 2))
        engine.createEntity().apply {
            add(state)
            engine.addEntity(this)
        }
    }

    override fun render() {
        super.render()
        engine.update(Gdx.graphics.deltaTime)
    }

}
