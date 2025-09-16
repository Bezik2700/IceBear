package igor.second.spaceapp.appwindows.gameProcess.settings

data class Message(
    val id: Int? = null,
    val content: String,
    val sender_name: String,
    val created_at: String? = null,
    val card_value: Int
)

data class UserRating(
    val id: Int? = null,
    val name: String,
    val created_at: String? = null
)