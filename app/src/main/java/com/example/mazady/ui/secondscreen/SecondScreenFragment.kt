package com.example.mazady.ui.secondscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mazady.databinding.SecondFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreenFragment :Fragment(){
    val args: SecondScreenFragmentArgs by navArgs()
    lateinit var binding: SecondFragmentBinding
    lateinit var adapter: TableAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SecondFragmentBinding.inflate(inflater, container, false)
        adapter=TableAdapter()
        binding.tableRv.adapter=adapter
        adapter.submitList(args.model.hashMap.toList())
        return binding.root
    }
}