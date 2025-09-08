package igor.second.spaceapp.appwindows.gameProcess.settings

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val tag = "ChatRepository"
    private val apiService = Retrofit.getService()

    fun loadMessages(onSuccess: (List<Message>) -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "load messages...")

        apiService.getMessages().enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    val messages = response.body() ?: emptyList()
                    Log.d(tag, "Great! Download ${messages.size} messages")
                    onSuccess(messages)
                } else {
                    val errorMsg = "Error HTTP: ${response.code()}"
                    Log.e(tag, errorMsg)
                    onError(errorMsg)
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                val errorMsg = "Error network: ${t.message}"
                Log.e(tag, errorMsg)
                onError(errorMsg)
            }
        })
    }

    fun sendMessage(message: Message, onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Send messages: ${message.content}")

        apiService.sendMessage(message).enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                if (response.isSuccessful) {
                    Log.d(tag, "Message send great")
                    onSuccess()
                } else {
                    val errorMsg = "Error send: ${response.code()}"
                    Log.e(tag, errorMsg)
                    onError(errorMsg)
                }
            }

            override fun onFailure(call: Call<Message>, t: Throwable) {
                val errorMsg = "Error network on send: ${t.message}"
                Log.e(tag, errorMsg)
                onError(errorMsg)
            }
        })
    }

    fun deleteAllMessages(onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Deleting all messages from server via REST...")

        // Пробуем основной метод
        apiService.deleteAllMessages().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d(tag, "All messages deleted successfully via REST")
                    onSuccess()
                } else {
                    Log.w(tag, "Primary delete failed (${response.code()}) ...")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.w(tag, "Primary delete network error ...")
            }
        })
    }
}