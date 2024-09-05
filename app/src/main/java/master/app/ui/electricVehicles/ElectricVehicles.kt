package master.app.ui.electricVehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import master.app.R
import master.app.databinding.FragmentElectricVehiclesBinding

class ElectricVehiclesFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentElectricVehiclesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElectricVehiclesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize map
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Update battery status
        binding.batteryStatusValue.text = "70%" // You can update it dynamically

        return root
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Move camera to Belgrade
//        val belgrade = LatLng(44.7866, 20.4489)
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(belgrade, 12f))

        // Add electric charger locations (markers)
        addChargerLocations()
    }

    private fun addChargerLocations() {
        // Example locations for electric chargers in Belgrade
        val charger1 = LatLng(44.8125, 20.4612) // Random coordinates
        val charger2 = LatLng(44.7984, 20.4314)
        val charger3 = LatLng(44.7837, 20.4781)

//        googleMap.addMarker(MarkerOptions().position(charger1).title("Electric Charger 1"))
//        googleMap.addMarker(MarkerOptions().position(charger2).title("Electric Charger 2"))
//        googleMap.addMarker(MarkerOptions().position(charger3).title("Electric Charger 3"))
    }

    // MapView lifecycle methods
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        _binding = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
