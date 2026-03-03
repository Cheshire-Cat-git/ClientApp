package com.example.client_app.ui

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.client_app.data.AppDatabase
import com.example.client_app.data.GeneratorContract
import com.example.client_app.data.HexCode
import com.example.client_app.data.HexRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HexViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HexRepository
    private val TAG = "HexViewModel"

    private val _hexCodes = MutableStateFlow<List<HexCode>>(emptyList())
    val hexCodes: StateFlow<List<HexCode>> = _hexCodes.asStateFlow()

    private val _isServiceGenerating = MutableStateFlow(false)
    val isServiceGenerating: StateFlow<Boolean> = _isServiceGenerating.asStateFlow()

    private val hexReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "Received broadcast: ${intent.action}")
            when (intent.action) {
                GeneratorContract.ACTION_GENERATE -> {
                    val code = intent.getStringExtra(GeneratorContract.EXTRA_CODE)
                    Log.d(TAG, "HEX Code received: $code")
                    if (code != null) {
                        viewModelScope.launch {
                            repository.insert(HexCode(code = code))
                        }
                    }
                }
                GeneratorContract.ACTION_STATUS_REPLY -> {
                    val isGenerating = intent.getBooleanExtra(GeneratorContract.EXTRA_IS_GENERATING, false)
                    Log.d(TAG, "Status updated: isGenerating = $isGenerating")
                    _isServiceGenerating.value = isGenerating
                }
            }
        }
    }

    init {
        val dao = AppDatabase.getDatabase(application).hexCodeDao()
        repository = HexRepository(application, dao)

        viewModelScope.launch {
            repository.allCodes.collect { codes ->
                _hexCodes.value = codes
            }
        }

        val filter = IntentFilter().apply {
            addAction(GeneratorContract.ACTION_GENERATE)
            addAction(GeneratorContract.ACTION_STATUS_REPLY)
            priority = 1000
        }

        Log.d(TAG, "Registering receiver for actions: ${GeneratorContract.ACTION_GENERATE}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            application.registerReceiver(hexReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            application.registerReceiver(hexReceiver, filter)
        }

        requestStatus()
    }

    fun startGenerating() {
        Log.d(TAG, "Sending Start command")
        repository.setGenerationMode(true)
    }

    fun stopGenerating() {
        Log.d(TAG, "Sending Stop command")
        repository.setGenerationMode(false)
    }

    fun requestStatus() {
        Log.d(TAG, "Requesting status")
        repository.requestStatus()
    }

    fun deleteCode(id: Int) = viewModelScope.launch {
        repository.delete(id)
    }

    fun deleteAllCodes() = viewModelScope.launch {
        repository.deleteAll()
    }

    override fun onCleared() {
        super.onCleared()
        try {
            getApplication<Application>().unregisterReceiver(hexReceiver)
        } catch (e: Exception) {
            Log.e(TAG, "Error unregistering receiver", e)
        }
    }
}
