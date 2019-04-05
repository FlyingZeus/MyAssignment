package org.wit.mortalkombat.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CharacterMemStore : CharacterStore, AnkoLogger {

    val characters = ArrayList<CharacterModel>()

    override fun findAll(): List<CharacterModel> {

        return characters
    }

    override fun create(character: CharacterModel) {
        character.id = getId()
        characters.add(character)
        logAll()


    }

    override fun update(character: CharacterModel) {
        var foundCharacter: CharacterModel? = characters.find { p -> p.id == character.id }
        if (foundCharacter != null) {
            foundCharacter.title = character.title
            foundCharacter.description = character.description
            foundCharacter.image = character.image
            foundCharacter.moves = character.moves
            foundCharacter.rating= character.rating

            logAll()
        }



    }

    override fun delete(character: CharacterModel) {
        characters.remove(character)
    }



    fun logAll() {
        characters.forEach{ info("${it}") }
     }
}