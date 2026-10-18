package com.example.mikhail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mikhail.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding // lateinit mangsudnya isi nanti. mengarah ke loginActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater) // inisialisasi untuk ngebinding ke loginactivity
        setContentView(binding.root) // ngeset view ke binding (baris 22)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val loginBTN : Button = findViewById(R.id.btnLogin)
//        val username : EditText = findViewById(R.id.edtUsername)
//        val password : EditText = findViewById(R.id.edtPassword)

        binding.btnLogin.setOnClickListener { // listener saat tombol diklik untuk btnLogin dengan binding
            val uname = binding.edtUsername.text.toString() // variabel untuk masang fungsi edtUsername dengan isi text dan jadikan isinya string
            val pass = binding.edtPassword.text.toString() // variabel untuk masang fungsi edtPassword dengan isi text dan jadikan isinya string

            val intent = Intent(this, MainActivity::class.java) // variabel untuk masang intent. this merujuk ke kepunyaan suatu class which is MainActivity
            intent.putExtra("username", uname) // username ini dianggap alamat untuk dirujuk dari MainActivity
            intent.putExtra("password", pass) // password ini dianggap alamat untuk dirujuk dari MainActivity
            startActivity(intent)

            Log.d("Output", "Username : $uname Password : $pass")
            Toast.makeText(this,"Username: $uname Password: $pass", Toast.LENGTH_LONG).show()
        }
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString()
            if (username.isNotEmpty() && username == password) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else
            {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
// Kode ini harus selalu dipanggil saat butuh akses "user_pref"
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
// Kondisi jika isLogin bernilai true
            val isLogin = sharedPref.getBoolean("isLogin", false)
            if (isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return
            }
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString()
            if (username.isNotEmpty() && username == password) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
        }
        }
    }
//            } else {
//                ... // AlertDialog “Silahkan coba lagi”
//            }
    }