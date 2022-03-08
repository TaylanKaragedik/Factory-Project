package com.example.textmesh.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.textmesh.R
import com.example.textmesh.model.ProductItem
import com.example.textmesh.ui.activities.DetailProductActivity
import com.google.firebase.firestore.FirebaseFirestore

class ProductListAdapter(private val productList: ArrayList<ProductItem>) :
    RecyclerView.Adapter<ProductListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val productListView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_page, parent, false)
        return ProductListViewHolder(productListView)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {

        when (holder) {
            is ProductListViewHolder -> {
                holder.bind(productList[position])
            }
        }
        holder.cardView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.cardView.context, DetailProductActivity::class.java)
            intent.putExtra("imageUrl", productList[position].imageUrl)
            intent.putExtra("modelKodu", productList[position].modelKodu)
            intent.putExtra("model", productList[position].model)
            intent.putExtra("model", productList[position].model)
            intent.putExtra("uretimNo", productList[position].uretimNo)
            intent.putExtra("renk", productList[position].renk)
            intent.putExtra("talimatAdeti", productList[position].talimatAdeti.toString())
            intent.putExtra("termin", productList[position].termin)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            holder.image.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {

        return productList.size
    }
}