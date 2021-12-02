package com.example.presentation.itemlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.base.ui.ext.gone
import com.example.presentation.base.ui.ext.visibility
import com.example.presentation.base.ui.ext.visible
import com.example.presentation.databinding.FragmentItemListBinding
import com.example.presentation.itemlist.model.ItemUiModel
import com.example.presentation.itemlist.ui.adapter.ItemsAdapter
import com.example.presentation.itemlist.viewmodel.ItemListViewModel
import com.example.presentation.itemlist.viewstate.ItemListViewState
import javax.inject.Inject

class ItemListFragment : BaseFragment() {

    private val args: ItemListFragmentArgs by navArgs()

    private lateinit var binding: FragmentItemListBinding

    private val itemListAdapter by lazy {
        ItemsAdapter { item, img ->
            onProductItemClicked(item, img)
        }
    }


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
        initItemsRv()
        getItems()
    }

    private fun initRefresh() {
        binding.refreshSrl.init {
            refreshItems()
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
            is ItemListViewState.onSuccess -> onItemsLoaded(viewState.result)
        }

    }

    private fun emptyState() {
        errorState()
        binding.errMessageRootView.btnRetry.gone()
        binding.errMessageRootView.messageTv.text = getString(R.string.empty_product)
    }

    private fun errorState(error: Throwable? = null) {
        binding.errMessageRootView.rootView.visible()
        showItemsViews(false)
        binding.refreshSrl.stopRefresh()
        binding.progressRootView.rootView.gone()
    }

    private fun loadingState() {
        showItemsViews(false)
        binding.progressRootView.rootView.visible()
        binding.errMessageRootView.rootView.gone()
    }

    private fun onItemsLoaded(result: List<ItemUiModel>) {
        showItemsViews(true)
        binding.refreshSrl.stopRefresh()
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.gone()
        itemListAdapter.setItems(result.toMutableList())
    }

    private fun initItemsRv() {
        binding.listRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.listRv.adapter = itemListAdapter

        binding.listRv.apply {
            adapter = itemListAdapter
            postponeEnterTransition()
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }
    }

    private fun onProductItemClicked(item: ItemUiModel, img: ImageView) {
        img.transitionName = "${item.id}${img.transitionName}"
        itemsViewModel.navigateToItemDetail(item,img,requireView().findNavController())
    }

    private fun getItems() {
        itemListAdapter.clear()
        itemsViewModel.getItemList(args.tagName)
    }

    private fun refreshItems() {
        itemListAdapter.clear()
        itemsViewModel.refreshItemList(args.tagName)
    }

    private fun showItemsViews(show: Boolean) {
        binding.listRv.visibility(show)
    }

    override fun onViewClicked() {
        super.onViewClicked()
        binding.errMessageRootView.btnRetry.setOnClickListener {
            getItems()
        }
    }

}