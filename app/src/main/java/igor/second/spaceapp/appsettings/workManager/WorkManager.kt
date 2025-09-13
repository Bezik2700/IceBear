package igor.second.spaceapp.appsettings.workManager

import android.content.Context
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
            val oneHourInMillis = 60 * 60 * 1000L

            if (currentTime - lastUpdate >= oneHourInMillis) {
                dataStoreManager.incrementCounter()
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        fun createWorkRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequestBuilder<AppWorkManager>(1, TimeUnit.HOURS)
                .setInitialDelay(1, TimeUnit.HOURS)
                .build()
        }
    }
}