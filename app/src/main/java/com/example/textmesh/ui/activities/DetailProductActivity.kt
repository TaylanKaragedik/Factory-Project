package com.example.textmesh.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.product_detial_page.*


class DetailProductActivity : AppCompatActivity() {

    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detial_page)
        val imageView: ImageView = findViewById(R.id.detailImage)
        val modelKodu: TextView = findViewById(R.id.detailModelKod)
        val model: TextView = findViewById(R.id.detailModel)
        val uretimNo: TextView = findViewById(R.id.detailUretimNo)
        val talimatAdeti: TextView = findViewById(R.id.detailAdet)
        val termin: TextView = findViewById(R.id.detailTermin)
        val renk: TextView = findViewById(R.id.detailRenk)

        model.text = intent.getStringExtra("model")
        modelKodu.text = intent.getStringExtra("modelKodu")
        uretimNo.text = intent.getStringExtra("uretimNo")
        renk.text = intent.getStringExtra("renk")
        talimatAdeti.text = intent.getStringExtra("talimatAdeti")

        sp_durumlar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mDataBase = FirebaseFirestore.getInstance()
                val text = parent!!.getItemAtPosition(position).toString()
                mDataBase.collection("Urunler").document(intent.getStringExtra("id").toString())
                    .update("sonDurum", text)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)


        Glide.with(baseContext)
            .applyDefaultRequestOptions(requestOptions)
            .load(intent.getStringExtra("imageUrl"))
            .fitCenter()
            .into(imageView)

    }


}