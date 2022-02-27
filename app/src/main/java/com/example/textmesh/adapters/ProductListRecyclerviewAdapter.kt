package com.example.textmesh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.databinding.UretimListPageBinding
import com.example.textmesh.model.ProductItem

class ProductListRecyclerviewAdapter(private var productList: List<ProductItem> = mutableListOf()) :
    RecyclerView.Adapter<ProductListRecyclerviewAdapter.ProductListViewHolder>() {

    inner class ProductListViewHolder(val binding: UretimListPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(productItem: ProductItem) {

            binding.adet.text = productItem.adet.toplamAdet.toString()
            binding.title.text = productItem.title
            binding.uretimNo.text = productItem.uretimNo.toString()
            binding.urunKodu.text = productItem.urunKodu.toString()

            if (productItem.yasCesidi.besSekiz || productItem.yasCesidi.dokuzOnIki || productItem.yasCesidi.onUcOnAltı)
                binding.yas.visibility = View.VISIBLE
            if (productItem.yasCesidi.besSekiz)
                binding.yasBes.visibility = View.VISIBLE
            if (productItem.yasCesidi.dokuzOnIki)
                binding.yasDokuz.visibility = View.VISIBLE
            if (productItem.yasCesidi.onUcOnAltı)
                binding.yasOnuc.visibility= View.VISIBLE
        }
    }

//    interface OnItemClickListener {
//        fun onItemClicked(id: Int)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            UretimListPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val productList = productList[position]
        holder.bindItem(productList)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}