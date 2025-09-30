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

            val tenMinutesInMillis = 60 * 60 * 1000L

            val intervalsPassed = (currentTime - lastUpdate) / tenMinutesInMillis
            Log.d("AppWorkManager", "Intervals passed: $intervalsPassed")

            if (intervalsPassed >= 1) {
                val totalToAdd = intervalsPassed * 100
                dataStoreManager.incrementCounter(totalToAdd.toInt())
                dataStoreManager.updateLastUpdate(currentTime)

                Log.d("AppWorkManager", "Added $totalToAdd for $intervalsPassed intervals")
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
                1, TimeUnit.HOURS
            ).build()
        }
    }
}