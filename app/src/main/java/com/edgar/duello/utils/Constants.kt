package com.edgar.duello.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.edgar.duello.activities.MyProfileActivity

object Constants {
    const val ID: String = "id"
    const val NAME: String = "name"
    const val USERS: String = "users"
    const val EMAIL: String = "email"
    const val IMAGE: String = "image"
    const val SELECT: String = "Select"
    const val MOBILE: String = "mobile"
    const val BOARDS: String = "boards"
    const val TASK_LIST: String = "taskList"
    const val UN_SELECT: String = "UnSelect"
    const val ASSIGNED_TO: String = "assignedTo"
    const val DOCUMENT_ID: String = "documentId"
    const val BOARD_DETAIL: String = "board_detail"
    const val BOARD_MEMBERS_LIST: String = "board_members_list"
    const val TASK_LIST_ITEM_POSITION: String = "task_list_item_position"
    const val CARD_LIST_ITEM_POSITION: String = "card_list_item_position"

    const val DUELLO_PREFERENCES: String = "DuelloPrefs"
    const val FCM_TOKEN: String = "fcmToken"
    const val FCM_TOKEN_UPDATED: String = "fcmTokenUpdated"

    const val FCM_BASE_URL: String = "https://fcm.googleapis.com/fcm/send"
    const val FCM_AUTHORIZATION: String = "authorization"
    const val FCM_KEY: String = "key"
    const val FCM_SERVER_KEY: String =
        "AAAAqSdkny8:APA91bFHx-yGtNTZPqsexiKXPke7bcvIHkcicznsLoJ2ph7DtvoY6my0QDDSzGn6cFUtTx3BIYMq_Noy3O5L_UqRH2rc9CISoGJqXALD-imrmZLw7MNpDaRhCzjCH30v5KCwTTVsu1g1"
    const val FCM_KEY_TITLE: String = "title"
    const val FCM_KEY_MESSAGE: String = "message"
    const val FCM_KEY_DATA: String = "data"
    const val FCM_KEY_TO: String = "to"

    const val READ_STORAGE_PERMISSION_CODE = 1
    const val PICK_IMAGE_REQUEST_CODE = 2

    fun showImageChooser(activity: Activity) {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}