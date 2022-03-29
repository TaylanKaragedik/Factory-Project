package com.example.textmesh.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.textmesh.R
import com.example.textmesh.model.UretimItems
import com.example.textmesh.model.UrunlerItems
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions

class UrunlerListAdapter(
    options: FirestorePagingOptions<UrunlerItems>,
    private var onItemClickListener: ProductListAdapter.OnItemClickListener
) :
    FirestorePagingAdapter<UrunlerItems, UrunlerListViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrunlerListViewHolder {
        val urunlerListView = LayoutInflater.from(parent.context)
            .inflate(R.layout.urunler_list, parent, false)
        return UrunlerListViewHolder(urunlerListView)
    }

    override fun onBindViewHolder(
        holder: UrunlerListViewHolder,
        position: Int,
        urunlerItems: UrunlerItems
    ) {
        when (holder) {
            is UrunlerListViewHolder -> {
                holder.bind(urunlerItems)
            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(getItem(position)!!.id)
        }
    }

}