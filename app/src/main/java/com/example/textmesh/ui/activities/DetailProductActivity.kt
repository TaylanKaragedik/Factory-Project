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
import com.example.textmesh.model.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.product_detial_page.*
import java.text.SimpleDateFormat


class DetailProductActivity : AppCompatActivity() {

    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detial_page)
        val itemId = intent.getStringExtra("itemId")
        val imageView: ImageView = findViewById(R.id.detailImage)
        val modelKodu: TextView = findViewById(R.id.detailModelKod)
        val model: TextView = findViewById(R.id.detailModel)
        val uretimNo: TextView = findViewById(R.id.detailUretimNo)
        val talimatAdeti: TextView = findViewById(R.id.detailAdet)
        val termin: TextView = findViewById(R.id.detailTermin)
        val renk: TextView = findViewById(R.id.detailRenk)

        mDataBase = FirebaseFirestore.getInstance()
        val docRef = mDataBase.collection("Urunler").document(itemId!!)
        docRef.get().addOnSuccessListener {
            val product = it.toObject<ProductItem>()!!
            model.text = product.model
            modelKodu.text = product.modelKodu
            uretimNo.text = product.uretimNo
            talimatAdeti.text = product.talimatAdeti.toString()
            renk.text = product.renk
            val formatter = SimpleDateFormat("dd/MM/yy")
            val date = formatter.format(product.termin?.toDate())
            termin.text = "Termin: $date"

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)


            Glide.with(baseContext)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.imageUrl)
                .fitCenter()
                .into(imageView)

            sp_durumlar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        val intent = Intent(this, RecyclerView::class.java)
        startActivity(intent)
    }


}