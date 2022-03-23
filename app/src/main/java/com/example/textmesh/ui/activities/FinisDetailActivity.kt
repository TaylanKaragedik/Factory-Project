package com.example.textmesh.ui.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.model.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.text.SimpleDateFormat

class FinisDetailActivity : AppCompatActivity() {
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finis_detail)
        val itemId = intent.getStringExtra("itemId")
        val imageView: ImageView = findViewById(R.id.detailImage)
        val modelKodu: TextView = findViewById(R.id.detailModelKod)
        val model: TextView = findViewById(R.id.detailModel)
        val uretimNo: TextView = findViewById(R.id.detailUretimNo)
        val talimatAdeti: TextView = findViewById(R.id.detailAdet)
        val termin: TextView = findViewById(R.id.detailTermin)
        val renk: TextView = findViewById(R.id.detailRenk)
        val durum: TextView = findViewById(R.id.finis_durum)

        mDataBase = FirebaseFirestore.getInstance()
        val docRef = mDataBase.collection("Urunler").document(itemId!!)
        docRef.get().addOnSuccessListener {
            val product = it.toObject<ProductItem>()!!
            model.text = product.model
            modelKodu.text = product.modelKodu
            uretimNo.text = product.uretimNo
            talimatAdeti.text = product.talimatAdeti.toString()
            renk.text = product.renk
            termin.text = product.termin
            durum.setTypeface(null, Typeface.BOLD)
            durum.text = product.sonDurum

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)


            Glide.with(baseContext)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.imageUrl)
                .fitCenter()
                .into(imageView)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, FinisActivity::class.java)
        startActivity(intent)
    }
}