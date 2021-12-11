package com.supinfo.supfitness.utilities

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.supinfo.supfitness.R
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

abstract class ActivityLocationProvider() : AppCompatActivity(), LocationListener,
    EasyPermissions.PermissionCallbacks {
    private lateinit var locationManager: LocationManager

    companion object {
        //Code used to identify the result of granting access to location
        const val REQUEST_LOCATION = 47456
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Activity Title
        val actionBar = supportActionBar
        actionBar!!.title = "R.string.actionBar_mainActivity"

    }


    private fun requestLocPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
            REQUEST_LOCATION,
        )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    protected fun registerListener() {
        requestPermissionLocation()
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_LOCATION)
    private fun requestPermissionLocation() {
        if (EasyPermissions.hasPermissions(this, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)) {
            // Already have permission, do the thing
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, this)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, this)
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.permission_location),
                REQUEST_LOCATION,
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, this)
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Log.d("PermissionDenied", "onPermissionsDenied: $requestCode :${perms.size}")
    }


    @SuppressLint("MissingPermission")
    override fun onProviderEnabled(provider: String) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, this)
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, this)
    }

    override fun onProviderDisabled(provider: String) {}

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}


}
