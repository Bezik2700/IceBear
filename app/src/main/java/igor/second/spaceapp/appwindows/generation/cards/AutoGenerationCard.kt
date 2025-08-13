package igor.second.spaceapp.appwindows.generation.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.settings.MainViewModel

@Composable
fun AutoGenerationCard(
    viewModel: MainViewModel = MainViewModel()
){
    val cardValue by viewModel.cardValue.collectAsState()

    Column(
        Modifier.size(160.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(modifier = Modifier){
            Card (modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                        .clickable(onClick = {
                            cardValue //random
                        })) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(160.dp),
                        progress = { cardValue / 100f }
                    )
                }
            }
            Text(
                text = "Hello",
                fontSize = 24.sp,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
    }
}