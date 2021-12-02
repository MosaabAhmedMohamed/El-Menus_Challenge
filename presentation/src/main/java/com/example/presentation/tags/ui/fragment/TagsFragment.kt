package com.example.presentation.tags.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.tags.model.TagModel
import com.example.presentation.R
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.base.ui.ext.gone
import com.example.presentation.base.ui.ext.visibility
import com.example.presentation.base.ui.ext.visible
import com.example.presentation.databinding.FragmentTagsBinding
import com.example.presentation.tags.ui.adapter.TagsAdapter
import com.example.presentation.tags.viewmodel.TagsViewModel
import com.example.presentation.tags.viewstate.TagsViewState
import javax.inject.Inject

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
    }


    private fun initTagsRv() {
        binding.productsListRv.setHasFixedSize(true)
        binding.productsListRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.productsListRv.adapter = tagsAdapter


        /* binding.productsListRv.adapter = tagsAdapter.withLoadStateHeaderAndFooter(
             header = HeaderFooterAdapter(adapter),
             footer = HeaderFooterAdapter(adapter)
         )*/
        tagsAdapter.addLoadStateListener {
            /* showLoading(it.refresh is LoadState.Loading)
             showRetryDialog(it.refresh is LoadState.Error) {
                 tagsAdapter.retry()
             }*/
        }

        binding.refreshSrl.setOnRefreshListener { tagsAdapter.refresh() }

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
            is TagsViewState.onError -> errorState(viewState.error)
            is TagsViewState.onSuccess -> onItemsLoaded(viewState.result)
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

    private fun onItemsLoaded(result: PagingData<TagModel>) {
        showItemsViews(true)
        binding.refreshSrl.stopRefresh()
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.gone()
        tagsAdapter.submitData(lifecycle, result)
    }

    private fun showItemsViews(show: Boolean) {
        binding.productsListRv.visibility(show)
    }


    private fun onTagItemClicked(it: TagModel) {
        tagsViewModel.navigateToSelectedTag(it.name)
    }

}