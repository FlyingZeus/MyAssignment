package org.wit.mortalkombat.models

interface CharacterStore {
    fun findAll(): List<CharacterModel>
    fun create(character: CharacterModel)
    fun update(character: CharacterModel)
    fun delete(character: CharacterModel)
}