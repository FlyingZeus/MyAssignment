package org.wit.mortalkombat.activities

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.wit.mortalkombat.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startButton.setOnClickListener {
            val intent =Intent(this,CharacterListActivity::class.java)
            //val intent2=MediaPlayer.create (this, R.raw.excellent_sound1)
            //intent2.start()
            startActivity(intent)


            //val button1 = findViewById (R.id.startButton) as Button
            //button1.setOnClickListener {
               // val mp = MediaPlayer.create (this, R.raw.excellent_sound)
               // mp.start ()

            }
        }
    }

