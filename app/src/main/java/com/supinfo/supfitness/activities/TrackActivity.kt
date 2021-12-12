package com.supinfo.supfitness.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior.getTag
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.supinfo.supfitness.R


class TrackActivity : AppCompatActivity(), OnMapReadyCallback {


    private var currentLocation: Location? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val permissionCode = 101
    private var isRunning: Boolean = false

    var mainHandler: Handler? = null

    private var updateTextTask = object : Runnable {
        override fun run() {
            fetchLocation()
            mainHandler?.postDelayed(this, 2500)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        // Get current location
        //Handler that focus the map on your position
        mainHandler = Handler(Looper.getMainLooper())
        // Call move to current location fab button
        val fetchLocationButton: FloatingActionButton = findViewById(R.id.fabFetchLocation)
        // If tested on emulator, be aware you need to select an emulator with playstore activated and set a position by default
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        //Get current location on demand
        fetchLocationButton.setOnClickListener {
            fetchLocation()
            Log.d("debugMapAfterFetch", "$currentLocation")
        }


        val playButton: FloatingActionButton = findViewById(R.id.fabPlayTrack)
        val stopButton: FloatingActionButton = findViewById(R.id.fabStopTrack)

        playButton.setOnClickListener {
            isRunning = true
        }
        stopButton.setOnClickListener {
            isRunning = false
        }



        val buttonWeight: Button = findViewById(R.id.buttonWeight)
        val buttonTrack: Button = findViewById(R.id.buttonTrack)
        val buttonChart: Button = findViewById(R.id.buttonChart)
        buttonTrack.setBackgroundColor(Color.parseColor("#33018786"))

        buttonWeight.setOnClickListener {
            startActivity(Intent(this, WeightActivity::class.java))
        }
        buttonTrack.setOnClickListener {
            startActivity(Intent(this, TrackActivity::class.java))
        }
        buttonChart.setOnClickListener {
            startActivity(Intent(this, ChartActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        mainHandler?.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler?.post(updateTextTask)
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode)
            return
        }

        val task = fusedLocationProviderClient!!.lastLocation
        task.addOnSuccessListener { location ->
            currentLocation = location
            val supportMapFragment =
                (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)
            supportMapFragment!!.getMapAsync(this@TrackActivity)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
        //Add marker
        val markerOptions = MarkerOptions().position(latLng).title("Votre position")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        googleMap.addMarker(markerOptions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        when (requestCode) {
            this.permissionCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}