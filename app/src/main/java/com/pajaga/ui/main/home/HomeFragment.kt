package com.pajaga.ui.main.home

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
import com.pajaga.databinding.HomeFragmentBinding
import com.pajaga.model.Permission
import com.pajaga.service.firebase.FirebaseService
import com.pajaga.service.handphone.NotifService
import com.pajaga.ui.main.MainActivity
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant
import com.pajaga.utils.other.Constant.TOPIC
import com.pajaga.utils.other.showLogAssert
import com.pajaga.utils.system.moveIntentTo

class HomeFragment : Fragment(R.layout.home_fragment) {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }

    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory(binding.rvContact, binding.rvZone, binding.rvPermission)
    }

    private lateinit var binding: HomeFragmentBinding

    // The entry point to the Places API.
    private lateinit var placesClient: PlacesClient

    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var locationPermissionGranted = false

    private var listPermission: ArrayList<Permission> = ArrayList()

    val listValuePermission = listOf(
        Permission("1","ACCESS_FINE_LOCATION",false),
        Permission("2","ACCESS_WIFI_STATE",false),
        Permission("3","ACCESS_COARSE_LOCATION",false),
    )

    var i = 0

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
//            Log.e("DEBUG", )
                showLogAssert("requestMultiplePermissions", "${it.key} = ${it.value}")
                locationPermissionGranted = it.value
                listPermission.add(Permission(listValuePermission[i].id, listValuePermission[i].title, it.value))
                i++
            }
            getDeviceLocation()
            viewModel.setDataPermission(listPermission)
            i = 0
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HomeFragmentBinding.bind(view)
        viewModel.setData()
        viewModel.setDataZone()

        Places.initialize(requireContext(), BuildConfig.MAPS_API_KEY)
        placesClient = Places.createClient(requireActivity())
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
//            showToast(requireContext(), it)
//            showLogAssert("token", it)
//            etToken.setText(it.token)
            getPermission()
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        NotifService.viewModel = viewModel
        NotifService.viewLifecycleOwner = viewLifecycleOwner

        binding.switchNotif.isChecked = SavedData.getBoolean(Constant.ONACTIVED_KEY_VOLUME)

        binding.switchNotif.setOnCheckedChangeListener { compoundButton, checked ->
            SavedData.setBoolean(Constant.ONACTIVED_KEY_VOLUME, checked)
            moveIntentTo(requireActivity(), MainActivity())
        }

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

                            SavedData.setFloat(
                                Constant.KEY_LATITUDE,
                                lastKnownLocation.latitude.toFloat()
                            )
                            SavedData.setFloat(
                                Constant.KEY_LONGITUDE,
                                lastKnownLocation.longitude.toFloat()
                            )
                        }
                    } else {
                        showLogAssert("test", "Current location is null. Using defaults.")
                        showLogAssert("maps error", "Exception: ${task.exception}")
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