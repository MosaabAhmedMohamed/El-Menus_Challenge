package com.example.presentation.itemdetail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.databinding.FragmentItemDetailBinding
import com.example.presentation.itemdetail.viewmodel.ItemDetailViewModel
import com.example.presentation.itemdetail.viewstate.ItemDetailViewState
import com.example.presentation.itemlist.model.ItemUiModel
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
           // duration = 500
        }
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun init() {
        observeViewState()
        itemDetailViewModel.setItemDetailModel(args.itemDetail)
        binding.poster.transitionName = "${args.itemDetail.id}${binding.poster.transitionName}"
    }

    private fun observeViewState() {
         itemDetailViewModel.itemDetailViewStateLD.observe(this, {
             handleViewState(it)
         })
    }

    private fun handleViewState(viewState: ItemDetailViewState) {
        when (viewState) {
            is ItemDetailViewState.onSuccess -> onItemsLoaded(viewState.result)
        }
    }


    private fun onItemsLoaded(result: ItemUiModel) {
        binding.collapsingToolbarLayout.title = result.name
        binding.descTv.text = result.description
        Glide.with(binding.root)
            .load(result.photoUrl)
            .into(binding.poster)
    }


}