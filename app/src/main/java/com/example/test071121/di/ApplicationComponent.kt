package com.example.test071121.di

import com.example.test071121.ui.foto_list.FotoListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: FotoListViewModel)
}