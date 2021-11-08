package com.example.test071121.domain

import android.annotation.SuppressLint
import com.example.test071121.data.network.RetrofitService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FotosUseCase @Inject constructor(private val retrofitService: RetrofitService) {

    @SuppressLint("CheckResult")
    fun getUrlFotos(): Observable<List<String>>
    {
        return retrofitService.getFotosList()
            .subscribeOn(Schedulers.io())
    }
}