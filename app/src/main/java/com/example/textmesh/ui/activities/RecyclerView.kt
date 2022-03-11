package com.example.textmesh.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textmesh.R
import com.example.textmesh.adapters.ProductListAdapter
import com.example.textmesh.model.ProductItem
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RecyclerView : AppCompatActivity(), ProductListAdapter.OnItemClickListener {

    private lateinit var productListRecycler: RecyclerView
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        productListRecycler = findViewById(R.id.productRecyclerView)
        productListRecycler.setHasFixedSize(true)
        productListRecycler.layoutManager = LinearLayoutManager(this)
        mDataBase = FirebaseFirestore.getInstance()
        val query: Query = mDataBase.collection("Urunler")
        var config = PagingConfig(pageSize = 10)
        val options =
            FirestorePagingOptions.Builder<ProductItem>()
                .setLifecycleOwner(this)
                .setQuery(query, config, ProductItem::class.java).build()
        productListAdapter = ProductListAdapter(options, this)
        productListRecycler.adapter = productListAdapter

    }

    override fun onItemClicked(itemId: String) {
        showProductDetailActivitcy(itemId)
    }

    fun showProductDetailActivitcy(itemId: String) {
        val productDetailActivitcy = DetailProductActivity()
        val intent = Intent(this, DetailProductActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("itemId", itemId)
        productDetailActivitcy.intent = intent
        startActivity(intent)
    }
}