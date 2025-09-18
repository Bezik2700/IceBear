package igor.second.spaceapp.appwindows.cardStartScreen.ratingSetting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.R

@Composable
fun UserRating(ratingViewModel: RatingViewModel = viewModel()){

    val userNames by ratingViewModel.userNames.collectAsState()
    val isLoading by ratingViewModel.isLoading.collectAsState()
    val error by ratingViewModel.error.collectAsState()

    val lastThreeNames = remember(userNames) {
        userNames.takeLast(3).reversed()
    }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
            .clip(CircleShape)
    ) {
        Box(modifier = Modifier){
            Image(
                painterResource(R.drawable.income_card_users),
                contentDescription = "rating fon",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (lastThreeNames.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                stringResource(R.string.top_users),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFFF58AB),
                                fontSize = 32.sp
                            )
                        }
                        items(lastThreeNames) { name ->
                            val displayIndex = lastThreeNames.indexOf(name) + 1
                            UserRatingItem(
                                name = name,
                                id = displayIndex
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Нет данных о пользователях")
                    }
                }
            }
            error?.let { errorMessage ->
                AlertDialog(
                    onDismissRequest = { ratingViewModel.clearError() },
                    title = { Text("Ошибка") },
                    text = { Text(errorMessage) },
                    confirmButton = {
                        Button(onClick = { ratingViewModel.clearError() }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}