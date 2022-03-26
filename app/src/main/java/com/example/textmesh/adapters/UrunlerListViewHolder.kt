package com.example.textmesh.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.model.UrunlerItems

class UrunlerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.urunlerImg)
    val urunKodu: TextView = view.findViewById(R.id.urunlerKodu)
    val urunAdi: TextView = view.findViewById(R.id.urunlerAdi)
    val yas1: TextView = view.findViewById(R.id.urunler_yas1)
    val yas2: TextView = view.findViewById(R.id.urunler_yas2)
    val yas3: TextView = view.findViewById(R.id.urunler_yas3)
    val renk1: TextView = view.findViewById(R.id.urunler_renk1)
    val renk2: TextView = view.findViewById(R.id.urunler_renk2)
    val renk3: TextView = view.findViewById(R.id.urunler_renk3)
    val renk4: TextView = view.findViewById(R.id.urunler_renk4)

    fun bind(urunlerItems: UrunlerItems) {
        urunKodu.text = urunlerItems.urunKodu
        urunAdi.text = urunlerItems.urunAdi
        yas1.text = urunlerItems.bedenler!![0]
        yas2.text = urunlerItems.bedenler!![1]
        yas3.text = urunlerItems.bedenler!![2]
        renk1.text = urunlerItems.renkler!![0]
        renk2.text = urunlerItems.renkler!![1]
        renk3.text = urunlerItems.renkler!![2]
        renk4.text = urunlerItems.renkler!![3]

        val requestOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(urunlerItems.imageUrl)
            .into(image)
    }
}