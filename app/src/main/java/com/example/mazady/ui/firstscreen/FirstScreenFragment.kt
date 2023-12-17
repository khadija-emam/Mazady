package com.example.mazady.ui.firstscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mazady.R
import com.example.mazady.databinding.FirstFragmentBinding
import com.example.mazady.domain.model.TableModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstScreenFragment : Fragment() {
    lateinit var binding: FirstFragmentBinding
    lateinit var adapter: PropertiesAdapter
    private val viewModel: FirstScreenViewModel by viewModels()
    val resultHashMap = HashMap<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentBinding.inflate(inflater, container, false)
        emitLoading()
        mainCategoriesHandling()
        subCategoryHandling()
        binding.submitButton.setOnClickListener {
          findNavController().navigate(FirstScreenFragmentDirections
              .actionFirstScreenFragmentToSecondScreenFragment(TableModel(resultHashMap)))
        }

        return binding.root
    }

    private fun subCategoryHandling() {
        binding.subCategoryDropdown.setOnItemClickListener { adapterView, view, i, l ->
            resultHashMap[resources.getString(R.string.sub_category)] =
                viewModel.subCategories.get(i).name?:""
            val propertiesId = viewModel.getSubCategoryId(i)
            if (propertiesId != 0) {
                viewModel.getProperties(propertiesId)
                handlePropertiesAdapter()
                binding.processTextInputLayout.visibility = View.GONE
            } else
                binding.processTextInputLayout.visibility = View.VISIBLE
        }
    }

    private fun handlePropertiesAdapter() {
        lifecycleScope.launch {
            adapter = PropertiesAdapter(DetailsClickListener {slug,name->
                resultHashMap[slug] = name
            })
            binding.propertiesRv.adapter = adapter
            viewModel.propertiesList.collect {
                adapter.submitList(it)
            }
        }
    }

    private fun mainCategoriesHandling() {
        lifecycleScope.launch {
            viewModel.categoryList.collect { list ->
                binding.mainDropdown.setAdapter(context?.let { createAdapter(it, list) })
            }
        }
        binding.mainDropdown.setOnItemClickListener { adapterView, view, i, l ->
            resultHashMap[resources.getString(R.string.main_category)] =
                viewModel.categories.get(i).name?:""
            binding.subCategoryDropdown.setAdapter(context?.let {
                createAdapter(
                    it,
                    viewModel.getSubCategory(i)
                )
            })

        }
    }

    private fun emitLoading() {
        lifecycleScope.launch {
            viewModel.loading.collect {
                if (it)
                    binding.progress.visibility = View.VISIBLE
                else
                    binding.progress.visibility = View.GONE
            }
        }
    }

    private fun createAdapter(context: Context, list: List<String>): ArrayAdapter<String> {
        return ArrayAdapter<String>(context, android.R.layout.select_dialog_item, list)
    }
}