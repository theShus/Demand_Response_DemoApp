package master.app.ui.electricVehicles

import ChargerLocation
import ElectricVehiclesViewModel
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import master.app.databinding.FragmentElectricVehiclesBinding

class ElectricVehicles : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentElectricVehiclesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    // ViewModel reference
    private val electricVehiclesViewModel: ElectricVehiclesViewModel by viewModels()

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

        // Observe charger locations LiveData from the ViewModel
        electricVehiclesViewModel.chargerLocations.observe(viewLifecycleOwner) { locations ->
            addChargerLocations(locations)
        }

        // Fetch charger locations from the API
        electricVehiclesViewModel.fetchChargerLocations()

        return root
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Move camera to Belgrade as a default location
        val belgrade = LatLng(44.7866, 20.4489) // Coordinates for Belgrade
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(belgrade, 12f)) // Set default zoom
    }

    private fun addChargerLocations(locations: List<ChargerLocation>) {
        // Clear any existing markers
        googleMap.clear()

        // Add new charger locations to the map
        locations.forEach { location ->
            val latLng = LatLng(location.latitude, location.longitude)
            googleMap.addMarker(MarkerOptions().position(latLng).title(location.title))
        }

        // Add vehicle location (this could be fetched dynamically as well)
        val vehicleLocation = LatLng(44.805, 20.450) // Replace with actual vehicle coordinates
        googleMap.addMarker(
            MarkerOptions()
                .position(vehicleLocation)
                .title("Your Vehicle")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)) // Blue marker
        )
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