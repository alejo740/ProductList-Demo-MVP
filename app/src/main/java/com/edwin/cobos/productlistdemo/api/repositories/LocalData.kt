package com.edwin.cobos.productlistdemo.api.repositories

import android.content.Context
import android.util.Log
import java.io.IOException

class LocalData {

    companion object {


        fun getAssetJsonData(context: Context): String? {
            var json: String = ""
            try {
                val inputStream = context.assets.open("codeTest_exploreData.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, charset("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            Log.e("data", json)
            return json

        }
    }

}