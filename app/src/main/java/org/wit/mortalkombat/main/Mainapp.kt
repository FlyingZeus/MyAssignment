package org.wit.mortalkombat.main



import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.mortalkombat.models.CharacterJSONStore
import org.wit.mortalkombat.models.CharacterMemStore
import org.wit.mortalkombat.models.CharacterModel
import org.wit.mortalkombat.models.CharacterStore

class MainApp : Application(), AnkoLogger {

    lateinit var characters: CharacterStore
    lateinit var database: FirebaseDatabase
    lateinit var reference: DatabaseReference
    override fun onCreate() {
        super.onCreate()
        //FirebaseApp.initializeApp(this);
        characters = CharacterJSONStore(applicationContext)
        info("Character Creater started")
        //database = FirebaseDatabase.getInstance()
        //reference = database.getReference("DAve")
        //characters.add(CharacterModel("One", "About one..."))
       // characters.add(CharacterModel("Two", "About two..."))
       // characters.add(CharacterModel("Three", "About three..."))
       // characters.add(CharacterModel("Three", "About three..."))
    }
}