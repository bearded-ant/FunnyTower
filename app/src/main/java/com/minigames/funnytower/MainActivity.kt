package com.minigames.funnytower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minigames.funnytower.databinding.ActivityMainBinding
import com.minigames.funnytower.game.Board
import korlibs.korge.android.KorgeAndroidView

class MainActivity : AppCompatActivity() {
    private lateinit var korgeAndroidView: KorgeAndroidView
    private var board = Board(3)
    private lateinit var binding: ActivityMainBinding
    private var choice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        korgeAndroidView = KorgeAndroidView(this)
        binding.toolContainer.addView(korgeAndroidView)

        binding.loadViewButton.setOnClickListener {
            if (binding.choice.text.toString().isNotBlank()) {
                choice = binding.choice.text.toString().toInt()
                board = board.placePiece('x', choice)
            }
            binding.arrayView.text = board.toArray().toString()

//            Toast.makeText(this, "${board.toArray()}", Toast.LENGTH_LONG).show()

//            binding.loadViewButton.isEnabled = false
//            binding.unloadViewButton.isEnabled = true
//            loadToolModule()
        }

        binding.unloadViewButton.setOnClickListener {
            binding.loadViewButton.isEnabled = true
            binding.unloadViewButton.isEnabled = false
            unloadToolModule()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.loadViewButton.isEnabled = true
        binding.unloadViewButton.isEnabled = false

    }

    override fun onPause() {
        super.onPause()
        unloadToolModule()
    }
//
//    private fun loadToolModule() {
//        korgeAndroidView.loadModule(CustomModule(width = 1920, height = 1080, callback = {
//            println("Callback from android app")
//        }))
//    }

    private fun unloadToolModule() {
        korgeAndroidView.unloadModule()
    }
}