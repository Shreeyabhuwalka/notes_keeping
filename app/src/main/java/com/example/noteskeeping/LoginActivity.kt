package com.example.noteskeeping

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth



class LoginActivity : AppCompatActivity() {

    val ref = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.idEdtPassword)
        val loginBtn = findViewById<Button>(R.id.idBtnLogin)

        val registerTV = findViewById<TextView>(R.id.registerText)

        registerTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            //            intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener{
            var userEmail:String
            var userPassword:String

            userEmail = email.text.toString()
            userPassword = password.text.toString()

            if(userEmail.isEmpty()) {
                email.setError("Please enter your email")
                email.requestFocus()

            }else if(userPassword.isEmpty())
            {
                password.setError("Please enter your password")
                password.requestFocus()

            }else
            {
                ref.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            val intent = Intent(this, MainActivity::class.java)
                            //            intent.putExtra("keyIdentifier", value)
                            startActivity(intent)
                            finish()
//                            val user = ref.currentUser
//                            updateUI(user)
                        } else {
//                            Log.w(TAG, "signInWithEmail:failure", task.exception)
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
            //            intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            finish()
        }
    }

}