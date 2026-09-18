package uz.nargiz.foodrecipes.domain.model

import com.google.gson.annotations.SerializedName

data class RecipeData(
    val id: Int,
    val slug: String,
    val lang: String,
    val title: String,
    @SerializedName("primary_category")
    val primaryCategory: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("has_video")
    val hasVideo: Boolean
)
