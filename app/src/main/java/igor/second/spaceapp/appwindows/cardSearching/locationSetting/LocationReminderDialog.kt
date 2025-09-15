package igor.second.spaceapp.appwindows.cardSearching.locationSetting

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LocationReminderDialog(
    onDismiss: () -> Unit,
    onEnableLocation: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Геолокация отключена")
        },
        text = {
            Text("Для работы приложения необходимо включить геолокацию. Хотите включить её сейчас?")
        },
        confirmButton = {
            Button(
                onClick = onEnableLocation
            ) {
                Text("Включить")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Позже")
            }
        }
    )
}