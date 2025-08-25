package igor.second.spaceapp.appwindows.connection

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

class ChatRepository {

    private val tag = "ChatRepository"
    private val apiService = RetrofitClient.getService()

    fun loadMessages(onSuccess: (List<Message>) -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Загрузка сообщений...")

        apiService.getMessages().enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    val messages = response.body() ?: emptyList()
                    Log.d(tag, "Успех! Загружено ${messages.size} сообщений")
                    onSuccess(messages)
                } else {
                    val errorMsg = "Ошибка HTTP: ${response.code()}"
                    Log.e(tag, errorMsg)
                    onError(errorMsg)
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                val errorMsg = "Ошибка сети: ${t.message}"
                Log.e(tag, errorMsg)
                onError(errorMsg)
            }
        })
    }

    fun sendMessage(message: Message, onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Отправка сообщения: ${message.content}")

        apiService.sendMessage(message).enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                if (response.isSuccessful) {
                    Log.d(tag, "Сообщение отправлено успешно")
                    onSuccess()
                } else {
                    val errorMsg = "Ошибка отправки: ${response.code()}"
                    Log.e(tag, errorMsg)
                    onError(errorMsg)
                }
            }

            override fun onFailure(call: Call<Message>, t: Throwable) {
                val errorMsg = "Ошибка сети при отправке: ${t.message}"
                Log.e(tag, errorMsg)
                onError(errorMsg)
            }
        })
    }
}

object RetrofitClient {

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

interface ApiService {
    @GET("messages?select=*&order=created_at.asc")
    fun getMessages(): Call<List<Message>>

    @POST("messages")
    fun sendMessage(@Body message: Message): Call<Message>
}

data class Message(
    val id: Int? = null,
    val content: String,
    val sender_name: String,
    val created_at: String? = null
)