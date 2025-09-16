package igor.second.spaceapp.appsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopRowNavigation(
    dialogShowValue: MutableState<Boolean>,
    userMoneyValue: MutableState<Int>,
    userName: MutableState<String>
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 8.dp, end = 8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(color = Color.Cyan)
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = { dialogShowValue.value = true }) {
                    Icon(
                        Icons.Rounded.Person,
                        contentDescription = "user info"
                    )
                }
                Text(
                    userName.value,
                    fontSize = 24.sp
                )
            }
            Text(
                ("$ ${userMoneyValue.value}"),
                fontSize = 24.sp
            )
        }
    }
}