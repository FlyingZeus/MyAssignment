package org.wit.mortalkombat.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.mortalkombat.R

import kotlinx.android.synthetic.main.activity_mortal_kombat_maps.*
import kotlinx.android.synthetic.main.content_mortal_kombat_maps.*
import org.wit.mortalkombat.main.MainApp

class MortalKombatMapsActivity : AppCompatActivity() {
    lateinit var map: GoogleMap
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mortal_kombat_maps)
        setSupportActionBar(toolbarMaps)
        app = application as MainApp
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            map = it
            configureMap()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    fun configureMap() {
        map.uiSettings.setZoomControlsEnabled(true)
        app.characters.findAll().forEach {
            //val loc = LatLng(it.lat, it.lng)
            //val options = MarkerOptions().title(it.title).position(loc)
           // map.addMarker(options).tag = it.id
        }
    }
}


