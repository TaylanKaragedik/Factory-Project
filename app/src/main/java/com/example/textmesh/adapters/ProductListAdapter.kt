package com.example.textmesh.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.textmesh.R
import com.example.textmesh.model.ProductItem

class ProductListAdapter(private val productList: ArrayList<ProductItem>) :
    RecyclerView.Adapter<ProductListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val productListView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_page, parent, false)
        return ProductListViewHolder(productListView)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        //TextView
        val product: ProductItem = productList[position]
        holder.model.text = product.model
        holder.modelKodu.text = product.modelKodu
        holder.sonDurum.text = """Durum: ${product.sonDurum}"""
        holder.termin.text = "Termin: "
        holder.talimatAdeti.text = product.talimatAdeti.toString()
        holder.uretimNo.text = product.uretimNo
        holder.renk.text = product.renk

        //ImageView
        val requestOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
        Glide.with(holder.itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(product.imageUrl)
            .into(holder.image)

        //Size
        if (product.beden?.get(0) == true || product.beden?.get(1) == true || product.beden?.get(2) == true)
            holder.yas.visibility = View.VISIBLE
        if (product.beden?.get(0) == true)
            holder.yasBes.visibility = View.VISIBLE
        if (product.beden?.get(1) == true)
            holder.yasDokuz.visibility = View.VISIBLE
        if (product.beden?.get(2) == true)
            holder.yasOnUc.visibility = View.VISIBLE


    }

    override fun getItemCount(): Int {

        return productList.size
    }

}