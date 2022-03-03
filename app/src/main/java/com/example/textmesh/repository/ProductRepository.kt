package com.example.myapplication.repository

import com.example.myapplication.model.Adet
import com.example.myapplication.model.YasCesidi
import com.example.textmesh.model.AdetDetay
import com.example.textmesh.model.ProductItem

class ProductRepository {
    companion object {

        fun getProductList(): List<ProductItem> {
            val productList = mutableListOf<ProductItem>()
            productList.add(
                ProductItem(
                    1,
                    213135135,
                    "Çarşap",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    452121313,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = true, dokuzOnIki = true, onUcOnAltı = true)
                )
            )
            productList.add(
                ProductItem(
                    2,
                    513513135,
                    "Çarşap",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    545454,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = true, dokuzOnIki = false, onUcOnAltı = false)
                )
            )
            productList.add(
                ProductItem(
                    3,
                    46545,
                    "Çarşap",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    465454,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = false, dokuzOnIki = false, onUcOnAltı = true)
                )
            )
            productList.add(
                ProductItem(
                    4,
                    64648,
                    "Çarşap",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    78994,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = true, dokuzOnIki = true, onUcOnAltı = true)
                )
            )
            productList.add(
                ProductItem(
                    5,
                    213135135,
                    "Çarşap",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    452121313,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = false, dokuzOnIki = false, onUcOnAltı = false)
                )
            )
            productList.add(
                ProductItem(
                    6,
                    213135135,
                    "AglaBagır1",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    452121313,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = true, dokuzOnIki = false, onUcOnAltı = true)
                )
            )
            productList.add(
                ProductItem(
                    7,
                    213135135,
                    "Book",
                    Adet(3212, listOf(AdetDetay("kırmızı", 545465, 46546, 454, 54654))),
                    452121313,
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.lollypopvintage.co.uk%2Fvintageweddingdresses%2Fprod_6178607-Liana-luxury-red-off-the-shoulder-full-skirt-vintage-swing-dress.html&psig=AOvVaw2-NbFqjZpJxnlK1TWVpuue&ust=1645774896656000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCMitx7Prl_YCFQAAAAAdAAAAABAD",
                    YasCesidi(besSekiz = true, dokuzOnIki = false, onUcOnAltı = true)
                )
            )
            return productList
        }
    }
}