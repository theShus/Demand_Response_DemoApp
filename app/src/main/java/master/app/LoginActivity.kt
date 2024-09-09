package master.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import master.app.databinding.ActivityLoginBinding
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UserResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val email: String,
    val password: String
)

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Call login API with email
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerLink.setOnClickListener {
            // Open registration activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        // Make an API call to check if the user exists
        val call = ApiClient.apiService.loginUser(email)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        // Check if the entered password matches the one in the database
                        if (user.data.password == password) {
                            // Password matches, login success
                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Password mismatch
                            Toast.makeText(this@LoginActivity, "Invalid password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed. Try again.", Toast.LENGTH_SHORT).show()
                    println("mali kurac")
                    println(response)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                println("veliki kurac")
                println(t)
            }
        })
    }
}
