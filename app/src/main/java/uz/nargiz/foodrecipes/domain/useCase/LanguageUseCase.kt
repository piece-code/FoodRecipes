package uz.nargiz.foodrecipes.domain.useCase

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import javax.inject.Inject

sealed interface LanguageUseCase {
    class GetLanguageUseCase @Inject constructor() {
        operator fun invoke(): String =
            AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "uz"
    }

    class SetLanguageUseCase @Inject constructor() {
        operator fun invoke(code: String) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(code)
            )
        }
    }
}
