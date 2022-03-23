package com.example.textmesh.ui.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.textmesh.R
import com.example.textmesh.databinding.ActivityProfileBinding
import com.example.textmesh.databinding.ActivityUretimScreenBinding
import com.google.firebase.auth.FirebaseAuth

class UretimScreen : AppCompatActivity() {
    private lateinit var binding: ActivityUretimScreenBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUretimScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonFason.setOnClickListener{
            startActivity(Intent(this, FasonActivity::class.java))
        }

        binding.imageButtonFinis.setOnClickListener {
            startActivity(Intent(this, FinisActivity::class.java))
        }

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

    }

    private fun checkUser() {
        //kullanıcının giriş yapıp yapmadığını kontrol et
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //null değilse giriş yapılmıştır. bilgileri alınır
            val email = firebaseUser.email

        }
        else{
            //null ise giriş yapılamadı, login activitye git
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}