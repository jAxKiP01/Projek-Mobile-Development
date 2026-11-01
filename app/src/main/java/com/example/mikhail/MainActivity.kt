package com.example.mikhail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mikhail.databinding.ActivityMainBinding
import com.example.mikhail.pertemuan5.LimaActivity
import com.example.mikhail.pertemuan5.WebViewActivity
import com.example.mikhail.pertemuan6.EnamActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 1. Fitur Toggle Search Bar saat ikon Kaca Pembesar di header diklik
        binding.btnSearchHeader.setOnClickListener {
            if (binding.layoutSearchBar.visibility == View.GONE) {
                binding.layoutSearchBar.visibility = View.VISIBLE
            } else {
                binding.layoutSearchBar.visibility = View.GONE
            }
        }

        // 2. Fitur Tombol Garis Tiga (Menu / Dialog Logout)
        binding.btnMenuHeader.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Menu Utama")
                .setMessage("Pilih tindakan yang ingin dilakukan:")
                .setPositiveButton("Buka Fragment (Pertemuan 6)") { _, _ ->
                    startActivity(Intent(this, EnamActivity::class.java))
                }
                .setNeutralButton("Logout") { _, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    startActivity(Intent(this, loginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        // 3. OnClick pada ImageView Buku 1 (English IELTS) -> Memunculkan Snackbar & Opsi Buka Reader
        binding.imageView4.setOnClickListener {
            Snackbar.make(binding.root, "Membuka buku: English IELTS...", Snackbar.LENGTH_LONG)
                .setAction("BACA") {
                    val intent = Intent(this, WebViewActivity::class.java)
                    startActivity(intent)
                }
                .show()
        }

        // 4. OnClick pada ImageView Buku 2 (Fragrant Flower) -> Memunculkan Alert & Pergi ke EnamActivity (Fragment)
        binding.imageView6.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Fragrant Flower")
                .setMessage("Apakah kamu ingin membuka halaman Fragment koleksi?")
                .setPositiveButton("Buka Fragment") { dialog, _ ->
                    dialog.dismiss()
                    startActivity(Intent(this, EnamActivity::class.java))
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        // 5. OnClick pada ImageView Buku 3 (The Boxer) -> Memunculkan Toast & Navigasi ke LimaActivity
        binding.imageView5.setOnClickListener {
            Toast.makeText(this, "Membuka detail The Boxer", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LimaActivity::class.java))
        }

        // 6. OnClick pada FAB (+)
        binding.fabAddBook.setOnClickListener {
            Toast.makeText(this, "Tambah Buku Diklik", Toast.LENGTH_SHORT).show()
        }
    }
}