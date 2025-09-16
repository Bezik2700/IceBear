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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import igor.second.spaceapp.R

@Composable
fun UserRating(ratingViewModel: RatingViewModel = viewModel()){

    val userNames by ratingViewModel.userNames.collectAsState()
    val isLoading by ratingViewModel.isLoading.collectAsState()
    val error by ratingViewModel.error.collectAsState()

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Box(modifier = Modifier){
            Image(
                painterResource(R.drawable.income_card_game),
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
                if (userNames.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                "TOP",
                                fontSize = 32.sp
                            )
                        }
                        items(userNames) { name ->
                            UserRatingItem(
                                name = name,
                                id = userNames.indexOf(name) + 1
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