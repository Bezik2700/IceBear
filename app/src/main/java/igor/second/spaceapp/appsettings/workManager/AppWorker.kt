package igor.second.spaceapp.appsettings.workManager

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import igor.second.spaceapp.appsettings.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppWorker : Application() {
    override fun onCreate() {
        super.onCreate()

        val workRequest = AppWorkManager.createWorkRequest()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "app_background_work",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )

        val dataStoreManager = DataStoreManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.initializeLastUpdate()
        }
    }
}