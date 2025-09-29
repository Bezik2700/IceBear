package igor.second.spaceapp.appwindows.gameProcess.settings

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("messages?select=*&order=created_at.asc")
    fun getMessages(): Call<List<Message>>

    @POST("messages")
    fun sendMessage(@Body message: Message): Call<Message>

    @DELETE("messages")
    fun deleteAllMessages(
        @Header("Prefer") prefer: String = "return=minimal",
        @Query("id") id: String = "gt.0"
    ): Call<Void>

    @GET("user_rating?select=name&order=created_at.asc")
    fun getUserNames(): Call<List<UserRating>>

    @POST("user_rating")
    fun addUserToRating(@Body userRating: UserRating): Call<UserRating>
}