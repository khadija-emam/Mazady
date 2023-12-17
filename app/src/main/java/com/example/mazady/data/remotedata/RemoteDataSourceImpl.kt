package com.example.mazady.data.remotedata

import com.example.mazady.domain.model.CategoryResponse
import com.example.mazady.domain.model.Option
import com.example.mazady.domain.model.Properties
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val retrofitService: RetrofitService):
    RemoteDataSource {
    override suspend fun getCategories(): CategoryResponse? {
        val result=retrofitService.getCategories()
        if (result.isSuccessful){
            return result.body()?.data
        }else{
            throw Exception(result.message())
        }
    }

    override suspend fun getProperties(catId: Int): List<Properties>? {
        val result=retrofitService.getProperties(catId)
        if (result.isSuccessful){
            return result.body()?.data
        }else{
            throw Exception(result.message())
        }
    }

    override suspend fun getOptions(option: Int): List<Option>? {
        val result=retrofitService.getOptions(option)
        if (result.isSuccessful){
            return result.body()?.data
        }else{
            throw Exception(result.message())
        }
    }


}