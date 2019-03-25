package org.wit.mortalkombat.main



import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.mortalkombat.models.CharacterJSONStore
import org.wit.mortalkombat.models.CharacterMemStore
import org.wit.mortalkombat.models.CharacterModel
import org.wit.mortalkombat.models.CharacterStore

class MainApp : Application(), AnkoLogger {

    lateinit var characters: CharacterStore

    override fun onCreate() {
        super.onCreate()
        characters = CharacterJSONStore(applicationContext)
        info("Character Creater started")
        //characters.add(CharacterModel("One", "About one..."))
       // characters.add(CharacterModel("Two", "About two..."))
       // characters.add(CharacterModel("Three", "About three..."))
       // characters.add(CharacterModel("Three", "About three..."))
    }
}