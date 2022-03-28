package com.pajaga.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pajaga.R
import com.pajaga.database.retrofit.RetrofitInstance
import com.pajaga.model.Contact
import com.pajaga.model.Permission
import com.pajaga.model.PushNotification
import com.pajaga.model.Zone
import com.pajaga.ui.main.home.adapter.ContactAdapter
import com.pajaga.ui.main.home.adapter.PermissionAdapter
import com.pajaga.ui.main.home.adapter.ZoneAdapter
import com.pajaga.utils.network.Response
import com.pajaga.utils.other.showLogAssert
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(val rvContact : RecyclerView, val rvZone: RecyclerView ,val rvPermission: RecyclerView) : ViewModel() {

    class Factory(val rvContact : RecyclerView, val rvZone : RecyclerView, val rvPermission: RecyclerView) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(rvContact,rvZone, rvPermission) as T
        }
    }

    val listContact = ArrayList<Contact>()
    val listZone = ArrayList<Zone>()
    val listPermission = ArrayList<Permission>()


    fun setData(){
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang1,"Dani","Friend"))
        listContact.add(Contact(R.drawable.orang2,"Pei","Friend"))
        listContact.add(Contact(R.drawable.orang3,"Andri","College"))
        setRecContact()

    }

    fun setDataPermission(){
        listPermission.add(Permission("1","ACCESS_FINE_LOCATION",false))
        listPermission.add(Permission("2","ACCESS_WIFI_STATE",false))
        listPermission.add(Permission("3","ACCESS_COARSE_LOCATION",false))
        setRecPermission()
    }

    fun setDataZone(){
        listZone.add(Zone("Jl. Sultan Hasanuddin","23:00 - 04:00 Wita"))
        listZone.add(Zone("Jl. Sultan Alauddin","23:00 - 04:00 Wita"))
        listZone.add(Zone("Jl. Sultan ","23:00 - 04:00 Wita"))
        setRecZone()

    }

    fun setRecContact(){

        val adapterr = ContactAdapter(listContact)
        rvContact.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = adapterr
        }

    }
     fun setRecPermission(){

        val adapterr = PermissionAdapter(listPermission)
        rvPermission.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = adapterr
        }

    }



    fun setRecZone(){
        val adapterrr = ZoneAdapter(listZone)
        rvZone.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = adapterrr
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getResponse(pushNotification: PushNotification): MutableLiveData<Response> {
        val data = MutableLiveData<Response>()
        GlobalScope.launch(Dispatchers.IO) {
//            withContext(Dispatchers.IO) {
                try {
                    showLogAssert("test response", "true")
                    val response = RetrofitInstance.apiNotif.postNotification(pushNotification)
                    showLogAssert("after response", "true")
                    if (response.isSuccessful) {
                        showLogAssert("response", "Response: ${Gson().toJson(response)}")
                    } else {
                        showLogAssert("error", response.errorBody().toString())
                    }
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
//            }
        }
        return data
    }

}