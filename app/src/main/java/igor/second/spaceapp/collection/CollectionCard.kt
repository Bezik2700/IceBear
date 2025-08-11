package igor.second.spaceapp.collection

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.io.File
import java.io.FileOutputStream

@Composable
fun CollectionSmallCard(
    modifier: Modifier = Modifier,
    cardScore: Int,
    @DrawableRes cardImage: Int,
    @StringRes cardName: Int
){

    var enabled by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (enabled){
        Dialog(
            onDismissRequest = {enabled = !enabled}
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                Row {
                    Button(onClick = {enabled = !enabled}) {
                        Text("exit")
                    }
                    Button(
                        onClick = { captureScreenshot(context) }
                    ) { Text("screen") }
                }
                Card (
                    modifier = Modifier
                        .size(width = 280.dp, height = 560.dp)
                        .clickable(onClick = {})
                ) {
                    Image(
                        painterResource(cardImage),
                        contentDescription = "box card",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(stringResource(cardName))
            }
        }
    } else {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(cardScore.toString())
            Card (
                modifier = Modifier
                    .size(width = 64.dp, height = 128.dp)
                    .clickable(onClick = {enabled = !enabled})
            ) {
                Image(
                    painterResource(cardImage),
                    contentDescription = "box card",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(stringResource(cardName))
        }
    }
}

private fun captureScreenshot(context: Context) {
    val bitmap = getScreenBitmap(context) ?: return
    try {
        val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val file = File.createTempFile(
            "timer_screenshot_${System.currentTimeMillis()}",
            ".png", storageDir
        )
        FileOutputStream(file).use { fos ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
        }
        val uri = Uri.fromFile(file)
        context.sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                uri
            )
        )
        Toast.makeText(context, "Screenshot saved to Pictures!",
            Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Failed to save screenshot: ${e.message}",
            Toast.LENGTH_LONG).show()
    }
}

private fun getScreenBitmap(context: Context): Bitmap? {
    val rootView = (context as? Activity)?.window?.decorView?.rootView
    rootView?.isDrawingCacheEnabled = true
    rootView?.buildDrawingCache()
    val bitmap = rootView?.drawingCache?.let { Bitmap.createBitmap(it) }
    rootView?.isDrawingCacheEnabled = false
    return bitmap
}