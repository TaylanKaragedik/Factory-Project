package com.example.textmesh.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.textmesh.R


class ProductListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.productImg)
    val modelKodu: TextView = view.findViewById(R.id.modelKodu)
    val model: TextView = view.findViewById(R.id.model)
    val uretimNo: TextView = view.findViewById(R.id.uretimNo)
    val talimatAdeti: TextView = view.findViewById(R.id.talimatAdeti)
    val termin: TextView = view.findViewById(R.id.termin)
    val sonDurum: TextView = view.findViewById(R.id.sonDurum)
    val renk: TextView = view.findViewById(R.id.renk)
    val yas: TextView = view.findViewById(R.id.yas)
    val yasBes: TextView = view.findViewById(R.id.yas_bes)
    val yasDokuz: TextView = view.findViewById(R.id.yas_dokuz)
    val yasOnUc: TextView = view.findViewById(R.id.yas_onuc)
}