package com.sopt.now

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollwerService {
    @GET("/api/users")
    fun followerInfo(
       @Query ("page") page : Int
    ) : Call<ResponseFollowerDto>
}