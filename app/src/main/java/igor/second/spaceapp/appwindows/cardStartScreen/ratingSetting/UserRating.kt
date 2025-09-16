package igor.second.spaceapp.appwindows.cardStartScreen.ratingSetting

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
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
                    items(userNames) { name ->
                        UserRatingItem(name = name)
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
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