package org.wit.mortalkombat.activities

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.mortalkombat.models.CharacterModel

import org.wit.mortalkombat.helpers.readImage
import org.wit.mortalkombat.helpers.readImageFromPath
import org.wit.mortalkombat.main.MainApp
import org.wit.mortalkombat.helpers.showImagePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.wit.mortalkombat.R


class MainActivity : AppCompatActivity(), AnkoLogger {

    var character = CharacterModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp
        setTitle(R.string.add_character_title)

        //app.reference.setValue("Hello, World!")


        if (intent.hasExtra("character_edit"))
        {
            setTitle(R.string.view_edit_character)
            edit = true
            character = intent.extras.getParcelable<CharacterModel>("character_edit")
            characterTitle.setText(character.title)
            characterDescription.setText(character.description)
            characterImage.setImageBitmap(readImageFromPath(this, character.image))

            if (character.image != null) {
                chooseImage.setText(R.string.button_changeImage)
            }

            btnAdd.setText(R.string.button_saveCharacter)
            //chooseImage.setText(R.string.button_changeImage)
        }

        btnAdd.setOnClickListener() {
            character.title = characterTitle.text.toString()
            character.description = characterDescription.text.toString()
            character.moves = moves.text.toString()
            character.rating = ratingbar.rating

            if (character.title.isNotEmpty()) {

                if (edit){
                    app.characters.update(character.copy())
                }
                else {
                    app.characters.create(character.copy())
                }
                info("Add Button Pressed. name: ${character.title}")
                setResult(AppCompatActivity.RESULT_OK)

                val intent2= MediaPlayer.create (this, R.raw.excellent_sound)
                intent2.start()


                finish()
            }
            else {
                toast (R.string.enter_character_title)
            }


        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
        //toolbarAdd.title = title
        //setSupportActionBar(toolbarAdd)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_character, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.characters.delete(character)
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
        }
    }




}



