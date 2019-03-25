package org.wit.mortalkombat.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_character_list.*
import org.wit.mortalkombat.R
import org.wit.mortalkombat.main.MainApp

import kotlinx.android.synthetic.main.card_character.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

import org.wit.mortalkombat.models.CharacterModel
class CharacterListActivity : AppCompatActivity(),CharacterListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadPlacemarks()
        //recyclerView.adapter = CharacterAdapter(app.characters.findAll(), this)
        setTitle(R.string.character_list_title)
        //setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(0)
        }

        when (item?.itemId) {
            R.id.wiki -> startActivityForResult<CharacterWiki>(0)
        }
        return super.onOptionsItemSelected(item)


    }

    override fun onCharacterClick(character: CharacterModel) {
        startActivityForResult(intentFor<MainActivity>().putExtra("character_edit", character), 0)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadPlacemarks()
        //recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadPlacemarks() {
        showPlacemarks(app.characters.findAll())
    }

    fun showPlacemarks (characters: List<CharacterModel>) {
        recyclerView.adapter = CharacterAdapter(characters, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


}



