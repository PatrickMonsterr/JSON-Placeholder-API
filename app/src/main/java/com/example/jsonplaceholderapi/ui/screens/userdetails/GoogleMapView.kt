package com.example.jsonplaceholderapi.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun GoogleMapView(lat: Double, lng: Double, modifier: Modifier) {
    val mapView = rememberMapViewWithLifecycle()

    AndroidView({ mapView }, modifier = modifier) {
        it.getMapAsync { googleMap ->
            val location = LatLng(lat, lng)
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 2f))
            googleMap.addMarker(MarkerOptions().position(location).title("Lokalizacja użytkownika"))
        }
    }
}
