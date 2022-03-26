package com.example.textmesh.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textmesh.R
import com.example.textmesh.adapters.ProductListAdapter
import com.example.textmesh.adapters.UrunlerListAdapter
import com.example.textmesh.model.UrunlerItems
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_urunler_screen.*

class UrunlerScreen : AppCompatActivity(), ProductListAdapter.OnItemClickListener {
    private lateinit var urunlerListRecyclerView: RecyclerView
    private lateinit var urunlerListAdapter: UrunlerListAdapter
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urunler_screen)

        urunlerListRecyclerView = findViewById(R.id.urunlerRecyler)
        urunlerListRecyclerView.setHasFixedSize(true)
        urunlerListRecyclerView.layoutManager = LinearLayoutManager(this)
        mDataBase = FirebaseFirestore.getInstance()
        val query: Query = mDataBase.collection("urunler")
        var config = PagingConfig(pageSize = 10)
        val options =
            FirestorePagingOptions.Builder<UrunlerItems>()
                .setLifecycleOwner(this)
                .setQuery(query, config, UrunlerItems::class.java).build()
        urunlerListAdapter = UrunlerListAdapter(options, this)
        urunlerListRecyclerView.adapter = urunlerListAdapter

        urunler_add.setOnClickListener {
            val intent = Intent(this, UrunlerAdd::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClicked(itemId: String) {
        showUrunlerDetailPage(itemId)
    }

    override fun onBackPressed() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    fun showUrunlerDetailPage(itemId: String) {
        val urunlerDetail = UrunlerDetail()
        val intent = Intent(this, UrunlerDetail::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("itemId", itemId)
        urunlerDetail.intent = intent
        startActivity(intent)
    }
}
