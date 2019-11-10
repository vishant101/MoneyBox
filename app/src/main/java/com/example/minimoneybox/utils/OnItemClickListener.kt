package com.example.minimoneybox.utils

import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse

interface OnItemClickListener {
    fun onItemClick(productResponse: ProductResponse)
}