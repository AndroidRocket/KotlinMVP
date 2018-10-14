package com.wilson.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import java.util.*


class Utils {

    companion object {
        private val cache = Hashtable<String, Typeface>()


        fun toast(message: CharSequence,context: Context) =
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()



        fun isNetworkConnected(context: Context): Boolean {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }


        fun getTypeFace(aContext: Context, assetFile: String): Typeface? {
            synchronized(cache) {
                if (!cache.containsKey(assetFile)) {
                    try {
                        val t = Typeface.createFromAsset(aContext.getAssets(),
                                assetFile)
                        cache.put(assetFile, t)
                    } catch (e: Exception) {
                        Log.e(TAG, "Could not get1 typeface '" + assetFile
                                + "' because " + e.message)
                        return null
                    }

                }
                return cache.get(assetFile)
            }
        }



    }

}