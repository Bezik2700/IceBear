package igor.second.spaceapp.appwindows.cardSearching.locationCard

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import igor.second.spaceapp.R

@Composable
fun OnSearchUser(){
    CircularProgressIndicator()
    Text(stringResource(R.string.location_update))
}