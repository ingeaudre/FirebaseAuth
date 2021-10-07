package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener {
            if (binding.editEmail.text.toString().trim()
                    .isNotEmpty() || binding.editPassword.text.toString().trim().isNotEmpty()) {
                createUser(binding.editEmail.text.trim().toString(), binding.editPassword.text.trim().toString())

            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()

            }
        }

        binding.tvLogin.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java);
            startActivity(intent)
        }
    }

    fun createUser(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Log.e("Task Message", "Succesfull ...");

                    var intent = Intent(this, DashboardActivity::class.java);
                    startActivity(intent);

                } else {
                    Log.e("Task Message", "Failed ..."+task.exception);
                }

            }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser;

        if(user != null){

            var intent = Intent(this, DashboardActivity::class.java);
            startActivity(intent);
        }
    }

}

