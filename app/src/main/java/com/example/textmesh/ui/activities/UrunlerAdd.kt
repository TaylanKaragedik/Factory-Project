package com.example.textmesh.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.textmesh.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_urunler_add.*
import java.util.*
import kotlin.collections.ArrayList

class UrunlerAdd : AppCompatActivity() {
    private lateinit var urunKodu: EditText
    private lateinit var urunAdi: EditText
    private lateinit var addBtn: Button
    private lateinit var bedenTxt: TextView
    private lateinit var bedenEditTxt: EditText
    private lateinit var renkTxt: TextView
    private lateinit var renkEditText: EditText
    private lateinit var db: FirebaseFirestore
    lateinit var beden: ArrayList<String>
    lateinit var renkler: ArrayList<String>
    private lateinit var urunler: MutableMap<Any, Any>
    var selectedPhotoUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urunler_add)
        db = FirebaseFirestore.getInstance()
        urunKodu = findViewById(R.id.urunlerAddKodu)
        urunAdi = findViewById(R.id.urunlerAddAdı)
        addBtn = findViewById(R.id.urunOlustur)
        bedenTxt = findViewById(R.id.urunlerbedenTxt)
        bedenEditTxt = findViewById(R.id.urunlerBedenler)
        renkTxt = findViewById(R.id.urunlerRenkTxt)
        renkEditText = findViewById(R.id.urunlerRenkler)
        urunlerAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        urunlerBedenButton.setOnClickListener {
            bedenTxt.text = bedenEditTxt.text.toString() + " " + bedenTxt.text.toString()
            bedenEditTxt.text.clear()
            val string = bedenTxt.text.toString().split(" ")
            beden = string as ArrayList<String>
        }
        urunlerRenklerButton.setOnClickListener {
            renkTxt.text = renkEditText.text.toString() + " " + renkTxt.text.toString()
            renkEditText.text.clear()
            val string = renkTxt.text.toString().split(" ")
            renkler = string as ArrayList<String>
        }
        addBtn.setOnClickListener {
            uploadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            urunlerAddImage.setBackgroundDrawable(bitmapDrawable)
            urunlerAddImage.text = ""
        }
    }

    private fun uploadImage() {
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    addProduct(it.toString())
                }
            }
    }

    private fun addProduct(imageUrl: String) {
        val kod: String = urunKodu.text.toString()
        val ad: String = urunAdi.text.toString()
        urunler = HashMap()
        urunler["urunKodu"] = kod
        urunler["urunAdi"] = ad
        urunler["imageUrl"] = imageUrl
        urunler["bedenler"] = beden
        urunler["renkler"] = renkler

        db.collection("urunler")
            .add(urunler)
            .addOnSuccessListener {
                Toast.makeText(this, "Urun Emri Başarılı", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Urun Emri Başarısız", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onBackPressed() {
        val intent = Intent(this, UrunlerScreen::class.java)
        startActivity(intent)
    }
}
