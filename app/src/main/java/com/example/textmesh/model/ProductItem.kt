package com.example.textmesh.model

import java.util.*

data class ProductItem(
    var beden: List<Boolean>?=null,
    var model: String?=null,
    var modelKodu: String?=null,
    var renk: String?=null,
    var durumlar: List<String>?=null,
    var sonDurum: String?=null,
    var talimatAdeti: Int?=null,
    var uretimNo: String?=null,
    var imageUrl: String?=null,
    var termin: Date?=null,
)

