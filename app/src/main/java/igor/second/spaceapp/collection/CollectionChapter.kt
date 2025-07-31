package igor.second.spaceapp.collection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import igor.second.spaceapp.R

@Composable
fun CollectionChapter(manyCard: Int){

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                "card name",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally))
        }

        // status bronze
        if (manyCard == 0){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                CollectionSmallCard(
                    cardImage = R.drawable.ic_launcher_background,
                    cardName = R.string.app_name
                )
                CollectionSmallCard(
                    cardImage = R.drawable.ic_launcher_background,
                    cardName = R.string.app_name
                )
                CollectionSmallCard(
                    cardImage = R.drawable.ic_launcher_background,
                    cardName = R.string.app_name
                )
                CollectionSmallCard(
                    cardImage = R.drawable.ic_launcher_background,
                    cardName = R.string.app_name
                )
            }
        // status silver
        } else if (manyCard == 1) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        // status gold
        } else if (manyCard == 2) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        // status diamond
        } else if (manyCard == 3) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        // status platinum
        } else if (manyCard == 4) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        // status epic
        } else {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}