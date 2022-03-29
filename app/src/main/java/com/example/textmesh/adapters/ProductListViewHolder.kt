package com.example.textmesh.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.model.UretimItems


class ProductListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cardView: CardView = view.findViewById(R.id.cardView)
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


    fun bind(uretimItems: UretimItems) {
        //TextView
        model.text = uretimItems.model
        modelKodu.text = uretimItems.modelKodu
        sonDurum.text = """Durum: ${uretimItems.sonDurum}"""
        termin.text = uretimItems.termin
        talimatAdeti.text = uretimItems.talimatAdeti.toString()
        uretimNo.text = "Üretim No: " + uretimItems.uretimNo
        renk.text = uretimItems.renk

        //ImageView
        val requestOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(uretimItems.imageUrl)
            .into(image)

        //Size
        if (uretimItems.beden?.get(0) == true || uretimItems.beden?.get(1) == true || uretimItems.beden?.get(
                2
            ) == true
        )
            yas.visibility = View.VISIBLE
        if (uretimItems.beden?.get(0) == true)
            yasBes.visibility = View.VISIBLE
        if (uretimItems.beden?.get(1) == true)
            yasDokuz.visibility = View.VISIBLE
        if (uretimItems.beden?.get(2) == true)
            yasOnUc.visibility = View.VISIBLE
    }

}