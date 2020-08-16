package com.example.beautyscannerandroid.listener

import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.Product

interface OnUserProductClickListener {
    fun onUserProductClicked(product: MyProduct)
}
