package com.example.mazady.data.repository

import com.example.mazady.domain.repo.Repository
import com.example.mazady.data.remotedata.RemoteDataSource
import com.example.mazady.domain.model.CategoryResponse
import com.example.mazady.domain.model.Option
import com.example.mazady.domain.model.Properties
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    Repository {
    override suspend fun getCategories(): CategoryResponse? {
        return remoteDataSource.getCategories()
    }

    override suspend fun getProperties(catId: Int): List<Properties>? {
        return remoteDataSource.getProperties(catId)
    }

    override suspend fun getOptions(option: Int): List<Option>? {
        return remoteDataSource.getOptions(option)
    }

}