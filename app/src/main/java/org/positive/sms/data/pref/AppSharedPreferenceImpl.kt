package org.positive.sms.data.pref

import android.content.Context
import org.positive.sms.BuildConfig
import javax.inject.Inject

class AppSharedPreferenceImpl @Inject constructor(context: Context) : AppSharedPreference {

    private val sharedPreferences = context.applicationContext.getSharedPreferences(
        BuildConfig.APPLICATION_ID,
        Context.MODE_PRIVATE
    )

    override var unixTime: Long
        get() = sharedPreferences.getLong(UNIX_TIME_KEY, 0L)
        set(value) {
            sharedPreferences.edit().putLong(UNIX_TIME_KEY, value).apply()
        }

    companion object {
        private const val UNIX_TIME_KEY = "UNIX_TIME_KEY"
    }
}