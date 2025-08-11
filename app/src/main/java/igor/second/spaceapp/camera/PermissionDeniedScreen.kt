package igor.second.spaceapp.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PermissionRequestScreen(
    showRationale: Boolean,
    onRequestPermission: () -> Unit,
    onDenyPermission: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (showRationale) {
                Text(
                    text = "Для работы компаса нужен доступ к вашей геолокации",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Приложение использует GPS только для определения направления",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Button(
                onClick = onRequestPermission,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Разрешить доступ")
            }

            if (showRationale) {
                Button(
                    onClick = onDenyPermission,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Продолжить без доступа", color = Color.White)
                }
            }
        }
    }
}