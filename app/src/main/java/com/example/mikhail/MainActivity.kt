package com.example.mikhail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mikhail.databinding.ActivityLoginBinding
import com.example.mikhail.databinding.ActivityMainBinding
import com.example.mikhail.pertemuan5.LimaActivity
import com.example.mikhail.pertemuan6.EnamActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // lateinit mangsudnya isi nanti,
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater) // inisialisasi untuk binding ke mainActivity
        setContentView(binding.root) // ngeset view ke binding (baris 16)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val user = intent.getStringExtra("username") //fungsi intent mengambil string dari alamat username pada activity_main terus diisi dalam variabel
        val pass = intent.getStringExtra("password") //fungsi intent mengambil string dari alamat password pada activity_main terus diisi dalam variabel
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.txtUsername.text = user // cara pertama untuk ngambil value dari variabel user baris 24
        binding.txtPassword.setText(pass) // cara kedua untuk ngambil value dari variabel pass baris 25

        binding.btnSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Snigckers", // deskripsi dalam snackbar
                Snackbar.LENGTH_LONG)
                .setAction("I AM") { //tombol
                    val intent = Intent(this, MainActivity::class.java) // bawa balek ke MainActivity
                    startActivity(intent)
                    Toast.makeText(this, "Kembali ke Main", Toast.LENGTH_LONG).show()
                }
                .show()
        }
        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(this).setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.btnToLima.setOnClickListener {
            startActivity(Intent(this, LimaActivity::class.java))
        }
        binding.btnToEnam.setOnClickListener {
            startActivity(Intent(this, EnamActivity::class.java))
        }
//        binding.btnLogout.setOnClickListener {
//            AlertDialog.Builder(this)
//                .setTitle("Logout")
//                .setMessage("Apakah Anda yakin ingin keluar?")
//                .setPositiveButton("Ya") { dialog, _ ->
//                    dialog.dismiss()
//                    startActivity(Intent(this, loginActivity::class.java))
//                    finish()
//                }
//                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
//                .show()
//        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear() // untuk menghapus preferensi
                    editor.apply() // untuk mengset preferensi
                    dialog.dismiss()
                    startActivity(Intent(this, loginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}