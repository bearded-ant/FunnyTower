package com.minigames.funnytower

//import com.soywiz.korge.scene.Module
//import com.soywiz.korge.scene.Scene
//import com.soywiz.korinject.AsyncInjector
//import com.soywiz.korma.geom.SizeInt
import korlibs.inject.AsyncInjector
import korlibs.korge.android.KorgeAndroidView
import korlibs.korge.scene.Scene
import korlibs.math.geom.SizeInt
import kotlin.reflect.KClass

@Suppress("unused")
class CustomModule(private val width: Int = DEFAULT_WIDTH, private val height: Int = DEFAULT_HEIGHT, val callback: () -> Unit)  {

    companion object {
        const val DEFAULT_WIDTH = 1920
        const val DEFAULT_HEIGHT = 1080
    }

//    override val size: SizeInt
//        get() = SizeInt.invoke(width, height)
//
//    override val windowSize: SizeInt
//        get() = SizeInt.invoke(width, height)
//
//    override val mainScene: KClass<out Scene> = CustomScene::class
//
//    override suspend fun AsyncInjector.configure() {
//        mapPrototype { CustomScene(width, height, callback) }
//    }
}