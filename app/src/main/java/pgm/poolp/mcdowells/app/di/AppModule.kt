package pgm.poolp.mcdowells.app.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pgm.poolp.core.data.BigMickRepository
import pgm.poolp.core.usecase.GetBigMicksUseCase
import pgm.poolp.mcdowells.framework.data.BigMickDataSourceImpl
import pgm.poolp.mcdowells.framework.data.Env
import pgm.poolp.mcdowells.framework.paging.BigMickPagingSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideKtorHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = 15_000
                    socketTimeout = 15_000
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("http log: ", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Client-ID ${Env.API_KEY}")
            }
        }
    }

    @Provides
    @Singleton
    fun provideBigMickPagingSource() = BigMickPagingSource(provideGetBigMicksUseCase())

    @Provides
    @Singleton
    fun provideGetBigMicksUseCase() = GetBigMicksUseCase(provideBigMickRepository())

    @Provides
    @Singleton
    fun provideBigMickRepository() = BigMickRepository(provideBigMickDataSourceImpl())

    @Provides
    @Singleton
    fun provideBigMickDataSourceImpl() = BigMickDataSourceImpl(provideKtorHttpClient())
}