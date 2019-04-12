package org.wit.mortalkombat.main



import android.app.Application
import com.google.firebase.FirebaseApp
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.mortalkombat.models.CharacterJSONStore
import org.wit.mortalkombat.models.CharacterMemStore
import org.wit.mortalkombat.models.CharacterModel
import org.wit.mortalkombat.models.CharacterStore
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainApp : Application(), AnkoLogger {

    lateinit var characters: CharacterStore



    override fun onCreate() {
        super.onCreate()
        characters = CharacterJSONStore(applicationContext)
        info("Character Creater started")
        FirebaseApp.initializeApp(this)



        //characters.add(CharacterModel("One", "About one..."))
       // characters.add(CharacterModel("Two", "About two..."))
       // characters.add(CharacterModel("Three", "About three..."))
       // characters.add(CharacterModel("Three", "About three..."))
    }
}