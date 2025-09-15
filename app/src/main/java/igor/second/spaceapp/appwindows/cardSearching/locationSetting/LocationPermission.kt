package igor.second.spaceapp.appwindows.cardSearching.locationSetting

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LocationPermission(locationViewModel: LocationViewModel, context: Context){
    Text("Требуется разрешение на доступ к геолокации")
    Button(
        onClick = {
            locationViewModel.openAppSettings(context = context)
        }
    ) {
        Text("Запросить разрешение")
    }
}