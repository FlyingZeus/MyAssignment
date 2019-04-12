package org.wit.mortalkombat.activities

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_character.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.mortalkombat.models.CharacterModel
import org.wit.mortalkombat.R
import org.wit.mortalkombat.helpers.readImage
import org.wit.mortalkombat.helpers.readImageFromPath
import org.wit.mortalkombat.main.MainApp
import org.wit.mortalkombat.helpers.showImagePicker
import org.wit.mortalkombat.main.Statics
import org.wit.mortalkombat.models.Location

class MainActivity : AppCompatActivity(), AnkoLogger {

    var character = CharacterModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var location = Location(52.245696, -7.139102, 15f)
    var charList:CharacterModel?=null
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp
        setTitle(R.string.add_character_title)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Characters")

        if (intent.hasExtra("character_edit")) {
            setTitle(R.string.view_edit_character)
            edit = true
            if (edit) {
                btnAdd.visibility = View.GONE
                btnUpdate.visibility = View.VISIBLE
            }
            character = intent.extras.getParcelable<CharacterModel>("character_edit")
            characterTitle.setText(character.title)
            characterDescription.setText(character.description)
            moves.setText(character.moves)
            characterImage.setImageBitmap(readImageFromPath(this, character.image))

            if (character.image != null) {
                chooseImage.setText(R.string.button_changeImage)
            }

            btnAdd.setText(R.string.button_saveCharacter)
            edit = true

        }

        btnAdd.setOnClickListener() {
            character.title = characterTitle.text.toString()
            character.description = characterDescription.text.toString()
            character.moves = moves.text.toString()
            character.rating = ratingbar.rating
            character.image = character.image


            if (!character.title.isEmpty()) {


                   saveToFirebase()
                    //app.characters.update(character.copy())

                // else {
                //    saveToFirebase()
                //app.characters.create(character.copy())
                // }
                //info("Add Button Pressed. name: ${character.title}")
                // setResult(AppCompatActivity.RESULT_OK)
                // val intent2= MediaPlayer.create (this, R.raw.excellent_sound)
                //  intent2.start()
                // finish()
            } else {
                toast(R.string.enter_character_title)
            }

            info("Add Button Pressed. name: ${character.title}")
            setResult(AppCompatActivity.RESULT_OK)
            val intent2 = MediaPlayer.create(this, R.raw.excellent_sound)
            intent2.start()
            finish()


        }

        btnUpdate.setOnClickListener {
            if(edit){
                app.characters.update(character.copy())
                 charList = CharacterModel(character.id, characterTitle.text.toString(), characterDescription.text.toString(),
                     characterMoves.text.toString(), ratingbar.rating, character.image)
                database!!.reference.child("Characters").child(character.id).setValue(charList)
                finish()
            }
        }



        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        characterOrigin.setOnClickListener {

            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

        //toolbarAdd.title = title
        //setSupportActionBar(toolbarAdd)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_character, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                myRef!!.child(character.id).removeValue()
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    character.image = data.getData().toString()
                    characterImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.button_changeImage)


                }
            }

            LOCATION_REQUEST -> {
                if (data != null) {
                    location = data.extras.getParcelable<Location>("location")


                }
            }


        }
    }

    fun updateFirebase()
    {


                app.characters.update(charList!!.copy())
                var myCharacters = CharacterModel(
                    charList!!.id,
                    characterTitle.text.toString(),
                    characterDescription.text.toString(),
                    characterMoves.text.toString(),
                    ratingbar.rating,
                    charList!!.image
                )
                database!!.reference.child("Characters").child(charList!!.id).setValue(myCharacters)
                finish()
            }



    fun saveToFirebase(){
        val chars = database!!.getReference("Characters").push().key
        charList = CharacterModel(chars!!, characterTitle.text.toString(), characterDescription.text.toString(),
             moves.text.toString(),ratingbar.rating, character.image)
        myRef!!.child(chars!!).setValue(charList)
    }
}



