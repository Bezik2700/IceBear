package igor.second.spaceapp.camera

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MainCamera(){
    var enabled by remember { mutableStateOf(false) }

    if (enabled){
        ARCompassApp()
    } else {
        Column {
            Button(onClick = {enabled = true}) { Text("Camera") }
        }
    }
}