package org.wit.mortalkombat.main



import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.mortalkombat.models.CharacterModel

class MainApp : Application(), AnkoLogger {

    val characters = ArrayList<CharacterModel>()

    override fun onCreate() {
        super.onCreate()
        info("Character Creater started")
        //characters.add(CharacterModel("One", "About one..."))
       // characters.add(CharacterModel("Two", "About two..."))
       // characters.add(CharacterModel("Three", "About three..."))
       // characters.add(CharacterModel("Three", "About three..."))
    }
}