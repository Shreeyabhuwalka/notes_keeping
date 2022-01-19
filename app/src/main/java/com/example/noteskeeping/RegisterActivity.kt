package com.example.noteskeeping

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    val ref = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.idEdtPassword)
        val registerBtn = findViewById<Button>(R.id.btnRegister)
        val name = findViewById<EditText>(R.id.idEdtName)
        val loginTV = findViewById<TextView>(R.id.loginText)

        loginTV.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            //            intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            finish()
        }

        registerBtn.setOnClickListener{
           var userEmail:String
           var userPassword:String
           var userName:String

           userEmail = email.text.toString()
           userPassword = password.text.toString()
           userName = name.text.toString()

            if(userName.isEmpty())
            {
                name.setError("Please enter your Name")
                name.requestFocus()

            } else if(userEmail.isEmpty()) {
                email.setError("Please enter your Email")
                email.requestFocus()

            }else if(userPassword.isEmpty())
            {
                password.setError("Please enter your Password")
                password.requestFocus()

            }else
            {
                ref.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
//                            Log.d(TAG, "createUserWithEmail:success")
//                            val user = ref.currentUser
//                            updateUI(user)
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
//                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
//                            updateUI(null)
                        }
                    }
            }

        }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = ref.currentUser
        if(currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}