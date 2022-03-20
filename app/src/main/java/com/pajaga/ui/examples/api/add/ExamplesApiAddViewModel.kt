package com.pajaga.ui.examples.api.add

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pajaga.repository.ExamplesApiRepository
import com.pajaga.utils.network.RetrofitUtils
import com.pajaga.utils.other.showLogAssert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream


@SuppressLint("StaticFieldLeak")
class ExamplesApiAddViewModel(
    val examplesApiRepository: ExamplesApiRepository
) : ViewModel() {

    lateinit var imageFile: InputStream
    lateinit var mimeImage: String
    lateinit var mimeFile: String
    lateinit var documentFile: InputStream

    fun onAddData(view: View) {
        if (::imageFile.isInitialized && ::documentFile.isInitialized) {
            uploadFile(imageFile, documentFile)
        } else {
            showLogAssert("error", "file kosong")
        }
    }

    private fun uploadFile(imageFile: InputStream, documentFile: InputStream) {
        val bodyImage = RetrofitUtils.createRequestFile("image", imageFile, mimeImage)
        val bodyFile = RetrofitUtils.createRequestFile("file", documentFile, mimeFile)

        val list = listOf(
            bodyImage,
            bodyFile
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = examplesApiRepository.addData("test", "alamat", "55555", list)
                    showLogAssert("response", response.toString())
                } catch (t: Throwable) {
                    showLogAssert("error", t.message.toString())
                }
            }
        }
    }

    class Factory(
        private val examplesApiRepository: ExamplesApiRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExamplesApiAddViewModel(examplesApiRepository) as T
        }
    }
}