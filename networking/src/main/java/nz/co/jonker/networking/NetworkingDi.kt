package nz.co.jonker.networking

import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkingModule = module {
    single<HttpClient> { ktorClient }
}
