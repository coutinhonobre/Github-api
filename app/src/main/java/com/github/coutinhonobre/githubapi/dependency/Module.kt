package com.github.coutinhonobre.githubapi.dependency

import com.github.coutinhonobre.githubapi.api.GeneralApi
import com.github.coutinhonobre.githubapi.presentation.RepositoryViewModel
import com.github.coutinhonobre.githubapi.repository.ItemRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        RepositoryViewModel(get())
    }
}

val repositoryModule = module {
    single {
        ItemRepository(get(), token)
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): GeneralApi {
        return retrofit.create(GeneralApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}

const val token = "token 22b6f6b19922b875b5973e0a955524ba05e825c0";