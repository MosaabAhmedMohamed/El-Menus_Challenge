package com.example.el_menus_challenge.presentation.ui.tagslist.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.el_menus_challenge.databinding.FragmentTagsBinding
import com.example.el_menus_challenge.presentation.ui.base.BaseFragment

class TagsFragment :BaseFragment() {


    private lateinit var binding: FragmentTagsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTagsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun init() {

        Log.d("testTAG", "init: ")
    }
}