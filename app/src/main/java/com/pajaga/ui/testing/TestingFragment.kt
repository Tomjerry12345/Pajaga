package com.pajaga.ui.testing

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.messaging.FirebaseMessaging
import com.pajaga.BuildConfig
import com.pajaga.R
import com.pajaga.service.firebase.FirebaseService
import com.pajaga.service.handphone.PlayerService
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant
import com.pajaga.utils.other.showLogAssert

const val TOPIC = "/topics/testing"

class TestingFragment : Fragment(R.layout.testing_fragment) {

    private val viewModel: TestingViewModel by viewModels {
        TestingViewModel.Factory()
    }

    private var locationPermissionGranted = false

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
//            Log.e("DEBUG", )
                showLogAssert("requestMultiplePermissions", "${it.key} = ${it.value}")
                locationPermissionGranted = it.value
            }
            getDeviceLocation()
        }

    // The entry point to the Places API.
    private lateinit var placesClient: PlacesClient

    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.setOnKeyListener(this)

        Places.initialize(requireContext(), BuildConfig.MAPS_API_KEY)
        placesClient = Places.createClient(requireActivity())
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
//            showToast(requireContext(), it)
            showLogAssert("token", it)
//            etToken.setText(it.token)
            getPermission()
            SavedData.setBoolean(Constant.ONACTIVED_KEY_VOLUME, true)
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        PlayerService.viewModel = viewModel
        PlayerService.viewLifecycleOwner = viewLifecycleOwner
    }

    private fun getPermission() {
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE
            )
        )

//        updateLocationUI()
    }

    // [START maps_current_place_get_device_location]
    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                showLogAssert("permission", "true")
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        val lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            showLogAssert("latitude", "${lastKnownLocation.latitude}")
                            showLogAssert("longitude", "${lastKnownLocation.longitude}")

                            SavedData.setFloat(Constant.KEY_LATITUDE, lastKnownLocation.latitude.toFloat())
                            SavedData.setFloat(Constant.KEY_LONGITUDE, lastKnownLocation.longitude.toFloat())
                        }
                    } else {
                        showLogAssert("test", "Current location is null. Using defaults.")
                        showLogAssert("maps error", "Exception: ${task.exception}")
//                        map?.moveCamera(
//                            CameraUpdateFactory
//                                .newLatLngZoom(defaultLocation, MapsFragment.DEFAULT_ZOOM.toFloat())
//                        )
//                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            } else {
                showLogAssert("locationPermissionGranted", "false")
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }
    // [END maps_current_place_get_device_location]

}