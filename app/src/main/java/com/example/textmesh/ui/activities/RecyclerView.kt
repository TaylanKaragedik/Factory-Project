package com.example.textmesh.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textmesh.R
import com.example.textmesh.adapters.ProductListAdapter
import com.example.textmesh.model.ProductItem
import com.google.firebase.firestore.*

class RecyclerView : AppCompatActivity() {

    private lateinit var productListRecycler: RecyclerView
    private lateinit var productList: ArrayList<ProductItem>
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        productListRecycler = findViewById(R.id.productRecyclerView)
        productListRecycler.layoutManager = LinearLayoutManager(this)
        productListRecycler.setHasFixedSize(true)

        productList = arrayListOf()
        EventChangeListener()
        productListAdapter = ProductListAdapter(productList)
        productListRecycler.adapter = productListAdapter
    }

    private fun EventChangeListener() {

        mDataBase = FirebaseFirestore.getInstance()
        mDataBase.collection("Urunler")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("FireStore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            productList.add(dc.document.toObject(ProductItem::class.java))
                        }
                    }

                    productListAdapter.notifyDataSetChanged()
                }

            })
    }

}