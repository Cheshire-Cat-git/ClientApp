package com.example.client_app.data

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.Flow

class HexRepository(private val context: Context, private val dao: HexCodeDao) {

    val allCodes: Flow<List<HexCode>> = dao.getAllCodes()

    suspend fun insert(hexCode: HexCode) {
        dao.insert(hexCode)
    }

    suspend fun delete(id: Int) {
        dao.delete(id)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    fun setGenerationMode(enabled: Boolean) {
        val intent = Intent(GeneratorContract.ACTION_SET_GENERATION_MODE).apply {
            putExtra(GeneratorContract.EXTRA_GENERATION_ENABLED, enabled)
            setPackage(GeneratorContract.SERVICE_PACKAGE)
        }
        context.sendBroadcast(intent)
    }

    fun requestStatus() {
        val intent = Intent(GeneratorContract.ACTION_GET_STATUS).apply {
            setPackage(GeneratorContract.SERVICE_PACKAGE)
        }
        context.sendBroadcast(intent)
    }
}
