    package com.dermansenel.catchtheabyideral

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import com.dermansenel.catchtheabyideral.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageVisible()
        object : CountDownTimer(10000, 1000,) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textView2.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Restart?")
                alert.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()
                    }
                })
                alert.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        binding.textView2.text = "Time: Off"
                        Toast.makeText(this@MainActivity, "GameOver", Toast.LENGTH_LONG).show()
                    }
                })
                alert.show()
            }

        }.start()
        funImage(arrayOf(binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9), binding.root)



    }
    fun imageVisible(){
         val imageViews: Array<ImageView> = arrayOf(
            binding.imageView,
            binding.imageView2,
            binding.imageView3,
            binding.imageView4,
            binding.imageView5,
            binding.imageView6,
            binding.imageView7,
            binding.imageView8,
            binding.imageView9
        )
        for (imageview in imageViews ){
            imageview.visibility = View.INVISIBLE
        }
    }
    fun funImage(imageViews: Array<ImageView>, view: View) {
        val handler = Handler()
        var score = 0


        for (imageView in imageViews) {
            imageView.setOnClickListener {
                score++
                binding.textView.text = "Score: $score"
            }
        }

        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val randomInt = Random.nextInt(0, 9)
                imageViews[randomInt].visibility = View.VISIBLE
                handler.postDelayed({ imageViews[randomInt].visibility = View.INVISIBLE }, 500)
            }

            override fun onFinish() {

            }
        }.start()
    }


}


