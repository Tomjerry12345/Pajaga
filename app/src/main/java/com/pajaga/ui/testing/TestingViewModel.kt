package com.pajaga.ui.testing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pajaga.database.retrofit.RetrofitInstance
import com.pajaga.model.PushNotification
import com.pajaga.utils.network.Response
import com.pajaga.utils.other.showLogAssert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class TestingViewModel() : ViewModel() {

    fun getResponse(pushNotification: PushNotification): MutableLiveData<Response> {
        val data = MutableLiveData<Response>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = RetrofitInstance.api.postNotification(pushNotification)
                    if (response.isSuccessful) {
                        showLogAssert("response", "Response: ${Gson().toJson(response)}")
                    } else {
                        showLogAssert("error", response.errorBody().toString())
                    }
//                    val resultConnection = examplesApiRepository.checkConnection()
//                    if (resultConnection.code == 200) {
//                        val resultData = examplesApiRepository.getData()
//                        data.postValue(Response.Changed(resultData))
//                    }
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            data.postValue(Response.Error("Network Error => ${throwable.message}"))
                        }
                        is HttpException -> {
                            val code = throwable.code()
                            val errorResponse = throwable.message()
                            data.postValue(Response.Error("Error $code $errorResponse"))
                        }
                        else -> {
                            data.postValue(Response.Error("Unknown Error => ${throwable.message}"))
                        }
                    }
                }
            }
        }

        return data
    }

    class Factory() : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TestingViewModel() as T
        }
    }

}