package com.example.mazady.domain.usecase

import com.example.mazady.domain.repo.Repository
import javax.inject.Inject

class getProperties @Inject constructor(val repository: Repository) {
    suspend operator fun invoke(id:Int)=repository.getProperties(id)
}