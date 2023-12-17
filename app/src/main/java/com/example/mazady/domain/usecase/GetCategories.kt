package com.example.mazady.domain.usecase

import com.example.mazady.domain.repo.Repository
import javax.inject.Inject

class GetCategories @Inject constructor(val repository: Repository){
    suspend operator fun invoke()= repository.getCategories()
}