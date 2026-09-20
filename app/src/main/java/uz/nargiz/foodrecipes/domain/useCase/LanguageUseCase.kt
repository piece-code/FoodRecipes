package uz.nargiz.foodrecipes.domain.useCase

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

sealed interface LanguageUseCase {
    class GetLanguageUseCase {
        operator fun invoke(): String =
            AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "uz"
    }

    class SetLanguageUseCase {
        operator fun invoke(code: String) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(code)
            )
        }
    }
}
