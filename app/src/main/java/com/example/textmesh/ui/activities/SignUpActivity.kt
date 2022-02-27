 package com.example.textmesh.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.textmesh.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

 class SignUpActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivitySignUpBinding

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
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Actionbar, geri butonu
        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Lütfen bekleyin")
        progressDialog.setMessage("Hesap Oluşturuluyor...")
        progressDialog.setCanceledOnTouchOutside(false)

        //firabase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //handle click, begin signup
        binding.signUpBtn.setOnClickListener {
            //validate
            validateData()
        }

    }

     private fun validateData() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //veri doğrulama

         if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
             //invalid email format
             binding.emailEt.error = "Geçersiz email formatı"

         }
         else if (TextUtils.isEmpty(password)){
             //password girilmediğinde
             binding.passwordEt.error = "Lütfen şifrenizi giriniz"

         }
         else if (password.length < 6){
             //if password length is less than 6
             binding.passwordEt.error = "Şire 6 haneden kısa olamaz"
         }
         else{
             //data is valid, go signup
             firebaseSignUp()
         }
     }

     private fun firebaseSignUp() {
         //show progress
         progressDialog.show()

        //hesap oluşturma
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //signup başarılı
                progressDialog.dismiss()
                //mevcut kullanıcı
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "$email ile hesap oluşturuldu", Toast.LENGTH_SHORT).show()

                //open profile
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()

            }
            .addOnFailureListener { e->
                //signup başarısız
                progressDialog.dismiss()
                Toast.makeText(this, "${e.message} nedeniyle oturum başarısız", Toast.LENGTH_SHORT).show()

            }
     }

     override fun onSupportNavigateUp(): Boolean {
         onBackPressed() //go back to previous activity, when back button of actionbar clicked
         return super.onSupportNavigateUp()
     }

}