package com.github.hanalee.glamclone.domain.retrofit.entity.response


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class ProfileResponse(
    @SerializedName("data")
    val data: Data = Data(),
    @SerializedName("meta")
    val meta: Meta = Meta()
) : Parcelable {
    @Keep
    @Parcelize
    data class Data(
        @SerializedName("birthday")
        val birthday: String = "",
        @SerializedName("body_type")
        val bodyType: String = "",
        @SerializedName("company")
        val company: String = "",
        @SerializedName("education")
        val education: String = "",
        @SerializedName("gender")
        val gender: String = "",
        @SerializedName("height")
        val height: Int = 0,
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("introduction")
        val introduction: String = "",
        @SerializedName("job")
        val job: String = "",
        @SerializedName("location")
        val location: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("pictures")
        val pictures: List<String> = listOf(),
        @SerializedName("school")
        val school: String = ""
    ) : Parcelable

    @Keep
    @Parcelize
    data class Meta(
        @SerializedName("body_types")
        val bodyTypes: List<BodyType> = listOf(),
        @SerializedName("educations")
        val educations: List<Education> = listOf(),
        @SerializedName("genders")
        val genders: List<Gender> = listOf(),
        @SerializedName("height_range")
        val heightRange: HeightRange = HeightRange()
    ) : Parcelable {
        @Keep
        @Parcelize
        data class BodyType(
            @SerializedName("key")
            val key: String = "",
            @SerializedName("name")
            val name: String = ""
        ) : Parcelable

        @Keep
        @Parcelize
        data class Education(
            @SerializedName("key")
            val key: String = "",
            @SerializedName("name")
            val name: String = ""
        ) : Parcelable

        @Keep
        @Parcelize
        data class Gender(
            @SerializedName("key")
            val key: String = "",
            @SerializedName("name")
            val name: String = ""
        ) : Parcelable

        @Keep
        @Parcelize
        data class HeightRange(
            @SerializedName("max")
            val max: Int = 0,
            @SerializedName("min")
            val min: Int = 0
        ) : Parcelable
    }
}