package com.example.minimoneybox.utils

// URLS
const val BASE_URL: String = "https://api-test01.moneyboxapp.com"

// REGEX
const val EMAIL_REGEX = "[^@]+@[^.]+\\..+"
const val NAME_REGEX = "[a-zA-Z]{6,30}"
const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{10,50}$"

// ERRORS
const val EMAIL_ADDRESS_ERROR = "Please enter a correct email address"
const val PASSWORD_ERROR = "Please enter a correct password"
const val FULL_NAME_ERROR = "Please enter your full name"

// PARAMETERS
const val IFDA = "ANYTHING"
const val PREF_FILENAME = "app_preferences"