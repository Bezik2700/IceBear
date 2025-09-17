package igor.second.spaceapp.appwindows.gameProcess.settings

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val tag = "ChatRepository"
    private val apiService = Retrofit.getService()

    fun addUserToRating(userName: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Добавляем пользователя $userName в рейтинг")

        val userRating = UserRating(name = userName)

        apiService.addUserToRating(userRating).enqueue(object : Callback<UserRating> {
            override fun onResponse(call: Call<UserRating>, response: Response<UserRating>) {
                if (response.isSuccessful) {
                    Log.d(tag, "Пользователь $userName успешно добавлен в рейтинг")
                    onSuccess()
                } else {
                    val errorMsg = "Ошибка добавления в рейтинг: ${response.code()}"
                    Log.e(tag, errorMsg)
                    onError(errorMsg)
                }
            }

            override fun onFailure(call: Call<UserRating>, t: Throwable) {
                val errorMsg = "Ошибка сети при добавлении в рейтинг: ${t.message}"
                Log.e(tag, errorMsg)
                onError(errorMsg)
            }
        })
    }

    fun loadUserNames(onSuccess: (List<String>) -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Загрузка имен пользователей...")

        apiService.getUserNames().enqueue(object : Callback<List<UserRating>> {
            override fun onResponse(call: Call<List<UserRating>>, response: Response<List<UserRating>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val names = users.map { it.name }
                    Log.d(tag, "Успешно! Загружено ${names.size} имен")
                    onSuccess(names)
                } else {
                    val errorMsg = "Ошибка: ${response.code()}"
                    Log.e(tag, errorMsg)
                    onError(errorMsg)
                }
            }

            override fun onFailure(call: Call<List<UserRating>>, t: Throwable) {
                val errorMsg = "Ошибка сети: ${t.message}"
                Log.e(tag, errorMsg)
                onError(errorMsg)
            }
        })
    }

    fun loadMessages(onSuccess: (List<Message>) -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Загружаем сообщения...")
        apiService.getMessages().enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful) {
                    val messages = response.body() ?: emptyList()
                    Log.d(tag, "Успешно! Загружено ${messages.size} сообщений")
                    onSuccess(messages)
                } else {
                    val errorMsg = "HTTP ошибка: ${response.code()}"
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
        Log.d(tag, "Отправляем сообщение: ${message.content}")
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

    fun deleteAllMessages(onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d(tag, "Deleting all messages from server via REST...")
        apiService.deleteAllMessages().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d(tag, "All messages deleted successfully via REST")
                    onSuccess()
                } else {
                    val errorMsg = "Ошибка отправки: ${response.code()}"
                    Log.w(tag, "Primary delete failed (${response.code()}) ...")
                    onError(errorMsg)
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.w(tag, "Primary delete network error ...")
            }
        })
    }
}