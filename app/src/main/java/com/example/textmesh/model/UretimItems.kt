package com.example.textmesh.model

import com.google.firebase.Timestamp

data class UretimItems(
    var beden: List<Boolean>? = null,
    var model: String? = null,
    var modelKodu: String? = null,
    var renk: String? = null,
    var sonDurum: String? = null,
    var talimatAdeti: Int? = null,
    var uretimNo: String? = null,
    var imageUrl: String? = null,
    var termin: String? = null,
    var atolye: String? = null
)

