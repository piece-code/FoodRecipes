package uz.nargiz.foodrecipes.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class AppSharedPref @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val pref = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    var lang: String
        get() = pref.getString("lang", "uz") ?: "uz"
        set(value) = pref.edit { putString("lang", value) }

    var recommended: String
        get() = pref.getString("recommended", "") ?: ""
        set(value) = pref.edit { putString("recommended", value) }

    var saved: String
        get() = pref.getString("saved", "") ?: ""
        set(value) = pref.edit { putString("saved", value) }
}