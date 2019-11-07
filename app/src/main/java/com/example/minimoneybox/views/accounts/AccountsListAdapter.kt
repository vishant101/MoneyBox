package com.example.minimoneybox.views.accounts


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.R
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.databinding.ItemAccountElementBinding
import com.example.minimoneybox.views.accounts.accountelement.AccountElementViewModel

class AccountsListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var accountsList: List<ProductResponse>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemAccountElementBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_account_element, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemAccountElementBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = AccountElementViewModel()

        fun bind(productResponse: ProductResponse){
            viewModel.bind(productResponse)
            binding.viewModel = viewModel
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(accountsList[position])
    }

    override fun getItemCount(): Int {
        return if(::accountsList.isInitialized) accountsList.size else 0
    }

    fun updateAccountsList(accountsList:List<ProductResponse>){
        this.accountsList = accountsList
        notifyDataSetChanged()
    }
}