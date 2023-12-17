package com.example.mazady.data.remotedata


import com.example.mazady.domain.model.BaseResponse
import com.example.mazady.domain.model.CategoryResponse
import com.example.mazady.domain.model.Option
import com.example.mazady.domain.model.Properties
import com.example.mazady.libs.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET(EndPoints.GET_ALL_CATEGORIES)
    suspend fun getCategories(): Response<BaseResponse<CategoryResponse>>

    @GET(EndPoints.GET_SUB_CATEGORIES)
    suspend fun getProperties(
        @Query("cat") catId: Int
    ): Response<BaseResponse<List<Properties>>>

    @GET(EndPoints.GET_OPTIONS)
    suspend fun getOptions(
        @Path ("option")option:Int
    ):Response<BaseResponse<List<Option>>>
}