package igor.second.spaceapp.appsettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopRowNavigation(
    dialogShowValue: MutableState<Boolean>,
    userMoneyValue: MutableState<Int>,
    userName: MutableState<String>
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 8.dp, end = 8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            IconButton(onClick = { dialogShowValue.value = true }) {
                Icon(
                    Icons.Rounded.Person,
                    tint = Color(0xFF9678B6),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "user info"
                )
            }
            Text(
                userName.value,
                fontSize = 20.sp,
                color = Color(0xFF9678B6),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            ("$ ${userMoneyValue.value}"),
            color = Color(0xFF9678B6),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}