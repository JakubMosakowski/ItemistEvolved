package com.jakmos.itemistevolved.domain.model.project

import java.util.*

interface DateTimeInterface {
    val date: Date
}

class DateTime : DateTimeInterface {
    override val date: Date
        get() = Date()
}