package org.wit.mortalkombat.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.mortalkombat.helpers.*
import java.util.*

val JSON_FILE = "characters.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CharacterModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CharacterJSONStore : CharacterStore, AnkoLogger {

    val context: Context
    var characters = mutableListOf<CharacterModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CharacterModel> {
        return characters
    }

    override fun create(placemark: CharacterModel) {
        placemark.id = generateRandomId()
        characters.add(placemark)
        serialize()
    }


    override fun update(character: CharacterModel) {
        val placemarksList = findAll() as ArrayList<CharacterModel>
        var foundCharacter: CharacterModel? = placemarksList.find { p -> p.id == character.id }
        if (foundCharacter != null) {
            foundCharacter.title = character.title
            foundCharacter.description = character.description
            foundCharacter.image = character.image
           // foundPlacemark.lat = character.lat
            //foundPlacemark.lng = character.lng
           // foundPlacemark.zoom = character.zoom
        }
        serialize()

    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(characters, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        characters = Gson().fromJson(jsonString, listType)
    }

    override fun delete(character: CharacterModel) {
        characters.remove(character)
        serialize()
    }
}