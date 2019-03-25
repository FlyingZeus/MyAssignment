package org.wit.mortalkombat.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(  var image: String = "",
                            var id: Long = 0,
                            var moves: String = "",
                            var rating: Float = 0.0f,
                            var title: String = "",
                            var description: String = "") : Parcelable{

}