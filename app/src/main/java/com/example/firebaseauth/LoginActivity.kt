package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivityLoginBinding
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            if(binding.editTextEmail.text.trim().toString().isNotEmpty() || binding.editTextPassword.text.trim().toString().isNotEmpty()){
                signInUser(binding.editTextEmail.text.trim().toString(), binding.editTextPassword.text.trim().toString());
            }else{
                Toast.makeText(this, "Input required", Toast.LENGTH_SHORT).show();
            }
        }
    }
    fun signInUser(email:String, password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener (this) {task->
                if (task.isSuccessful){
                   val intent = Intent(this, DashboardActivity::class.java);
                   startActivity(intent)
                }else{
                    Toast.makeText(this, "Error !!" + task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

}