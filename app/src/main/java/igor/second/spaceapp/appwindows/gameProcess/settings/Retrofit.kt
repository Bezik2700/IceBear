package igor.second.spaceapp.appwindows.gameProcess.settings

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    private const val BASE_URL = "https://avvwekmrqpzbeyezjxlw.supabase.co/rest/v1/"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImF2dndla21ycXB6YmV5ZXpqeGx3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTYxMjIyNzUsImV4cCI6MjA3MTY5ODI3NX0.BgV6EJ4eLuFeKRzUMkQbkbjuCx4JurSEMXR23e80sL8"

    private val apiService: ApiService by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
                .header("apikey", API_KEY)
                .header("Authorization", "Bearer $API_KEY")
                .header("Content-Type", "application/json")
                .header("Prefer", "return=representation")
                .build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    fun getService(): ApiService = apiService
}