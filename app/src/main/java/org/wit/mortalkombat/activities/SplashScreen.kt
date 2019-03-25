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
        supportActionBar?.hide()

        startButton.setOnClickListener {
            val intent =Intent(this,CharacterListActivity::class.java)


            startActivity(intent)



            }
        }
    }

