package uz.nargiz.foodrecipes.domain.model

import com.google.gson.annotations.SerializedName

sealed interface RecipeDetail {
    data class Data(
        val id: Int,
        val slug: String,
        val lang: String,
        val title: String,
        @SerializedName("primary_category")
        val primaryCategory: String,
        val description: String,
        val ingredients: List<Ingredient>,
        val steps: List<Step>,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("video_url")
        val videoUrl: String,
        val author: String,
        @SerializedName("published_date")
        val publishedDate: String,
        val url: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("has_video")
        val hasVideo: Boolean
    )

    data class Ingredient(
        val type: String,
        val amount: String,
        val name: String
    )

    data class Step(
        @SerializedName("step_num")
        val stepNum: Int,
        @SerializedName("step_label")
        val stepLabel: String,
        val text: String,
        val images: List<String>

    )
}