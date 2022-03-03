package com.example.textmesh.model

import com.example.myapplication.model.Adet
import com.example.myapplication.model.YasCesidi

data class ProductItem(
    val id: Int,
    val uretimNo: Int?,
    val title: String?,
    val adet: Adet,
    val urunKodu: Int?,
    val imageUrl: String?,
    val yasCesidi: YasCesidi,
)
