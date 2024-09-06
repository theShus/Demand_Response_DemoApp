package master.app.ui.home

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import master.app.R
import master.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up gradient for the main header text
        val mainHeaderText = root.findViewById<TextView>(R.id.mainHeaderText)
//        val textShader = LinearGradient(
//            0f, 0f, 0f, mainHeaderText.textSize,
//            intArrayOf(
//                Color.parseColor("#006400"),
//                Color.parseColor("#00CED1")
//            ), // Deep Green to Light Blue gradient
//            null, Shader.TileMode.CLAMP
//        )
//        mainHeaderText.paint.shader = textShader

        // Find the "Join Now" button and set the click listener
        val joinNowButton = root.findViewById<Button>(R.id.joinNowButton)
        joinNowButton.setOnClickListener {
            Toast.makeText(requireContext(), "Thank you for joining!", Toast.LENGTH_SHORT).show()

            // Open browser and go to google.com
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
//            startActivity(browserIntent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
