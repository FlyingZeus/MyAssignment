package org.wit.mortalkombat.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(  var id: String = "",
                            var title: String = "",
                            var description: String = "",
                            var moves: String = "",
                            var rating: Float = 0.0f,
                            var image: String = "") : Parcelable{

}

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable


