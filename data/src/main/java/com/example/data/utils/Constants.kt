package com.example.data.utils


object Constants {

    var BASE_URL = ""


    object PrefKeys {
        const val USER_DATA = "USER_DATA"
        const val LANG = "lang"
        const val DEVICE_ID = "dev_id"
        const val IS_USER_LOGGED = "IS_USER_LOGGED"
        const val IS_USER_REGISTER = "IS_REGISTER"
        const val TOKEN = "TOKEN"
        const val IS_GUEST_USER_LOGGED = "IS_GUEST_USER_LOGGED"
        const val PREFERENCES_NAME = "PREFERENCES_NAME"
        const val DEVICE_TOKEN = "DEVICE_TOKEN"
        const val LOCATION_DATA = "LOCATION_DATA"
        const val LOCATION_SAVED = "LOCATION_SAVED"
        const val IS_LOCATION_UPDATED = "IS_LOCATION_UPDATED"
        const val FIREBASE: String = "fcm"
        const val STATUS: String = "status"
        const val INTERVAL: String = "interval"
        const val HAS_REGISTER: String = "hasRegister"
        const val SHOW_NOTIFICATION: String = "notification_show"
        const val USER: String = "user"
        const val SESSION: String = "session"
        const val IS_VIEW_WELCOME_SCREENS = "user-view-welcome-screens"
    }


    object ERROR_API {
        //deleted or unauthorized 401
        const val UNAUTHRIZED = "unAuthroized"

        //500
        const val SERVER_ERROR = "serverError"

        const val CONNECTION_ERROR = "no_connection"


    }

    object INTENT {
        const val TITLE = "title"
        const val MAIN_CATEGORY = "main-category"
        const val SUB_CATEGORY = "sub-category"
        const val PROPERTY_OPTION = "options"
        const val SELECTED_PROPERTY_OPTION = "selected-options"

    }

}