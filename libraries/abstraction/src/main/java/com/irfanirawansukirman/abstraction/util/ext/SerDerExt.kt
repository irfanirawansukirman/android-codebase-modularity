package com.irfanirawansukirman.abstraction.util.ext

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

fun getJsonSD() = Json(JsonConfiguration.Stable)