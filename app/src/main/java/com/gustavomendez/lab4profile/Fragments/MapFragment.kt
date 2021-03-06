package com.gustavomendez.lab4profile.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gustavomendez.lab4profile.R
import android.view.InflateException
import android.util.Log
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        private const val TAG = "MapFragment"
    }

    private lateinit var rootView: View
    private lateinit var mMap:GoogleMap
    private lateinit var mMapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.title = "Mapa"

        try {
            rootView = inflater.inflate(R.layout.fragment_map, container, false)

            //Initialize the map
            MapsInitializer.initialize(this.activity!!)

            //Binding Map fragment
            mMapView = rootView.findViewById(R.id.map) as MapView
            mMapView.onCreate(savedInstanceState)
            mMapView.getMapAsync(this)
        } catch (e: InflateException) {
            Log.e(TAG, "Inflate exception")
        }

        return rootView

    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap){
        //Setting the map instance
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val guatemala = LatLng(14.604713, -90.489377)
        mMap.addMarker(MarkerOptions().position(guatemala).title("Guatemala City"))

        //Update camera to new marker
        val cameraPosition = CameraPosition.Builder().target(guatemala).zoom(7.0f).build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap.moveCamera(cameraUpdate)


    }






}
