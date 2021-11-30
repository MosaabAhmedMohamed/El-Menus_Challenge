package com.example.presentation.tags.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.base.ViewModelFactory
import com.example.presentation.base.ui.BaseFragment
import com.example.presentation.databinding.FragmentTagsBinding
import com.example.presentation.tags.ui.adapter.TagsAdapter
import com.example.presentation.tags.viewmodel.TagsViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TagsFragment : BaseFragment() {


    private lateinit var binding: FragmentTagsBinding

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
    }


    private fun initTagsRv() {
        val tagsAdapter = TagsAdapter()
        binding.productsListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.productsListRv.adapter = tagsAdapter

       tagsViewModel.getTags().subscribe {
           tagsAdapter.submitData(lifecycle, it)
        }.addTo(tagsViewModel.compositeDisposable)

    }

}