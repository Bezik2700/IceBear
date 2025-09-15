package igor.second.spaceapp.appwindows.cardStartScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import igor.second.spaceapp.R

@Composable
fun ExitDialogGame(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.exit)) },
        text = { Text(stringResource(R.string.exit_enabled)) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(R.string.no))
            }
        }
    )
}