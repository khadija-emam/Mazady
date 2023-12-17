package com.example.mazady.domain.usecase

import com.example.mazady.domain.repo.Repository
import javax.inject.Inject

class GetOptions @Inject constructor(val repository: Repository) {
    suspend operator fun invoke(option:Int)=repository.getOptions(option)
}