package com.asia.newsapp.ui.util

import java.text.SimpleDateFormat
import java.util.TimeZone

fun formatDate(input: String): String? {
   return  try {
       val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
       inputFormat.timeZone = TimeZone.getTimeZone("UTC")
       val date = inputFormat.parse(input)
       val outputFormat = SimpleDateFormat("MMM d, yyyy - h:mm a")
       outputFormat.timeZone = TimeZone.getDefault() // Set your desired timezone here
       return date?.let { outputFormat.format(it) }
   }catch (e:Exception){
       e.printStackTrace()
       null
   }

}
