package com.example.onlinestore.di

import android.app.Application
import androidx.room.Room
import com.example.data.api.ApiService
import com.example.data.local.AppDatabase
import com.example.data.local.dao.ProductDao
import com.example.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao
    }

    @Singleton
    @Provides
    fun provideHttpClient():HttpClient{
        return HttpClient(CIO){
            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level= LogLevel.ALL
            }
            install(DefaultRequest){
                url(Constants.BASE_URL)
            }
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: HttpClient) : ApiService {
        return ApiService(httpClient)
    }

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Default
}