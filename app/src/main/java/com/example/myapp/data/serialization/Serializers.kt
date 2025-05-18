package com.example.myapp.data.serialization

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
val customSerializersModule = SerializersModule {
    contextual(OffsetDateTime::class, OffsetDateTimeSerializer)
}

@RequiresApi(Build.VERSION_CODES.O)
val json = Json {
    serializersModule = customSerializersModule
    ignoreUnknownKeys = true
}