package igor.second.spaceapp.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlin.math.max

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {

    companion object {
        val USER_GENERATION_LEVEL = intPreferencesKey("userGenerationLevel")
        val USER_MONEY_VALUE = intPreferencesKey("userMoneyValue")
        val USER_PLATINUM_VALUE1 = intPreferencesKey("platinumValue1")
        val USER_PLATINUM_VALUE2 = intPreferencesKey("platinumValue2")
        val USER_EPIC_VALUE1 = intPreferencesKey("epicValue1")
        val USER_EPIC_VALUE2 = intPreferencesKey("epicValue2")
    }

    suspend fun plusCardValue(amount: Int){
        context.dataStore.edit { pref ->
            if (amount == 1){
                val current = pref[USER_PLATINUM_VALUE1] ?: 0
                pref[USER_PLATINUM_VALUE1] = current + 1
            } else if(amount == 2){
                val current = pref[USER_PLATINUM_VALUE2] ?: 0
                pref[USER_PLATINUM_VALUE2] = current + 1
            } else if(amount == 3){
                val current = pref[USER_EPIC_VALUE1] ?: 0
                pref[USER_EPIC_VALUE1] = current + 1
            } else {
                val current = pref[USER_EPIC_VALUE2] ?: 0
                pref[USER_EPIC_VALUE2] = current + 1
            }
        }
    }

    suspend fun removeCardValue(amount: Int){
        context.dataStore.edit { pref ->
            if (amount == 1){
                val current = pref[USER_PLATINUM_VALUE1] ?: 0
                pref[USER_PLATINUM_VALUE1] = max(0, current - 1)
            } else if(amount == 2){
                val current = pref[USER_PLATINUM_VALUE2] ?: 0
                pref[USER_PLATINUM_VALUE2] = max(0, current - 1)
            } else if(amount == 3){
                val current = pref[USER_EPIC_VALUE1] ?: 0
                pref[USER_EPIC_VALUE1] = max(0, current - 1)
            } else {
                val current = pref[USER_EPIC_VALUE2] ?: 0
                pref[USER_EPIC_VALUE2] = max(0, current - 1)
            }
        }
    }

    suspend fun upGenerationLevel(points: Int) {
        context.dataStore.edit { pref ->
            val current = pref[USER_GENERATION_LEVEL] ?: 0
            pref[USER_GENERATION_LEVEL] = current + points
        }
    }

    suspend fun removeGenerationLevel(points: Int) {
        context.dataStore.edit { pref ->
            val current = pref[USER_GENERATION_LEVEL] ?: 0
            pref[USER_GENERATION_LEVEL] = max(0, current - points)
        }
    }

    suspend fun upMoneyValue(points: Int) {
        context.dataStore.edit { preferences ->
            val current = preferences[USER_MONEY_VALUE] ?: 0
            preferences[USER_MONEY_VALUE] = current + points
        }
    }

    suspend fun removeMoneyValue(points: Int) {
        context.dataStore.edit { preferences ->
            val current = preferences[USER_MONEY_VALUE] ?: 0
            preferences[USER_MONEY_VALUE] = max(0, current - points)
        }
    }

    suspend fun saveSettings(settingData: SettingData) {
        context.dataStore.edit { pref ->

            pref[intPreferencesKey("userGenerationLevel")] = settingData.userGenerationLevel
            pref[intPreferencesKey("userMoneyValue")] = settingData.userMoneyValue

            pref[intPreferencesKey("bronzeValue1")] = settingData.bronzeValue1
            pref[intPreferencesKey("bronzeValue2")] = settingData.bronzeValue2
            pref[intPreferencesKey("bronzeValue3")] = settingData.bronzeValue3
            pref[intPreferencesKey("bronzeValue4")] = settingData.bronzeValue4
            pref[intPreferencesKey("bronzeValue5")] = settingData.bronzeValue5
            pref[intPreferencesKey("bronzeValue6")] = settingData.bronzeValue6
            pref[intPreferencesKey("bronzeValue7")] = settingData.bronzeValue7
            pref[intPreferencesKey("bronzeValue8")] = settingData.bronzeValue8

            pref[intPreferencesKey("silverValue1")] = settingData.silverValue1
            pref[intPreferencesKey("silverValue2")] = settingData.silverValue2
            pref[intPreferencesKey("silverValue3")] = settingData.silverValue3
            pref[intPreferencesKey("silverValue4")] = settingData.silverValue4
            pref[intPreferencesKey("silverValue5")] = settingData.silverValue5
            pref[intPreferencesKey("silverValue6")] = settingData.silverValue6
            pref[intPreferencesKey("silverValue7")] = settingData.silverValue7
            pref[intPreferencesKey("silverValue8")] = settingData.silverValue8

            pref[intPreferencesKey("goldValue1")] = settingData.goldValue1
            pref[intPreferencesKey("goldValue2")] = settingData.goldValue2
            pref[intPreferencesKey("goldValue3")] = settingData.goldValue3
            pref[intPreferencesKey("goldValue4")] = settingData.goldValue4
            pref[intPreferencesKey("goldValue5")] = settingData.goldValue5
            pref[intPreferencesKey("goldValue6")] = settingData.goldValue6
            pref[intPreferencesKey("goldValue7")] = settingData.goldValue7
            pref[intPreferencesKey("goldValue8")] = settingData.goldValue8

            pref[intPreferencesKey("diamondValue1")] = settingData.diamondValue1
            pref[intPreferencesKey("diamondValue2")] = settingData.diamondValue2
            pref[intPreferencesKey("diamondValue3")] = settingData.diamondValue3
            pref[intPreferencesKey("diamondValue4")] = settingData.diamondValue4
            pref[intPreferencesKey("diamondValue5")] = settingData.diamondValue5
            pref[intPreferencesKey("diamondValue6")] = settingData.diamondValue6
            pref[intPreferencesKey("diamondValue7")] = settingData.diamondValue7
            pref[intPreferencesKey("diamondValue8")] = settingData.diamondValue8

            pref[intPreferencesKey("platinumValue1")] = settingData.platinumValue1
            pref[intPreferencesKey("platinumValue2")] = settingData.platinumValue2
            pref[intPreferencesKey("platinumValue3")] = settingData.platinumValue3
            pref[intPreferencesKey("platinumValue4")] = settingData.platinumValue4

            pref[intPreferencesKey("epicValue1")] = settingData.epicValue1
            pref[intPreferencesKey("epicValue2")] = settingData.epicValue2
            pref[intPreferencesKey("epicValue3")] = settingData.epicValue3
            pref[intPreferencesKey("epicValue4")] = settingData.epicValue4
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingData(
            pref[intPreferencesKey("userGenerationLevel")] ?: 1,
            pref[intPreferencesKey("userMoneyValue")] ?: 50,

            pref[intPreferencesKey("bronzeValue1")] ?: 0,
            pref[intPreferencesKey("bronzeValue2")] ?: 0,
            pref[intPreferencesKey("bronzeValue3")] ?: 0,
            pref[intPreferencesKey("bronzeValue4")] ?: 0,
            pref[intPreferencesKey("bronzeValue5")] ?: 0,
            pref[intPreferencesKey("bronzeValue6")] ?: 0,
            pref[intPreferencesKey("bronzeValue7")] ?: 0,
            pref[intPreferencesKey("bronzeValue8")] ?: 0,

            pref[intPreferencesKey("silverValue1")] ?: 0,
            pref[intPreferencesKey("silverValue2")] ?: 0,
            pref[intPreferencesKey("silverValue3")] ?: 0,
            pref[intPreferencesKey("silverValue4")] ?: 0,
            pref[intPreferencesKey("silverValue5")] ?: 0,
            pref[intPreferencesKey("silverValue6")] ?: 0,
            pref[intPreferencesKey("silverValue7")] ?: 0,
            pref[intPreferencesKey("silverValue8")] ?: 0,

            pref[intPreferencesKey("goldValue1")] ?: 0,
            pref[intPreferencesKey("goldValue2")] ?: 0,
            pref[intPreferencesKey("goldValue3")] ?: 0,
            pref[intPreferencesKey("goldValue4")] ?: 0,
            pref[intPreferencesKey("goldValue5")] ?: 0,
            pref[intPreferencesKey("goldValue6")] ?: 0,
            pref[intPreferencesKey("goldValue7")] ?: 0,
            pref[intPreferencesKey("goldValue8")] ?: 0,

            pref[intPreferencesKey("diamondValue1")] ?: 0,
            pref[intPreferencesKey("diamondValue2")] ?: 0,
            pref[intPreferencesKey("diamondValue3")] ?: 0,
            pref[intPreferencesKey("diamondValue4")] ?: 0,
            pref[intPreferencesKey("diamondValue5")] ?: 0,
            pref[intPreferencesKey("diamondValue6")] ?: 0,
            pref[intPreferencesKey("diamondValue7")] ?: 0,
            pref[intPreferencesKey("diamondValue8")] ?: 0,

            pref[intPreferencesKey("platinumValue1")] ?: 0,
            pref[intPreferencesKey("platinumValue2")] ?: 0,
            pref[intPreferencesKey("platinumValue3")] ?: 0,
            pref[intPreferencesKey("platinumValue4")] ?: 0,

            pref[intPreferencesKey("epicValue1")] ?: 0,
            pref[intPreferencesKey("epicValue2")] ?: 0,
            pref[intPreferencesKey("epicValue3")] ?: 0,
            pref[intPreferencesKey("epicValue4")] ?: 0
        )
    }
}

data class SettingData(
    val userGenerationLevel: Int,
    val userMoneyValue: Int,

    val bronzeValue1: Int,
    val bronzeValue2: Int,
    val bronzeValue3: Int,
    val bronzeValue4: Int,
    val bronzeValue5: Int,
    val bronzeValue6: Int,
    val bronzeValue7: Int,
    val bronzeValue8: Int,
    val silverValue1: Int,
    val silverValue2: Int,
    val silverValue3: Int,
    val silverValue4: Int,
    val silverValue5: Int,
    val silverValue6: Int,
    val silverValue7: Int,
    val silverValue8: Int,
    val goldValue1: Int,
    val goldValue2: Int,
    val goldValue3: Int,
    val goldValue4: Int,
    val goldValue5: Int,
    val goldValue6: Int,
    val goldValue7: Int,
    val goldValue8: Int,
    val diamondValue1: Int,
    val diamondValue2: Int,
    val diamondValue3: Int,
    val diamondValue4: Int,
    val diamondValue5: Int,
    val diamondValue6: Int,
    val diamondValue7: Int,
    val diamondValue8: Int,
    val platinumValue1: Int,
    val platinumValue2: Int,
    val platinumValue3: Int,
    val platinumValue4: Int,
    val epicValue1: Int,
    val epicValue2: Int,
    val epicValue3: Int,
    val epicValue4: Int
)