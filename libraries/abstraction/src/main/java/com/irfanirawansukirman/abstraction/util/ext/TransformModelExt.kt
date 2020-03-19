package com.irfanirawansukirman.abstraction.util.ext

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

fun getJson() = Json(JsonConfiguration.Stable)

inline fun <reified T> List<*>.asListOfType(): List<T>? =
    if (all { it is T })
        @Suppress("UNCHECKED_CAST")
        this as List<T> else
        null

