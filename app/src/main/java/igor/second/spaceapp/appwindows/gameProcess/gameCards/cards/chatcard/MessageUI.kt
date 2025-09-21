package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.chatcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.appwindows.gameProcess.settings.Message

@Composable
fun MessageUI(message: Message, isMyMessage: Boolean) {

    val bubbleColor = if (isMyMessage) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val textColor = if (isMyMessage) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Row(
        horizontalArrangement = if (isMyMessage) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
    ) {
        Surface(
            color = bubbleColor,
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isMyMessage) 18.dp else 4.dp,
                bottomEnd = if (isMyMessage) 4.dp else 18.dp
            ),
            shadowElevation = 2.dp,
            modifier = Modifier.widthIn(max = 240.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (isMyMessage) Arrangement.End else Arrangement.Start,
                modifier = Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp)
            ) {
                Text(
                    text = "${message.sender_name}: ",
                    color = textColor.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )
                Text(
                    text = "${message.content} - ",
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Surface(
                    color = textColor.copy(alpha = 0.2f),
                    shape = CircleShape
                ) {
                    Text(
                        text = message.card_value.toString(),
                        color = textColor,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}