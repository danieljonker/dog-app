package nz.co.jonker.breedview.di

import nz.co.jonker.breedview.api.BreedViewApi
import nz.co.jonker.breedview.api.BreedViewKtorApi
import nz.co.jonker.breedview.data.BreedViewRepository
import nz.co.jonker.breedview.data.BreedViewRepositoryImpl
import nz.co.jonker.breedview.presentation.BreedViewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val breedViewModule = module {

    factory<BreedViewApi> { BreedViewKtorApi(ktorClient = get()) }
    factory<BreedViewRepository> {
        BreedViewRepositoryImpl(get())
    }

    viewModel { BreedViewViewModel(get(), get()) }
}
