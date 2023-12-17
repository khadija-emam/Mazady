package com.example.mazady.ui.firstscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mazady.domain.model.Category
import com.example.mazady.domain.model.Children
import com.example.mazady.domain.model.Option
import com.example.mazady.domain.model.Properties
import com.example.mazady.domain.usecase.GetCategories
import com.example.mazady.domain.usecase.GetOptions
import com.example.mazady.domain.usecase.getProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val getCategories: GetCategories,
    private val getProperties: getProperties,
    val getOptions: GetOptions
) : ViewModel() {
     val categories = mutableListOf<Category>()
     val subCategories = mutableListOf<Children>()

    private val _categoryList = MutableStateFlow<List<String>>(emptyList())
    val categoryList: StateFlow<List<String>>
        get() = _categoryList

    private val _propertiesList = MutableStateFlow<List<Properties>>(emptyList())
    val propertiesList: StateFlow<List<Properties>>
        get() = _propertiesList

    private val _optionList = MutableStateFlow<List<Option>>(emptyList())
    val optionList: StateFlow<List<Option>>
        get() = _optionList

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean>
        get() = _loading
    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String>
        get() = _error

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _loading.emit(true)
            val result = getCategories.invoke()
            try {
                if (result != null) {
                    _loading.emit(false)
                    categories.addAll(result.categories)
                    _categoryList.emit(
                        result.categories.map {
                            it.name ?: ""
                        })
                }
            } catch (e: Exception) {
                _loading.emit(false)
                e.message?.let { _error.emit(it) }
            }


        }
    }

    fun getSubCategory(position: Int): List<String> {
        subCategories.addAll(categories[position].children?.toMutableList()?: emptyList())
        val subCategory = categories[position].children?.map { it.name ?: "" }?.toMutableList()
        subCategory?.apply { add(0,"other") }
        return subCategory?.toList() ?: emptyList()
    }
    fun getSubCategoryId(position: Int):Int{
        return if (position !=0) {
            subCategories[position].id
        } else
            0
    }
    fun getProperties(id: Int) {
        viewModelScope.launch {
            _loading.emit(true)
            val result = getProperties.invoke(id)
            try {
                Log.i("TAG", "getProperties: $result")
                if (result != null) {
                    _loading.emit(false)
                    _propertiesList.emit(result)
                }
            } catch (e: Exception) {
                _loading.emit(false)
                e.message?.let { _error.emit(it) }
            }


        }
    }

    fun getOptions(id: Int) {
        viewModelScope.launch {
            _loading.emit(true)
            val result = getOptions.invoke(id)
            try {
                if (result != null) {
                    _loading.emit(false)
                    _optionList.emit(result)
                }
            } catch (e: Exception) {
                _loading.emit(false)
                e.message?.let { _error.emit(it) }
            }


        }
    }

}