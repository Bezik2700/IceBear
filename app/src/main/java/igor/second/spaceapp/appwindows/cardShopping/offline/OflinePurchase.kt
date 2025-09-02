package igor.second.spaceapp.appwindows.cardShopping.offline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OfflinePurchase(){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Text("purchase for game cash")
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            OfflinePurchaseCard(cardWidth = 0.3f)
            OfflinePurchaseCard(cardWidth = 0.5f)
            OfflinePurchaseCard(cardWidth = 1f)
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            OfflinePurchaseCard(cardWidth = 0.3f)
            OfflinePurchaseCard(cardWidth = 0.5f)
            OfflinePurchaseCard(cardWidth = 1f)
        }
    }
}

@Preview
@Composable
fun OfflinePurchasePreview(){
    OfflinePurchase()
}