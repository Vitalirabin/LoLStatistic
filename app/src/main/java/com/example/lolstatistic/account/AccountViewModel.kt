package com.example.lolstatistic.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolstatistic.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountViewModel():ViewModel() {
    val accountRepository = AccountRepository(ApiFactory.getApi())
    val accountModel = MutableLiveData<AccountModel?>()
    fun loadAccount(id:String) {
        Log.e("StatisticViewModel","loadPuuid")
        viewModelScope.launch {
            Log.e("StatisticViewModel","coroutine")
            accountModel.value = withContext(Dispatchers.IO) {
                accountRepository.getAccount(id).data
            }
        }
    }
}