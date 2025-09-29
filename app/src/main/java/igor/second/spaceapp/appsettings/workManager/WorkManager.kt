package igor.second.spaceapp.appsettings.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import igor.second.spaceapp.appsettings.DataStoreManager
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

class AppWorkManager(
    context: Context,
    params: WorkerParameters
): CoroutineWorker(context, params) {

    private val dataStoreManager = DataStoreManager(context)

    override suspend fun doWork(): Result {
        return try {
            val lastUpdate = dataStoreManager.lastUpdate.first()
            val currentTime = System.currentTimeMillis()

            Log.d("AppWorkManager", "Last update: $lastUpdate, Current time: $currentTime")

            val oneHourInMillis = 24 * 60 * 60 * 1000L

            val hoursPassed = (currentTime - lastUpdate) / oneHourInMillis
            Log.d("AppWorkManager", "Hours passed: $hoursPassed")

            if (hoursPassed >= 1) {
                dataStoreManager.incrementCounter()
                dataStoreManager.updateLastUpdate(currentTime)
            }

            Result.success()
        } catch (e: Exception) {
            Log.e("AppWorkManager", "Error in doWork", e)
            Result.retry()
        }
    }

    companion object {
        fun createWorkRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequestBuilder<AppWorkManager>(
                15, TimeUnit.MINUTES
            )
                .setInitialDelay(1, TimeUnit.DAYS)
                .build()
        }
    }
}