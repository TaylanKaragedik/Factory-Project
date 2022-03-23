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
import kotlinx.android.synthetic.main.activity_finis.*

class FinisActivity : AppCompatActivity(), ProductListAdapter.OnItemClickListener {

    private lateinit var finisListRecycler: RecyclerView
    private lateinit var finisListAdapter: ProductListAdapter
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finis)
        finisListRecycler = findViewById(R.id.finisRecyclerView)
        finisListRecycler.setHasFixedSize(true)
        finisListRecycler.layoutManager = LinearLayoutManager(this)
        mDataBase = FirebaseFirestore.getInstance()
        val query: Query = mDataBase.collection("Urunler")
        var config = PagingConfig(pageSize = 10)
        val options =
            FirestorePagingOptions.Builder<ProductItem>()
                .setLifecycleOwner(this)
                .setQuery(query, config, ProductItem::class.java).build()
        finisListAdapter = ProductListAdapter(options, this)
        finisListRecycler.adapter = finisListAdapter

        finis_add.setOnClickListener {
            val intent = Intent(this, FinisAddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClicked(itemId: String) {
        showProductDetailActivitcy(itemId)
    }

    fun showProductDetailActivitcy(itemId: String) {
        val finisDetailActivity = FinisDetailActivity()
        val intent = Intent(this, FinisDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("itemId", itemId)
        finisDetailActivity.intent = intent
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, UretimScreen::class.java)
        startActivity(intent)
    }
}