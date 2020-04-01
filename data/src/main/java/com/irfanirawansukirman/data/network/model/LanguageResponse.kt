package com.irfanirawansukirman.data.network.model

import com.irfanirawansukirman.data.network.base.DomainMapper
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LanguageResponse(
    @SerialName("choices")
    val choices: List<Choice>,
    @SerialName("published_at")
    val publishedAt: String?,
    @SerialName("question")
    val question: String?
) : DomainMapper<LanguangeMapper> {
    override fun mapToDomainModel() =
        LanguangeMapper(question)
}

@Serializable
data class Choice(
    @SerialName("choice")
    val choice: String?,
    @SerialName("votes")
    val votes: Int?
)