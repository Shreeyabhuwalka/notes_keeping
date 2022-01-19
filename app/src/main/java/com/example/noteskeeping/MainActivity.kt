package com.example.noteskeeping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var mcreatenodefab:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle("All Notes")
        mcreatenodefab = findViewById(R.id.createnotefab)
        mcreatenodefab.setOnClickListener {
            val intent = Intent(this,CreateNote::class.java)
            startActivity(intent)
        }
    }
}