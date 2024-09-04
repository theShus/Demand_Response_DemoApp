package master.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import master.app.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle registration logic here
        binding.registerButton.setOnClickListener {
            // For now, we'll simply go back to the LoginActivity after registration.
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
