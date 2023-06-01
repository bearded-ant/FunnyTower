package com.minigames.funnytower

import android.net.Uri
import korlibs.image.color.Colors
import korlibs.io.async.launchImmediately
import korlibs.io.resources.ResourcesContainer
import korlibs.korge.animate.animate
import korlibs.korge.input.onClick
import korlibs.korge.resources.resourceBitmap
import korlibs.korge.scene.Scene
import korlibs.korge.view.Container
import korlibs.korge.view.anchor
import korlibs.korge.view.image
import korlibs.korge.view.position
import korlibs.korge.view.solidRect

//import com.soywiz.klock.seconds
//import com.soywiz.korge.animate.animate
//import com.soywiz.korge.input.onClick
//import com.soywiz.korge.resources.resourceBitmap
//import com.soywiz.korge.scene.Scene
//import com.soywiz.korge.view.*
//import com.soywiz.korim.color.Colors
//import com.soywiz.korio.async.launchImmediately
//import com.soywiz.korio.resources.ResourcesContainer
//import com.soywiz.korma.geom.Angle
//import com.soywiz.korma.interpolation.Easing

class CustomScene(
    private val widthParam: Int,
    private val heightParam: Int,
    val callback: () -> Unit
) : Scene() {

    //    val ResourcesContainer.korge_png by resourceBitmap(R.drawable.korge)
    val imgPath = Uri.parse("R.drawable.korge")
    val ResourcesContainer.korge_png by resourceBitmap(path = imgPath.path.toString())

    suspend fun Container.sceneInit() {

        solidRect(widthParam, heightParam, Colors.GREEN) {
            position(0, 0)
        }

        image(korge_png) {
            anchor(.5, .5)
            position(widthParam / 2, heightParam / 2)
            onClick {
                callback()
            }

            launchImmediately {
                animate(completeOnCancel = true) {
                    parallel {
                        sequence(looped = true) {
//                            rotateTo(Angle.Companion.fromDegrees(45), 1.5.seconds, Easing.EASE_IN_OUT_QUAD)
//                            rotateTo(Angle.Companion.fromDegrees(-45), 1.5.seconds, Easing.EASE_IN_OUT_QUAD)
                        }
                    }
                }
            }
        }
    }
}