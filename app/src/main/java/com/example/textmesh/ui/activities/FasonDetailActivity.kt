package com.example.textmesh.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.model.UretimItems
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_fason_detail.*

class FasonDetailActivity : AppCompatActivity() {
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fason_detail)
        val itemId = intent.getStringExtra("itemId")
        val imageView: ImageView = findViewById(R.id.fasonDetailImage)
        val modelKodu: TextView = findViewById(R.id.fasonDetailModelKod)
        val model: TextView = findViewById(R.id.fasonDetailModel)
        val uretimNo: TextView = findViewById(R.id.fasonDetailUretimNo)
        val talimatAdeti: TextView = findViewById(R.id.fasonDetailAdet)
        val termin: TextView = findViewById(R.id.fasonDetailTermin)
        val renk: TextView = findViewById(R.id.fasonDetailRenk)

        mDataBase = FirebaseFirestore.getInstance()
        val docRef = mDataBase.collection("fasonUrunler").document(itemId!!)
        docRef.get().addOnSuccessListener {
            val product = it.toObject<UretimItems>()!!
            model.text = product.model
            modelKodu.text = product.modelKodu
            uretimNo.text = product.uretimNo
            talimatAdeti.text = product.talimatAdeti.toString()
            renk.text = product.renk
            termin.text = product.termin

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)


            Glide.with(baseContext)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.imageUrl)
                .fitCenter()
                .into(imageView)

            sp_fason_durumlar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val text = parent!!.getItemAtPosition(position).toString()
                    docRef.update("sonDurum", text)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    docRef.update("sonDurum", product.sonDurum)
                }

            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, FasonActivity::class.java)
        startActivity(intent)
    }
}