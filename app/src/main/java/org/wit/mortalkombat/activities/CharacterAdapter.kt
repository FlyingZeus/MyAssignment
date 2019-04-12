package org.wit.mortalkombat.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_character.view.*
import org.wit.mortalkombat.R
import org.wit.mortalkombat.helpers.readImageFromPath
import org.wit.mortalkombat.models.CharacterModel


interface CharacterListener {
    fun onCharacterClick(character: CharacterModel)
}

class CharacterAdapter constructor(private var characters: List<CharacterModel>,private val listener: CharacterListener) : RecyclerView.Adapter<CharacterAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_character, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val character = characters[holder.adapterPosition]
        holder.bind(character,listener)
    }

    fun filterList(findCharacter: ArrayList<CharacterModel>){
        this.characters = findCharacter
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characters.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterModel, listener: CharacterListener) {
            itemView.characterTitleList.text = character.title
            itemView.characterDescriptionList.text = character.description
            itemView.characterMoves.text = character.moves
            itemView.ratingbarOnCard.rating=character.rating
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, character.image))
            itemView.setOnClickListener { listener.onCharacterClick(character) }
        }





    }


}