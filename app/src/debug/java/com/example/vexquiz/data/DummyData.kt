package com.example.vexquiz.data

import com.example.vexquiz.domain.Country
import com.example.vexquiz.domain.Flag

object DummyData {
    val canada = Country(
        name = "Canada",
        capitals = listOf("Ottawa"),
        flag = Flag(
            emoji = "🇨🇦",
            url = ""
        )

    )

    val japan = Country(
        name = "Japan",
        capitals = listOf("Tokyo"),
        flag = Flag(
            emoji = "🇯🇵",
            url = ""
        )

    )

    val usa = Country(
        name = "United States of America",
        capitals = listOf("Washington D.C."),
        flag = Flag(
            emoji = "🇺🇸",
            url = ""
        )

    )

    fun dummyList(count: Int = 10): List<Country> {
        return List(count) { index ->
            Country(
                name = "Country $index",
                capitals = listOf("Capital $index"),
                flag = Flag(
                    emoji = "🏳️",
                    url = ""
                )
            )
        }
    }
}