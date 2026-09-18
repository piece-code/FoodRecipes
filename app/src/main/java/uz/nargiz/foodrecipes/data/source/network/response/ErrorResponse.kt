package uz.nargiz.foodrecipes.data.source.network.response

sealed interface ErrorResponse {
    data class Data(
        val detail: List<Detail>
    )

    data class Detail(
        val loc: List<String>,
        val msg: String,
        val type: String
    )
}
