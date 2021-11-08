package com.example.test071121.data.network

import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitService {

    @GET("task-m-001/list.php")
    fun getFotosList(): Observable<List<String>>
}