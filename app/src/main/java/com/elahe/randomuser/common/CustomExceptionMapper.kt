package com.elahe.randomuser.common

import com.elahe.randomuser.R
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

class CustomExceptionMapper {

    companion object {
        fun map(throwable: Throwable): CustomException {
            if (throwable is HttpException) {
                try {
                    val errorJsonObject = JSONObject(throwable.response()?.errorBody()?.string()!!)
                    val errorMessage = errorJsonObject.getString("message")
                    return CustomException(
                        if (throwable.code() == 401) CustomException.Type.AUTH else CustomException.Type.SIMPLE,
                        serverMessage = errorMessage
                    )
                } catch (exception: Exception) {
                    Timber.e(exception)
                }
            }
            return CustomException(CustomException.Type.SIMPLE, R.string.error_message)
        }
    }

}