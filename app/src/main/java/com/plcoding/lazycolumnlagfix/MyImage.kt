package com.plcoding.lazycolumnlagfix

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class MyImage(
    val resId: Int,
    val title: String,
    val tags: List<String>,
    val id: String = UUID.randomUUID().toString()
)