package nz.co.jonker.breedlist.di

import nz.co.jonker.breedlist.api.BreedsListApi
import nz.co.jonker.breedlist.api.BreedsListKtorApi
import nz.co.jonker.breedlist.data.BreedsListMapper
import nz.co.jonker.breedlist.data.BreedsListRepository
import nz.co.jonker.breedlist.data.BreedsListRepositoryImpl
import nz.co.jonker.breedlist.presentation.BreedListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val breedListModule = module {

    factory<BreedsListApi> { BreedsListKtorApi(ktorClient = get()) }
    factory<BreedsListRepository> {
        BreedsListRepositoryImpl(
            breedsListApi = get(),
            breedsListMapper = get()
        )
    }
    factory { BreedsListMapper() }

    viewModel {
        BreedListViewModel(get())
    }
}
