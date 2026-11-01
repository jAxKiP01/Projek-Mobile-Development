package com.example.mikhail.pertemuan6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mikhail.R
import com.example.mikhail.databinding.ActivityEnamBinding

class EnamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEnamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        binding.btnUploadCover.setOnClickListener {
            Toast.makeText(this, "Upload Cover Diklik", Toast.LENGTH_SHORT).show()
        }

        binding.btnUploadSource.setOnClickListener {
            Toast.makeText(this, "Upload Berkas PDF Local", Toast.LENGTH_SHORT).show()
        }
    }
}