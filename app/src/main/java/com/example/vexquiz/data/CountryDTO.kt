package com.example.vexquiz.data

import com.example.vexquiz.domain.Country
import com.example.vexquiz.domain.Flag
import kotlinx.serialization.Serializable

@Serializable
data class CountriesRootResponse(
    val data: CountriesDataResponse
)

@Serializable
data class CountriesDataResponse(
    val objects: List<CountryDTO>
)

@Serializable
data class CountryDTO(
    val names: NamesDTO,
    val capitals: List<CapitalDTO>,
    val flag: FlagDTO
)

@Serializable
data class NamesDTO(
    val common: String
)

@Serializable
data class CapitalDTO(
    val name: String
)

@Serializable
data class FlagDTO(
    val emoji: String,
    val url_png: String
)

fun CountryDTO.toDomain(): Country {
    return Country(
        name = this.names.common,
        capitals = capitals.map(CapitalDTO::name),
        flag = Flag(
            emoji = this.flag.emoji,
            url = this.flag.url_png
        )
    )
}
