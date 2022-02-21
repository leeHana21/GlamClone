package com.github.hanalee.glamclone.domain.retrofit.entity.response


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdditionalRecommendResponse(
    @SerializedName("data")
    val data: List<Data> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta()
) : Parcelable {
    @Keep
    @Parcelize
    data class Data(
        @SerializedName("age")
        val age: Int = 0,
        @SerializedName("company")
        val company: String = "",
        @SerializedName("distance")
        val distance: Int = 0,
        @SerializedName("height")
        val height: Int = 0,
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("introduction")
        val introduction: String? = null,
        @SerializedName("job")
        val job: String = "",
        @SerializedName("location")
        val location: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("pictures")
        val pictures: List<String> = listOf()
    ) : Parcelable

    @Keep
    @Parcelize
    data class Meta(
        @SerializedName("next")
        val next: Next = Next()
    ) : Parcelable {
        @Keep
        @Parcelize
        data class Next(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("method")
            val method: String = "",
            @SerializedName("url")
            val url: String = ""
        ) : Parcelable
    }
}