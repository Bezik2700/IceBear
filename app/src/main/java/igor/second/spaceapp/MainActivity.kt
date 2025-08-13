package igor.second.spaceapp

import android.app.Activity
import android.content.Context
import android.content.Context.VIBRATOR_MANAGER_SERVICE
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import igor.second.spaceapp.settings.DataStoreManager
import igor.second.spaceapp.settings.NavigationActivity
import igor.second.spaceapp.ui.theme.SpaceAppTheme
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStoreManager = DataStoreManager(this)

        val timerValue = mutableStateOf("10")
        val timerRunning = mutableStateOf(false)

        setContent {
            SpaceAppTheme {

                // data values
                var userMoneyValue = remember { mutableDoubleStateOf(0.0) }
                var bronzeValue1 = remember { mutableIntStateOf(0) }
                var bronzeValue2 = remember { mutableIntStateOf(0) }
                var bronzeValue3 = remember { mutableIntStateOf(0) }
                var bronzeValue4 = remember { mutableIntStateOf(0) }
                var bronzeValue5 = remember { mutableIntStateOf(0) }
                var bronzeValue6 = remember { mutableIntStateOf(0) }
                var bronzeValue7 = remember { mutableIntStateOf(0) }
                var bronzeValue8 = remember { mutableIntStateOf(0) }
                var silverValue1 = remember { mutableIntStateOf(0) }
                var silverValue2 = remember { mutableIntStateOf(0) }
                var silverValue3 = remember { mutableIntStateOf(0) }
                var silverValue4 = remember { mutableIntStateOf(0) }
                var silverValue5 = remember { mutableIntStateOf(0) }
                var silverValue6 = remember { mutableIntStateOf(0) }
                var silverValue7 = remember { mutableIntStateOf(0) }
                var silverValue8 = remember { mutableIntStateOf(0) }
                var goldValue1 = remember { mutableIntStateOf(0) }
                var goldValue2 = remember { mutableIntStateOf(0) }
                var goldValue3 = remember { mutableIntStateOf(0) }
                var goldValue4 = remember { mutableIntStateOf(0) }
                var goldValue5 = remember { mutableIntStateOf(0) }
                var goldValue6 = remember { mutableIntStateOf(0) }
                var goldValue7 = remember { mutableIntStateOf(0) }
                var goldValue8 = remember { mutableIntStateOf(0) }
                var diamondValue1 = remember { mutableIntStateOf(0) }
                var diamondValue2 = remember { mutableIntStateOf(0) }
                var diamondValue3 = remember { mutableIntStateOf(0) }
                var diamondValue4 = remember { mutableIntStateOf(0) }
                var diamondValue5 = remember { mutableIntStateOf(0) }
                var diamondValue6 = remember { mutableIntStateOf(0) }
                var diamondValue7 = remember { mutableIntStateOf(0) }
                var diamondValue8 = remember { mutableIntStateOf(0) }
                var platinumValue1 = remember { mutableIntStateOf(0) }
                var platinumValue2 = remember { mutableIntStateOf(0) }
                var platinumValue3 = remember { mutableIntStateOf(0) }
                var platinumValue4 = remember { mutableIntStateOf(0) }
                var epicValue1 = remember { mutableIntStateOf(0) }
                var epicValue2 = remember { mutableIntStateOf(0) }
                var epicValue3 = remember { mutableIntStateOf(0) }
                var epicValue4 = remember { mutableIntStateOf(0) }

                LaunchedEffect(true) {
                    dataStoreManager.getSettings().collect { settings ->
                        userMoneyValue.doubleValue = settings.userMoneyValue
                        bronzeValue1.intValue = settings.bronzeValue1
                        bronzeValue2.intValue = settings.bronzeValue2
                        bronzeValue3.intValue = settings.bronzeValue3
                        bronzeValue4.intValue = settings.bronzeValue4
                        bronzeValue5.intValue = settings.bronzeValue5
                        bronzeValue6.intValue = settings.bronzeValue6
                        bronzeValue7.intValue = settings.bronzeValue7
                        bronzeValue8.intValue = settings.bronzeValue8
                        silverValue1.intValue = settings.silverValue1
                        silverValue2.intValue = settings.silverValue2
                        silverValue3.intValue = settings.silverValue3
                        silverValue4.intValue = settings.silverValue4
                        silverValue5.intValue = settings.silverValue5
                        silverValue6.intValue = settings.silverValue6
                        silverValue7.intValue = settings.silverValue7
                        silverValue8.intValue = settings.silverValue8
                        goldValue1.intValue = settings.goldValue1
                        goldValue2.intValue = settings.goldValue2
                        goldValue3.intValue = settings.goldValue3
                        goldValue4.intValue = settings.goldValue4
                        goldValue5.intValue = settings.goldValue5
                        goldValue6.intValue = settings.goldValue6
                        goldValue7.intValue = settings.goldValue7
                        goldValue8.intValue = settings.goldValue8
                        diamondValue1.intValue = settings.diamondValue1
                        diamondValue2.intValue = settings.diamondValue2
                        diamondValue3.intValue = settings.diamondValue3
                        diamondValue4.intValue = settings.diamondValue4
                        diamondValue5.intValue = settings.diamondValue5
                        diamondValue6.intValue = settings.diamondValue6
                        diamondValue7.intValue = settings.diamondValue7
                        diamondValue8.intValue = settings.diamondValue8
                        platinumValue1.intValue = settings.platinumValue1
                        platinumValue2.intValue = settings.platinumValue2
                        platinumValue3.intValue = settings.platinumValue3
                        platinumValue4.intValue = settings.platinumValue4
                        epicValue1.intValue = settings.epicValue1
                        epicValue2.intValue = settings.epicValue2
                        epicValue3.intValue = settings.epicValue3
                        epicValue4.intValue = settings.epicValue4
                    }
                }
                NavigationActivity(
                    dataStoreManager = dataStoreManager,
                    userMoneyValue = userMoneyValue,
                    bronzeValue1 = bronzeValue1,
                    bronzeValue2 = bronzeValue2,
                    bronzeValue3 = bronzeValue3,
                    bronzeValue4 = bronzeValue4,
                    bronzeValue5 = bronzeValue5,
                    bronzeValue6 = bronzeValue6,
                    bronzeValue7 = bronzeValue7,
                    bronzeValue8 = bronzeValue8,
                    silverValue1 = silverValue1,
                    silverValue2 = silverValue2,
                    silverValue3 = silverValue3,
                    silverValue4 = silverValue4,
                    silverValue5 = silverValue5,
                    silverValue6 = silverValue6,
                    silverValue7 = silverValue7,
                    silverValue8 = silverValue8,
                    goldValue1 = goldValue1,
                    goldValue2 = goldValue2,
                    goldValue3 = goldValue3,
                    goldValue4 = goldValue4,
                    goldValue5 = goldValue5,
                    goldValue6 = goldValue6,
                    goldValue7 = goldValue7,
                    goldValue8 = goldValue8,
                    diamondValue1 = diamondValue1,
                    diamondValue2 = diamondValue2,
                    diamondValue3 = diamondValue3,
                    diamondValue4 = diamondValue4,
                    diamondValue5 = diamondValue5,
                    diamondValue6 = diamondValue6,
                    diamondValue7 = diamondValue7,
                    diamondValue8 = diamondValue8,
                    platinumValue1 = platinumValue1,
                    platinumValue2 = platinumValue2,
                    platinumValue3 = platinumValue3,
                    platinumValue4 = platinumValue4,
                    epicValue1 = epicValue1,
                    epicValue2 = epicValue2,
                    epicValue3 = epicValue3,
                    epicValue4 = epicValue4,
                    navController = rememberNavController(),
                    timerValue = timerValue,
                    timerRunning = timerRunning
                )
            }
        }
    }
}

private fun vibrate(context: Context) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(VIBRATOR_SERVICE) as Vibrator
    }

    if (vibrator.hasVibrator()) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    1000,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(1000)
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

