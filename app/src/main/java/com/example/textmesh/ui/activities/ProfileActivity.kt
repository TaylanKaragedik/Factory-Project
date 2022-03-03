package com.example.textmesh.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.textmesh.databinding.ActivityLoginBinding
import com.example.textmesh.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityProfileBinding

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
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonUretim.setOnClickListener{
            startActivity(Intent(this, RecyclerView::class.java))
        }

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, çıkış yap
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
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

}