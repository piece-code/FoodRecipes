package uz.nargiz.foodrecipes.domain.model

import com.google.gson.annotations.SerializedName

data class CategoryData(
    val key: String,
    @SerializedName("name_uz")
    val nameUz: String,
    @SerializedName("name_ru")
    val nameRu: String,
    val icon: String,
    val count: Int
)
