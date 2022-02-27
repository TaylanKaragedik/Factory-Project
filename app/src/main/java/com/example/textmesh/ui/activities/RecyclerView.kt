package com.example.textmesh.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.repository.ProductRepository
import com.example.textmesh.adapters.ProductListRecyclerviewAdapter
import com.example.textmesh.databinding.ActivityRecyclerViewBinding

class RecyclerView : AppCompatActivity() {

    var binding: ActivityRecyclerViewBinding? = null
    private lateinit var productAdapter: ProductListRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        productAdapter = ProductListRecyclerviewAdapter(ProductRepository.getProductList())
        binding?.recyclerView?.adapter = productAdapter
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}