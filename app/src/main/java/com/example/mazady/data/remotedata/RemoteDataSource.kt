package com.example.mazady.data.remotedata

import com.example.mazady.domain.model.CategoryResponse
import com.example.mazady.domain.model.Option
import com.example.mazady.domain.model.Properties


interface RemoteDataSource {
suspend fun getCategories(): CategoryResponse?
suspend fun getProperties(catId:Int):List<Properties>?
suspend fun getOptions(option:Int):List<Option>?

}