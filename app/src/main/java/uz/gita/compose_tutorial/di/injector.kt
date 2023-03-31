package uz.gita.compose_tutorial.di


import android.system.Os.bind
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

import uz.gita.compose_tutorial.MainViewModel
import uz.gita.compose_tutorial.impl.MainViewModelImpl

val viewModelInject = module {
    viewModel {
        MainViewModelImpl()
    }
}