package org.wit.mortalkombat.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.SearchView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_character_list.*
import org.wit.mortalkombat.R
import org.wit.mortalkombat.main.MainApp

import kotlinx.android.synthetic.main.card_character.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.mortalkombat.main.Statics

import org.wit.mortalkombat.models.CharacterModel
class CharacterListActivity : AppCompatActivity(),CharacterListener {

    lateinit var app: MainApp
    var characterAdapter: CharacterAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Characters")

        app = application as MainApp
        Statics.characterList = ArrayList()

        for(chars in app.characters.findAll()){
            Statics.characterList!!.add(chars)
        }
        getCharactersFromDatabase()
//        layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//        characterAdapter = CharacterAdapter(Statics.characterList!!, this@CharacterListActivity)
//        Log.i("TAG", "${Statics.characterList!!.size}")
//        recyclerView.adapter = characterAdapter
//        recyclerView.adapter?.notifyDataSetChanged()
//        //recyclerView.adapter = CharacterAdapter(characters, this)
//        recyclerView.adapter?.notifyDataSetChanged()
        //loadCharacters()
        //recyclerView.adapter = CharacterAdapter(app.characters.findAll(), this)
        setTitle(R.string.character_list_title)
        //setSupportActionBar(toolbarMain)

        search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(search: Editable?) {
                filterList(search.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                null
            }
        })

    }

    fun getCharactersFromDatabase(){
        database!!.reference.child("Characters").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    Statics.characterList!!.clear()//to stop duplication
                    for (character in dataSnapshot.children) { //looping through infoon firebase
                        val  myChars = character.getValue(CharacterModel::class.java)
                        Statics.characterList!!.add(myChars!!)//adds database back to list
                        layoutManager = LinearLayoutManager(applicationContext)
                        recyclerView.layoutManager = layoutManager
                        characterAdapter = CharacterAdapter(Statics.characterList!!, this@CharacterListActivity)
                        recyclerView.adapter = characterAdapter
                        recyclerView.adapter?.notifyDataSetChanged()



                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                toast("Failed to retrieve information from database.").show()
            }
        }
        )
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)



        return true


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(0)
            R.id.item_map -> startActivity<MortalKombatMapsActivity>()
        }

        when (item?.itemId) {
            R.id.wiki -> startActivityForResult<CharacterWiki>(0)
        }
        return super.onOptionsItemSelected(item)


    }

    override fun onCharacterClick(character: CharacterModel) {
        startActivityForResult(intentFor<MainActivity>().putExtra("character_edit", character), 0)
    }



//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        loadCharacters()
//        recyclerView.adapter?.notifyDataSetChanged()
//        super.onActivityResult(requestCode, resultCode, data)
//    }
//
//    private fun loadCharacters() {
//        showCharacters(app.characters.findAll())
//        //Log.i("Chars", app.characters.findAll().get(0).title)
//    }
//    fun showCharacters(characters: List<CharacterModel>) {
//        recyclerView.adapter = CharacterAdapter(characters, this)
//        recyclerView.adapter?.notifyDataSetChanged()
//    }


    fun filterList(text: String) {
        var fliterCharacterArray: ArrayList<CharacterModel> = ArrayList()
        for (character in Statics.characterList!!) {
            if (character.title!!.toLowerCase().contains(text.toLowerCase())) {
                fliterCharacterArray.add(character)
                characterAdapter!!.filterList(fliterCharacterArray)
            }
        }
    }


}


