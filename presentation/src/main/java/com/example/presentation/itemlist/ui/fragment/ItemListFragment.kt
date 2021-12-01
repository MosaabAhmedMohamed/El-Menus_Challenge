package com.example.presentation.itemlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.presentation.R
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.base.ui.ext.gone
import com.example.presentation.base.ui.ext.visibility
import com.example.presentation.base.ui.ext.visible
import com.example.presentation.databinding.FragmentItemListBinding
import com.example.presentation.itemlist.ui.adapter.ItemsAdapter
import com.example.presentation.itemlist.viewmodel.ItemListViewModel
import com.example.presentation.tags.viewstate.ItemListViewState
import javax.inject.Inject

class ItemListFragment: BaseFragment() {

    private val args: ItemListFragmentArgs by navArgs()

    private lateinit var binding: FragmentItemListBinding

    private val itemListAdapter by lazy { ItemsAdapter{
        onProductItemClicked(it)
    }}


    @Inject
    lateinit var itemsViewModelFactory: ViewModelFactory<ItemListViewModel>
    private val itemsViewModel by lazy {
        ViewModelProvider(this, itemsViewModelFactory)
            .get(ItemListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun init() {
        initRefresh()
        observeViewState()
        initProductsRv()
        getProducts()
    }

    private fun initRefresh() {
        binding.refreshSrl.init {
            refreshProducts()
        }
    }

    private fun observeViewState() {
        itemsViewModel.itemListViewStateLD.observe(this, {
            handleViewState(it)
        })

    }

    private fun handleViewState(viewState: ItemListViewState) {
        when (viewState) {
            ItemListViewState.Loading -> loadingState()
            ItemListViewState.onEmptyState -> emptyState()
            is ItemListViewState.onError -> errorState(viewState.error)
            is ItemListViewState.onSuccess -> onProductsLoaded(viewState.result)
        }

    }

    private fun emptyState() {
        errorState()
        binding.errMessageRootView.btnRetry.gone()
        binding.errMessageRootView.messageTv.text = getString(R.string.empty_product)
    }

    private fun errorState(error: Throwable? = null) {
        binding.errMessageRootView.rootView.visible()
        showProductViews(false)
        binding.refreshSrl.stopRefresh()
        binding.progressRootView.rootView.gone()
    }

    private fun loadingState() {
        showProductViews(false)
        binding.progressRootView.rootView.visible()
        binding.errMessageRootView.rootView.gone()
    }

    private fun onProductsLoaded(result: List<ItemModel>) {
        showProductViews(true)
        binding.refreshSrl.stopRefresh()
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.gone()
        itemListAdapter.setItems(result.toMutableList())
    }

    private fun initProductsRv() {
        binding.listRv.layoutManager = LinearLayoutManager(requireContext())
        binding.listRv.adapter = itemListAdapter
    }

    private fun onProductItemClicked(item: ItemModel) {
       // requireActivity().navigateToProductDetails(item)
    }

    private fun getProducts() {
        itemListAdapter.clear()
        itemsViewModel.getItemList(args.tagName)
    }

    private fun refreshProducts() {
        itemListAdapter.clear()
        itemsViewModel.refreshItemList(args.tagName)
    }

    private fun showProductViews(show: Boolean) {
        binding.listRv.visibility(show)
    }

    override fun onViewClicked() {
        super.onViewClicked()
        binding.errMessageRootView.btnRetry.setOnClickListener {
            getProducts()
        }
    }

}