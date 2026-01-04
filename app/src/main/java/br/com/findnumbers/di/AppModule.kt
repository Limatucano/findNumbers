package br.com.findnumbers.di

import br.com.findnumbers.domain.usecase.NumberFinderUseCase
import br.com.findnumbers.ui.camera.CameraViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

internal object AppModule: KoinModule {
    override val module: Module = module {
        single { NumberFinderUseCase() }

        viewModelOf(::CameraViewModel)
    }
}