package org.wit.mortalkombat.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.mortalkombat.models.CharacterModel
import org.wit.mortalkombat.R
import org.wit.mortalkombat.main.MainApp

class MainActivity : AppCompatActivity(), AnkoLogger {

    var character = CharacterModel()
    var app : MainApp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            character.title = mortalkombatTitle.text.toString()
            character.description = description.text.toString()
            if (character.title.isNotEmpty()) {
                app!!.characters.add(character.copy())
                info("add Button Pressed: $mortalkombatTitle")
                app!!.characters.forEach { info("add Button Pressed: ${it.title}")}
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}


