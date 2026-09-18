package uz.nargiz.foodrecipes.util

import com.google.gson.Gson
import uz.nargiz.foodrecipes.data.source.network.response.ErrorResponse

internal fun Gson.parseError(errorJson: String?): Exception {
    if (errorJson.isNullOrEmpty()) return Exception("Unknown error!")
    val error = fromJson(errorJson, ErrorResponse.Data::class.java)
    return Exception(error.detail[0].msg)
}