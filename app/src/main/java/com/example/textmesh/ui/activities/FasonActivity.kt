package com.example.textmesh.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textmesh.R
import com.example.textmesh.adapters.ProductListAdapter
import com.example.textmesh.model.UretimItems
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_fason.*

class FasonActivity : AppCompatActivity(), ProductListAdapter.OnItemClickListener {
    private lateinit var fasonListRecycler: RecyclerView
    private lateinit var fasonListAdapter: ProductListAdapter
    private lateinit var mDataBase: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fason)
        fasonListRecycler = findViewById(R.id.fasonRecyclerView)
        fasonListRecycler.setHasFixedSize(true)
        fasonListRecycler.layoutManager = LinearLayoutManager(this)
        mDataBase = FirebaseFirestore.getInstance()
        val query: Query = mDataBase.collection("fasonUrunler")
        var config = PagingConfig(pageSize = 10)
        val options =
            FirestorePagingOptions.Builder<UretimItems>()
                .setLifecycleOwner(this)
                .setQuery(query, config, UretimItems::class.java).build()
        fasonListAdapter = ProductListAdapter(options, this)
        fasonListRecycler.adapter = fasonListAdapter

        fason_add.setOnClickListener {
            val intent = Intent(this, FasonAddActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onItemClicked(itemId: String) {
        showProductDetailActivitcy(itemId)
    }

    fun showProductDetailActivitcy(itemId: String) {
        val fasonDetailActivity = FasonDetailActivity()
        val intent = Intent(this, FasonDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("itemId", itemId)
        fasonDetailActivity.intent = intent
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, UretimScreen::class.java)
        startActivity(intent)
    }
}