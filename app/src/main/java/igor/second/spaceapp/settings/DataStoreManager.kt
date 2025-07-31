package igor.second.spaceapp.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {

    suspend fun saveSettings(settingData: SettingData) {
        context.dataStore.edit { pref ->
            pref[intPreferencesKey("value1")] = settingData.value1
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingData(
            pref[intPreferencesKey("value1")] ?: 1
        )
    }
}

data class SettingData(
    val value1: Int
)