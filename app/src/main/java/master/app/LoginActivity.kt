package master.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import master.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // Handle login logic (validation, authentication, etc.)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.registerLink.setOnClickListener {
            // Open registration activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
