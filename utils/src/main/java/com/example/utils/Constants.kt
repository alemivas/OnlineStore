package com.example.utils

object Constants {
    const val BASE_URL = "https://api.escuelajs.co"
    const val NAME_DATABASE = "catalog.db"
    const val DEFAULT_MANAGER_PASSWORD = "123456"
    enum class TypeOfAccount { USER, MANAGER }

    enum class SortType {
        NAME,
        PRICE,
        RANGE
    }

    enum class Country {
        USA,
        BRAZIL,
        ARGENTINA,
        MEXICO,
        EUROPE,
        UNITED_KINGDOM,
        JAPAN,
        RUSSIA,
        CHINA
    }
}