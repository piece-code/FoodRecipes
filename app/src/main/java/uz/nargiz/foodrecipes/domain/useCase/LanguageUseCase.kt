package uz.nargiz.foodrecipes.domain.useCase

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import uz.nargiz.foodrecipes.data.source.local.AppSharedPref
import javax.inject.Inject

sealed interface LanguageUseCase {
    class ChangeLanguage @Inject constructor(
        private val pref: AppSharedPref
    ) {
        operator fun invoke(): String {
            val lang = if (pref.lang == "uz") "ru" else "uz"
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(lang)
            )
            pref.lang = lang
            Log.d("TTT", "lang: ${pref.lang}")
            return lang
        }

    }

    class GetLanguageUseCase @Inject constructor(
        private val pref: AppSharedPref
    ) {
        operator fun invoke(): String =
            AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "uz"
    }

    class SetLanguageUseCase @Inject constructor(
        private val pref: AppSharedPref
    ) {
        operator fun invoke(code: String) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(code)
            )
        }
    }
}
