package com.example.minimoneybox.views.accounts


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.R
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.databinding.ItemAccountElementBinding
import com.example.minimoneybox.utils.OnItemClickListener
import com.example.minimoneybox.views.accounts.account_element.AccountElementViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class AccountsListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var accountsList: List<ProductResponse>
    private var onClickListener: OnItemClickListener
    private val clickSubject = PublishSubject.create<ProductResponse>()
    val clickEvent: Observable<ProductResponse> = clickSubject

    init {
        this.onClickListener = OnClickListener()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemAccountElementBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_account_element, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(accountsList[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return if(::accountsList.isInitialized) accountsList.size else 0
    }

    fun updateAccountsList(accountsList:List<ProductResponse>){
        this.accountsList = accountsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAccountElementBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = AccountElementViewModel()

        fun bind(productResponse: ProductResponse, onItemClickListener: OnItemClickListener){
            viewModel.bind(productResponse, onItemClickListener)
            binding.viewModel = viewModel
        }
    }

    inner class OnClickListener : OnItemClickListener {
        override fun onItemClick(productResponse: ProductResponse) {
            clickSubject.onNext(productResponse)
        }
    }
}