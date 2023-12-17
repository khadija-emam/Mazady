package com.example.mazady.domain.repo

import com.example.mazady.domain.model.CategoryResponse
import com.example.mazady.domain.model.Option
import com.example.mazady.domain.model.Properties


interface Repository {
    suspend fun getCategories(): CategoryResponse?
    suspend fun getProperties(catId:Int):List<Properties>?
    suspend fun getOptions(option:Int):List<Option>?

}