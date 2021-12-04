package com.example.presentation.tags.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.base.ui.ext.gone
import com.example.presentation.base.ui.ext.visibility
import com.example.presentation.base.ui.ext.visible
import com.example.presentation.databinding.FragmentTagsBinding
import com.example.presentation.tags.model.TagUiModel
import com.example.presentation.tags.ui.adapter.LoadStateAdapter
import com.example.presentation.tags.ui.adapter.TagsAdapter
import com.example.presentation.tags.viewmodel.TagsViewModel
import com.example.presentation.tags.viewstate.TagsViewState
import javax.inject.Inject
import androidx.recyclerview.widget.LinearSnapHelper




class TagsFragment : BaseFragment() {

    private lateinit var binding: FragmentTagsBinding

    private val tagsAdapter by lazy { TagsAdapter { onTagItemClicked(it) } }

    @Inject
    lateinit var tagsViewModelFactory: ViewModelFactory<TagsViewModel>
    private val tagsViewModel by lazy {
        ViewModelProvider(requireActivity(), tagsViewModelFactory)
            .get(TagsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTagsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        initTagsRv()
        observeViewState()
        setRefreshListener()
    }

    private fun initTagsRv() {
        binding.productsListRv.setHasFixedSize(true)
        binding.productsListRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.productsListRv.adapter = tagsAdapter
        setLoadStateAdapter()
        tagsAdapter.addLoadStateListener {
            tagsViewModel.handleLoadState(it)
        }
        setRecyclerSnapHelper()
    }

    private fun setRecyclerSnapHelper() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.productsListRv)
        binding.productsListRv.onFlingListener = snapHelper
    }

    private fun setLoadStateAdapter() {
        binding.productsListRv.adapter = tagsAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { tagsAdapter.retry() },
            footer = LoadStateAdapter { tagsAdapter.retry() }
        )
    }

    private fun observeViewState() {
        tagsViewModel.tagsViewStateLD.observe(this, {
            handleViewState(it)
        })
    }

    private fun handleViewState(viewState: TagsViewState) {
        when (viewState) {
            TagsViewState.Loading -> loadingState()
            TagsViewState.onEmptyState -> emptyState()
            is TagsViewState.onError -> errorState()
            is TagsViewState.onSuccess -> onItemsLoaded(viewState.result)
        }
    }

    private fun emptyState() {
        if (tagsAdapter.itemCount < 1) {
            errorState()
            binding.errMessageRootView.btnRetry.gone()
            binding.errMessageRootView.messageTv.text = getString(R.string.empty_product)
        }
    }

    private fun errorState() {
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

    private fun onItemsLoaded(result: PagingData<TagUiModel>) {
        showItemsViews(true)
        binding.refreshSrl.stopRefresh()
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.gone()
        tagsAdapter.submitData(lifecycle, result)
    }

    private fun showItemsViews(show: Boolean) {
        binding.productsListRv.visibility(show)
    }


    private fun onTagItemClicked(it: TagUiModel) {
        tagsViewModel.navigateToSelectedTag(it.name, it.id)
    }

    override fun onViewClicked() {
        super.onViewClicked()
        binding.errMessageRootView.btnRetry.setOnClickListener {
            tagsAdapter.refresh()
        }
    }

    private fun setRefreshListener() {
        binding.refreshSrl.setOnRefreshListener { tagsAdapter.refresh() }
    }

}