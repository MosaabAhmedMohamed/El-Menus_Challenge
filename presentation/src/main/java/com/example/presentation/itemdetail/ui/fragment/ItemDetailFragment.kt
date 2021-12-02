package com.example.presentation.itemdetail.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.databinding.FragmentItemDetailBinding
import com.example.presentation.itemdetail.viewmodel.ItemDetailViewModel
import com.example.presentation.itemlist.ui.fragment.ItemListFragmentArgs
import com.example.presentation.itemlist.viewstate.ItemListViewState
import javax.inject.Inject

class ItemDetailFragment : BaseFragment() {

    private val args: ItemDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentItemDetailBinding


    @Inject
    lateinit var itemDetailViewModelFactory: ViewModelFactory<ItemDetailViewModel>
    private val itemDetailViewModel by lazy {
        ViewModelProvider(this, itemDetailViewModelFactory)
            .get(ItemDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          sharedElementEnterTransition = TransitionInflater.from(requireContext())
              .inflateTransition(android.R.transition.move)
          setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedElementEnterTransition = ChangeBounds().apply {
            duration = 500
        }
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun init() {
        binding.poster.transitionName = "${args.itemDetail.id}${binding.poster.transitionName}"
       // binding.descTv.text = args.itemDetail.description
        binding.collapsingToolbarLayout.title = args.itemDetail.name
        binding.descTv.text = args.itemDetail.description
        observeViewState()
        Glide.with(binding.root)
            .load(args.itemDetail.photoUrl)
            .into(binding.poster)
    }

    private fun observeViewState() {
        /* itemsViewModel.itemListViewStateLD.observe(this, {
             handleViewState(it)
         })*/
    }

    private fun handleViewState(viewState: ItemListViewState) {
        when (viewState) {
            ItemListViewState.Loading -> loadingState()
            ItemListViewState.onEmptyState -> {
            }
            is ItemListViewState.onError -> errorState(viewState.error)
            is ItemListViewState.onSuccess -> {
            }
        }
    }


    private fun errorState(error: Throwable? = null) {
        /*  binding.errMessageRootView.rootView.visible()
          showItemsViews(false)
          binding.refreshSrl.stopRefresh()
          binding.progressRootView.rootView.gone()*/
    }

    private fun loadingState() {
        /*  showItemsViews(false)
          binding.progressRootView.rootView.visible()
          binding.errMessageRootView.rootView.gone()*/
    }

    private fun onItemsLoaded(result: List<ItemModel>) {
        /*  showItemsViews(true)
          binding.refreshSrl.stopRefresh()
          binding.progressRootView.rootView.gone()
          binding.errMessageRootView.rootView.gone()*/
    }

    private fun onProductItemClicked(item: ItemModel) {
        // requireActivity().navigateToProductDetails(item)
    }

    private fun showItemsViews(show: Boolean) {
        //  binding.listRv.visibility(show)
    }

    override fun onViewClicked() {
        super.onViewClicked()
        /* binding.errMessageRootView.btnRetry.setOnClickListener {
             getItems()
         }*/
    }

}