package com.example.textmesh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.textmesh.R
import com.example.textmesh.model.ProductItem
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions

class ProductListAdapter(
    options: FirestorePagingOptions<ProductItem>,
    private var onItemClickListener: OnItemClickListener
) :
    FirestorePagingAdapter<ProductItem, ProductListViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val productListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_list_page, parent, false)
        return ProductListViewHolder(productListView)
    }

    override fun onBindViewHolder(
        holder: ProductListViewHolder,
        position: Int,
        productItem: ProductItem
    ) {
        when (holder) {
            is ProductListViewHolder -> {
                holder.bind(productItem)
            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(getItem(position)!!.id)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(itemId: String)
    }
}