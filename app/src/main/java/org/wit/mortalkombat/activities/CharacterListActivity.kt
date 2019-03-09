package org.wit.mortalkombat.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_character_list.*
import org.wit.mortalkombat.R
import org.wit.mortalkombat.main.MainApp

import kotlinx.android.synthetic.main.card_character.view.*

import org.wit.mortalkombat.models.CharacterModel



class CharacterListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CharacterAdapter(app.characters)
    }
}



class CharacterAdapter constructor(private var placemarks: List<CharacterModel>) : RecyclerView.Adapter<CharacterAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_character, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val character = placemarks[holder.adapterPosition]
        holder.bind(character)
    }

    override fun getItemCount(): Int = placemarks.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterModel) {
            itemView.mortalkombatTitle.text = character.title
            itemView.description.text = character.description
        }
    }
}