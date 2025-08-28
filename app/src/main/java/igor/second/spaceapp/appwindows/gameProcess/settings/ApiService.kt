package igor.second.spaceapp.appwindows.gameProcess.settings

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("messages?select=*&order=created_at.asc")
    fun getMessages(): Call<List<Message>>

    @POST("messages")
    fun sendMessage(@Body message: Message): Call<Message>
}