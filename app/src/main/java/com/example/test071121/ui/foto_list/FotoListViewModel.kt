package com.example.test071121.ui.foto_list

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test071121.App
import com.example.test071121.domain.FotosUseCase
import com.example.test071121.ui.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FotoListViewModel: ViewModel() {

    @Inject
    lateinit var fotosUseCase: FotosUseCase
    init { App.instance?.applicationComponent?.inject(this) }

    private val fotoListLiveData = SingleLiveEvent<List<String>>()
    val fotoList: LiveData<List<String>> get() = fotoListLiveData

    @SuppressLint("CheckResult")
    fun getFotoList(){

        fotosUseCase.getUrlFotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fotoListLiveData.postValue(it)
            })
    }
}
