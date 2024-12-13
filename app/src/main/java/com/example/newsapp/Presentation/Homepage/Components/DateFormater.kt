package com.example.newsapp.Presentation.Homepage.Components

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


fun String.formatToReadableDate(): String {
    // Parse the input ISO 8601 date string
    val parsedDate = ZonedDateTime.parse(this)
    // Define the desired output format
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault())
    // Format the date
    return parsedDate.format(formatter)
}


