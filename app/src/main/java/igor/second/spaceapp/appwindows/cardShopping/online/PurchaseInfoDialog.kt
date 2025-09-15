package igor.second.spaceapp.appwindows.cardShopping.online

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomInformationIcon(onClick: () -> Unit){
    IconButton(onClick = { onClick.invoke() }) {
        Icon(Icons.Rounded.Info, contentDescription = "info")
    }
}

@Composable
fun PurchaseInfoDialog(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    enabled: MutableState<Boolean>
){
    Dialog(onDismissRequest = { enabled.value = false }) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                Text(text = stringResource(text))
                TextButton(onClick = { enabled.value = false }) {
                    Text("Yes")
                }
            }
        }
    }
}