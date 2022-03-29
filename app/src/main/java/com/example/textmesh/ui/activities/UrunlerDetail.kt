package com.example.textmesh.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.model.UrunlerItems
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class UrunlerDetail : AppCompatActivity() {
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urunler_detail)
        val itemId = intent.getStringExtra("itemId")
        val imageView: ImageView = findViewById(R.id.urunlerDetailImage)
        val urunKodu: TextView = findViewById(R.id.urunlerKoduDetail)
        val urunAdi: TextView = findViewById(R.id.urunlerAdiDetail)
        val yas1: TextView = findViewById(R.id.detailYas1)
        val yas2: TextView = findViewById(R.id.detailYas2)
        val yas3: TextView = findViewById(R.id.detailYas3)
        val renk1: TextView = findViewById(R.id.detailRenk1)
        val renk2: TextView = findViewById(R.id.detailRenk2)
        val renk3: TextView = findViewById(R.id.detailRenk3)
        val renk4: TextView = findViewById(R.id.detailRenk4)
        mDataBase = FirebaseFirestore.getInstance()
        val docRef = mDataBase.collection("urunler").document(itemId!!)
        docRef.get().addOnSuccessListener {
            val product = it.toObject<UrunlerItems>()!!
            urunKodu.text = product.urunKodu
            urunAdi.text = product.urunAdi
            yas1.text = product.bedenler!![0]
            yas2.text = product.bedenler!![1]
            yas3.text = product.bedenler!![2]
            renk1.text = product.renkler!![0]
            renk2.text = product.renkler!![1]
            renk3.text = product.renkler!![2]
            renk4.text = product.renkler!![3]

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
}